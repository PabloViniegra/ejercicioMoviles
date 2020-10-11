package com.example.ejercicio_pablo2dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
TextView txtTimeFromHome = null;
InitHomeActivity home = null;
Button btnPermissons = null;
Button btnToHome = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        home = new InitHomeActivity();
        home.chronometer();
        Intent timeFromHome = getIntent();
        String actualTime = timeFromHome.getStringExtra("KEY");
        txtTimeFromHome = findViewById(R.id.txtTimeHomeActivity);
        txtTimeFromHome.append(actualTime);
        btnPermissons = findViewById(R.id.btnAddPermissions);
        btnToHome = findViewById(R.id.btnBackToMain);

        //EVENTO DE CLICK DE LOS PERMISOS DE ACCESO
        btnPermissons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}