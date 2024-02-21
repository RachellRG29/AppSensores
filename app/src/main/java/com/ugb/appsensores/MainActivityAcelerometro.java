package com.ugb.appsensores;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class MainActivityAcelerometro extends AppCompatActivity {
    TextView tempVal;
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_acelerometro);

        //cambiar color barra de estado
        cambiarColorBarraEstado(getResources().getColor(R.color.bluegraylight));

        tempVal = findViewById(R.id.lblAcelerometro);
        activarSensorAcelerometro();
    }

    @Override
    protected void onResume() {
        iniciarAcelerometro();
        super.onResume();
    }
    @Override
    protected void onPause() {
        detenerAcelerometro();
        super.onPause();
    }

    private void activarSensorAcelerometro(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor==null){
            tempVal.setText("Tu telefono NO tiene sensor Acelerometro.");
            finish();
        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                tempVal.setText("Acelerometro: X="+ sensorEvent.values[0] +"; Y="+ sensorEvent.values[1] +"; Z="+ sensorEvent.values[2]);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }

    private void iniciarAcelerometro(){
        sensorManager.registerListener(sensorEventListener, sensor, 2000*1000);
    }
    private void detenerAcelerometro(){
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