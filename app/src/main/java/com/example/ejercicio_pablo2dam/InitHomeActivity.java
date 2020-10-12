package com.example.ejercicio_pablo2dam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

public class InitHomeActivity extends AppCompatActivity {
    TimerTask mytimertask = null;
    Timer timer = null;
    public final int REQUEST_ACTIVITY_RESULT = 1;
    public final String TAG = getClass().getName();
    public TextView cajaTexto = null;
    public TextView txtcontador = null;
    public TextView txtFromThird = null;
    public TextView txtData = null;
    public TextView txtFromSecond = null;
    public TextView txtPermissions = null;
    public Button btnSecondActivity = null;
    public Button btnThirdActivity = null;
    int currentTime = 0;
    public static boolean SAVE = false;
    public static boolean PERMISSIONS = false;
    Activity actualActivity = this;
    public ArrayList <Integer> totalSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_home);
        Log.d(TAG, "En OnCreate");
        chronometer();
        cajaTexto = findViewById(R.id.textoTiempo);
        txtcontador = findViewById(R.id.contadortxt);
        txtFromSecond = findViewById(R.id.txtSecondActivity);
        txtFromThird = findViewById(R.id.txtThirdActivity);
        txtPermissions = findViewById(R.id.txtPermissions);
        txtData = findViewById(R.id.txtSaveData);
        btnSecondActivity = findViewById(R.id.secondActivityButton);
        btnThirdActivity = findViewById(R.id.thirdActivityButton);

        btnSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(InitHomeActivity.this, SecondActivity.class);
                secondIntent.putExtra("KEY", String.valueOf(totalSeconds.get(totalSeconds.size()-1)));
                cajaTexto.setText(String.valueOf(totalSeconds.get(totalSeconds.size()-1)));
                resetChronometer(timer);
                startActivity(secondIntent);
            }
        });

        btnThirdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thirdIntent = new Intent(InitHomeActivity.this,ThirdActivity.class);
                thirdIntent.putExtra("KEY",String.valueOf(totalSeconds.get(totalSeconds.size()-1)));
                cajaTexto.setText(String.valueOf(totalSeconds.get(totalSeconds.size()-1)));
                resetChronometer(timer);
                startActivity(thirdIntent);
            }
        });
        Intent fromSecond = getIntent();
        String aux = fromSecond.getStringExtra("KEY_SECOND_BACK");
        txtFromSecond.setText(aux);

        Intent fromThird = getIntent();
        String aux2 = fromThird.getStringExtra("KEY_THIRD_BACK");
        txtFromThird.setText(aux2);
        txtData.setText(String.valueOf(SAVE));
        txtPermissions.setText(String.valueOf(PERMISSIONS));

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "En OnResume");
        Toast.makeText(getApplicationContext(), "OnResume en " + TAG, Toast.LENGTH_SHORT).show();
        if (timer != null) {
            timer = null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void chronometer() {
        totalSeconds = new ArrayList<>();
        mytimertask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        currentTime++;
                        totalSeconds.add(currentTime);
                        txtcontador.setText(String.valueOf(currentTime));
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(mytimertask, 1, 1000);
    }

    public static void resetChronometer (Timer t) {
        t = null;

    }
}