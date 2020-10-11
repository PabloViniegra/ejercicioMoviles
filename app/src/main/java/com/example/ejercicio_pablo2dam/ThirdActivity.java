package com.example.ejercicio_pablo2dam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ThirdActivity extends AppCompatActivity {
TextView txtTimeFromHome = null;
InitHomeActivity home = null;
Button btnPermissons = null;
Button btnToHome = null;
ArrayList <Integer> totalSeconds;
TimerTask mytimertask = null;
TextView txtTimeFromInit = null;
TextView txtContador = null;
int currentTime = 0;
Timer timer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        home = new InitHomeActivity();
        home.chronometer();
        final Intent timeFromHome = getIntent();
        String actualTime = timeFromHome.getStringExtra("KEY");
        txtTimeFromHome = findViewById(R.id.txtTimeHomeActivity);
        txtTimeFromHome.append(actualTime);
        btnPermissons = findViewById(R.id.btnAddPermissions);
        btnToHome = findViewById(R.id.btnBackToMain);
        chronometer();
        //EVENTO DE CLICK DE LOS PERMISOS DE ACCESO
        btnPermissons.setOnClickListener(new View.OnClickListener() {
            private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 0 ;

            @Override
            public void onClick(View v) {
                if(PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(ThirdActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)){

                    //En caso de que no fueran concedidos con anterioridad mostramos el diálogo para concederlos
                    ActivityCompat.requestPermissions(ThirdActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);

                    Toast.makeText(getApplicationContext(), "Permiso concedido!!!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Permiso concedido en el pasado!!!", Toast.LENGTH_SHORT).show();
                }
                InitHomeActivity.PERMISSIONS = true;
            }

        });
        Intent back = getIntent();
        String aux = back.getStringExtra("KEY");
        txtTimeFromInit = findViewById(R.id.txtTimeHomeActivity);
        txtTimeFromInit.append(aux);
        btnToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timeFromThird = new Intent(getApplicationContext(),InitHomeActivity.class);
                timeFromHome.putExtra("KEY_THIRD_BACK",String.valueOf(totalSeconds.get(totalSeconds.size()-1)));
            }
        });
    }

    public void chronometer() {
        txtContador = findViewById(R.id.txtContador);
        totalSeconds = new ArrayList<>();
        mytimertask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        currentTime++;
                        totalSeconds.add(currentTime);
                        txtContador.setText(currentTime);
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(mytimertask, 1, 1000);
    }
}