package com.example.ejercicio_pablo2dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    public final String TAG = getClass().getName();
    final int REQUEST = 2;
    public Button btnSave = null;
    public Button btnBack = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, "en OnCreate");
        btnSave = findViewById(R.id.buttonSaveSecondAct);
        btnBack = findViewById(R.id.backToHome);
        Intent receiveIntent = getIntent();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitHomeActivity.SAVE = true;
                Intent save = new Intent(getApplicationContext(), InitHomeActivity.class);
                save.putExtra("OK_REQUEST", REQUEST);
                startActivity(save);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(),InitHomeActivity.class);
                startActivity(back);
                Log.d(TAG,"vuelvo del SecondActivity");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"En OnResume");
    }
}