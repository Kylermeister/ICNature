package com.example.icnature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;


import Data.Plant;
import Data.PlantDB;

public class plantViewActivity extends AppCompatActivity {

    String name;
    String scientificName;
    String habitat;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_view);
        Button btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(view -> openMain());

        TextView tv = (TextView)findViewById(R.id.plantTV);
        Plant p = new Plant("Oak Tree", "Quercus spp","Forests", "A large tree with broad leaves");
        p.updateTV(tv);
        /*
        try {


            PlantDB plantDB = new PlantDB();


            Plant plant = plantDB.createPlant();

            tv.setText("bb");
            if (plant != null) {
                tv.setText("b");
                plant.updateTV(tv);
            } else {
                tv.setText("c");
                System.out.println("Failed to read plant data.");
            }
        } catch (IOException e) {
        System.out.println("An error occurred while reading data: " + e.getMessage());
    }

         */
    }

    public void openMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}