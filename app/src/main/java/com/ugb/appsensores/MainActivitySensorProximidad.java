package com.ugb.appsensores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivitySensorProximidad extends AppCompatActivity {
    TextView tempVal;
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sensor_proximidad);

        //cambiar color barra de estado
        cambiarColorBarraEstado(getResources().getColor(R.color.bluegraylight));

        tempVal = findViewById(R.id.lblProximidad);
        activarSensorProximidad();
    }

    @Override
    protected void onResume() {
        iniciarProximidad();
        super.onResume();
    }
    @Override
    protected void onPause() {
        detenerProximidad();
        super.onPause();
    }
    private void activarSensorProximidad(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(sensor==null){
            tempVal.setText("Tu telefono NO tiene sensor Proximidad.");
            finish();
        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                double valor = sensorEvent.values[0];
                tempVal.setText("Proximidad: "+ valor);
                if( valor<=4 ){
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                } else if (valor<=8) {
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }else{
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }
    private void iniciarProximidad(){
        sensorManager.registerListener(sensorEventListener, sensor, 2000*1000);
    }
    private void detenerProximidad(){
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