package com.example.icnature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMap = (Button) findViewById(R.id.buttonMap);
        btnMap.setOnClickListener(view -> openMap());

        Button btnBirdID = (Button) findViewById(R.id.buttonBID);
        btnBirdID.setOnClickListener(view -> openBirdID());

        Button btnPlantID = (Button) findViewById(R.id.buttonPID);
        btnPlantID.setOnClickListener(view -> openPlantID());

        Button btnPlantList = (Button) findViewById(R.id.buttonLP);
        btnPlantList.setOnClickListener(view -> openPlantList());

        Button btnBirdList = (Button) findViewById(R.id.buttonLB);
        btnBirdList.setOnClickListener(view -> openBirdList());

        Button btnQuiz = (Button) findViewById(R.id.buttonQuiz);
        btnQuiz.setOnClickListener(view -> openQuiz());
    }

    public void openMap(){
        Intent intent = new Intent(this, mapActivity.class);
        startActivity(intent);
    }

    public void openBirdID(){
        Intent intent = new Intent(this,birdIDActivity.class);
        startActivity(intent);
    }

    public void openPlantID(){
        Intent intent = new Intent(this,plantIDActivity.class);
        startActivity(intent);
    }

    public void openBirdList(){
        Intent intent = new Intent(this, listBirdsScrollingActivity.class);
        startActivity(intent);
    }

    public void openPlantList(){
        Intent intent = new Intent(this, listPlantsScrollingActivity.class);
        startActivity(intent);
    }
    public void openQuiz(){
        Intent intent = new Intent(this, quizActivity.class);
        startActivity(intent);
    }
}