package com.ugb.appsensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //cambiar color barra de estado
        cambiarColorBarraEstado(getResources().getColor(R.color.bluegrey));

        //boton ingresar Acelerometro
        Button btnAcel = (Button) findViewById(R.id.btnAcelerometro);
        btnAcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    btnAcel.setOnClickListener(v -> openAcelerometro());
            }
        });
        //Giroscopio
        Button btnGir = (Button) findViewById(R.id.btnGiroscopio);
        btnGir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnGir.setOnClickListener(v -> openGiroscopio());
            }
        });
        //Sensor luz
        Button btnLuz = (Button) findViewById(R.id.btnSensorLuz);
        btnLuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLuz.setOnClickListener(v -> openSensorLuz());
            }
        });
        //sensor proximidad
        Button btnProx = (Button) findViewById(R.id.btnProximidad);
        btnProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnProx.setOnClickListener(v -> openSensorProximidad());
            }
        });
        //magnometro
        Button btnMag = (Button) findViewById(R.id.btnMagnometro);
        btnMag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnMag.setOnClickListener(v -> openMagnometro());
            }
        });
        //sensor huella
        Button btnHuel = (Button) findViewById(R.id.btnHuella);
        btnHuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHuel.setOnClickListener(v -> openSensorHuella());
            }
        });


    }

    private void cambiarColorBarraEstado(int color) {
        // Verificar si la versión del SDK es Lollipop o superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    } //fin cambiar colorbarraestado

    //Botones para pasar a nuevos activitys
    public void openAcelerometro(){
        Intent intent = new Intent(this, MainActivityAcelerometro.class);
        startActivity(intent);

    }

    public void openGiroscopio(){
        Intent intent = new Intent(this, MainActivityGiroscopio.class);
        startActivity(intent);

    }

    public void openSensorLuz(){
         Intent intent = new Intent(this, MainActivitySensorLuz.class);
         startActivity(intent);

    }

    public void openSensorProximidad(){
         Intent intent = new Intent(this, MainActivitySensorProximidad.class);
         startActivity(intent);

    }

    public void openMagnometro(){
         Intent intent = new Intent(this, MainActivityMagnometro.class);
         startActivity(intent);

    }

    public void openSensorHuella(){
         Intent intent = new Intent(this, MainActivitySensorHuella.class);
         startActivity(intent);

    }
    //fin botones

}