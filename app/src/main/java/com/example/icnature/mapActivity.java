package com.example.icnature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class mapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Button btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(view -> openMain());
    }

    public void openMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}