package com.ugb.appsensores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.os.LocaleListCompat;

import android.hardware.SensorListener;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityGiroscopio extends AppCompatActivity {
    TextView tempVal;
    SensorManager sensorManager;
    Sensor gyroSensor;
    SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_giroscopio);

        //cambiar color barra de estado
        cambiarColorBarraEstado(getResources().getColor(R.color.bluegraylight));


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        tempVal = findViewById(R.id.lblGiroscopio);
        activarSensorGiroscopio();
    }

    @Override
    protected void onResume() {
        iniciarGiroscopio();
        super.onResume();
    }
    @Override
    protected void onPause() {
        detenerGiroscopio();
        super.onPause();
    }

    private void activarSensorGiroscopio(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(gyroSensor==null){
            tempVal.setText("Tu telefono NO tiene sensor Giroscopio.");
            finish();
        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                    float x = sensorEvent.values[0];
                    float y = sensorEvent.values[1];
                    float z = sensorEvent.values[2];

                    // imprimir en la consola
                    System.out.println("X: " + x + ", Y: " + y + ", Z: " + z);
                    //tempVal.setText("Giroscopio: X="+ x +"; Y="+ y +"; Z="+ z);
                    tempVal.setText("Giroscopio: X="+ sensorEvent.values[0] +"; Y="+ sensorEvent.values[1] +"; Z="+ sensorEvent.values[2]);
                }

            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }

    private void iniciarGiroscopio(){
        sensorManager.registerListener(sensorEventListener, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    private void detenerGiroscopio(){
        sensorManager.unregisterListener(sensorEventListener);
    }


    private void cambiarColorBarraEstado(int color) {
        // Verificar si la versión del SDK es Lollipop o superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    } //fin cambiar colorbarraestado
}