package com.example.ejercicio_pablo2dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SecondActivity extends AppCompatActivity {
    public final String TAG = getClass().getName();
    final int REQUEST = 2;
    public Button btnSave = null;
    public Button btnBack = null;
    public ArrayList <Integer> totalSeconds;
    public TimerTask mytimertask = null;
    public Timer timer = null;
    int currentTime = 0;
    public TextView txtContador = null;
    public TextView txtTimeMain = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, "en OnCreate");
        chronometer();
        btnSave = findViewById(R.id.buttonSaveSecondAct);
        btnBack = findViewById(R.id.backToHome);
        Intent receiveIntent = getIntent();
        String aux = receiveIntent.getStringExtra("KEY");
        txtTimeMain = findViewById(R.id.txtTimeMain);
        txtTimeMain.setText(aux);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitHomeActivity.SAVE = true;
                Toast.makeText(SecondActivity.this, "Se ha activado la opción de guardado", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(),InitHomeActivity.class);
                back.putExtra("KEY_SECOND_BACK",String.valueOf(totalSeconds.get(totalSeconds.size()-1)));
                Log.d(TAG,"vuelvo del SecondActivity");
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"En OnResume");
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
                        txtContador.setText(String.valueOf(currentTime));
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(mytimertask, 1, 1000);
    }
}