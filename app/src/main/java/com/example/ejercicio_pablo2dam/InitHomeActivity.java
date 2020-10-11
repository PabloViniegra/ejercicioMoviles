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
    public Button btnSecondActivity = null;
    public Button btnThirdActivity = null;
    int currentTime = 0;
    TextView txtFromSecond = null;
    TextView txtFromThird = null;
    TextView txtData = null;
    TextView txtPermissions = null;
    public static boolean SAVE = false;
    public static boolean PERMISSIONS = false;
    Activity actualActivity = this;
    ArrayList <Integer> totalSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_home);
        Log.d(TAG, "En OnCreate");
        cajaTexto = findViewById(R.id.textoTiempo);
        txtcontador = findViewById(R.id.contadortxt);
        btnSecondActivity = findViewById(R.id.secondActivityButton);
        btnThirdActivity = findViewById(R.id.thirdActivityButton);
        chronometer();

        btnSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(InitHomeActivity.this, SecondActivity.class);
                secondIntent.putExtra("KEY", currentTime);
                cajaTexto.append(String.valueOf(totalSeconds.get(totalSeconds.size()-1)));
                finish();
            }
        });

        btnThirdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thirdIntent = new Intent(InitHomeActivity.this,ThirdActivity.class);
                thirdIntent.putExtra("KEY",String.valueOf(totalSeconds.get(totalSeconds.size()-1)));
                finish();
            }
        });
        txtFromSecond = findViewById(R.id.txtSecondActivity);
        Intent fromSecond = getIntent();
        String aux = fromSecond.getStringExtra("KEY_SECOND_BACK");
        txtFromSecond.append(aux);

        txtFromThird = findViewById(R.id.txtThirdActivity);
        Intent fromThird = getIntent();
        String aux2 = fromThird.getStringExtra("KEY_THIRD_BACK");
        txtFromThird.append(aux2);
        txtData = findViewById(R.id.txtSaveData);
        txtPermissions = findViewById(R.id.txtPermissions);

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


}