package com.example.icnature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import Data.Bird;
import Data.Plant;

public class birdViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_view);
        Button btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(view -> openMain());

        TextView tv = (TextView)findViewById(R.id.birdTV);
        Bird b = new Bird("Blue Jay", "Cyanocitta cristata","Woodlands", "Vibrant blue bird with a crest");
        b.updateTV(tv);
    }

    public void openMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}