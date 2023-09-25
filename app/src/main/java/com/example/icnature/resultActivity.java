package com.example.icnature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/*
 * recieves the user score and displays it
 */
public class resultActivity extends AppCompatActivity{
    TextView textView ;

    /*
     * display the user score
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textView = findViewById(R.id.textView);

        // getting and displaying the score
        int score = getIntent().getIntExtra("Result",0);
        textView.setText("Score : " + score);

        //return to main menu
        findViewById(R.id.btn_restart).setOnClickListener(
                restart->{
                    Intent intent  = new Intent(this , MainActivity.class);
                    startActivity(intent);
                    finish();
                }
        );
    }
}
