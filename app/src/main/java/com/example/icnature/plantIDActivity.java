package com.example.icnature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class plantIDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_idactivity);
        Button btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(view -> openMain());

        Button btnSnap = (Button) findViewById(R.id.buttonSnap);
        btnSnap.setOnClickListener(view -> openViewPlant());
    }

    public void openMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void openViewPlant(){
        Intent intent = new Intent(this, plantViewActivity.class);
        startActivity(intent);
    }
}