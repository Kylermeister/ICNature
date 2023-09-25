package com.example.icnature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/*
 * main menu that redircts the user to different features
 */
public class MainActivity extends AppCompatActivity {

    /*
     * allows the user to clock on a button and move to a different screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMap = (Button) findViewById(R.id.buttonMap);
        btnMap.setOnClickListener(view -> openMap());

        Button btnPlantID = (Button) findViewById(R.id.buttonPID);
        btnPlantID.setOnClickListener(view -> openPlantID());

        Button btnPlantList = (Button) findViewById(R.id.buttonLP);
        btnPlantList.setOnClickListener(view -> openPlantList());

        Button btnBirdList = (Button) findViewById(R.id.buttonLB);
        btnBirdList.setOnClickListener(view -> openBirdList());

        Button btnQuiz = (Button) findViewById(R.id.buttonQuiz);
        btnQuiz.setOnClickListener(view -> openQuiz());

        Button btnBingo = (Button) findViewById(R.id.buttonBingo);
        btnBingo.setOnClickListener(view -> openBingo());

        TextView hyperlink = findViewById(R.id.hyperlink);
        SpannableString spannableString = new SpannableString("Click here for more information");


        // take the user to the Intaka website
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                //URL to open when the link is clicked
                String url = "https://intaka.co.za/";

                // Intent to open the URL in a web browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                // Start the web browser activity
                startActivity(intent);
            }
        };

        spannableString.setSpan(clickableSpan, 0, spannableString.length(), 0);

        // Make the TextView clickable
        hyperlink.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());

        // Set the SpannableString as the text for the TextView
        hyperlink.setText(spannableString);
    }

    /*
     * opens the map activity
     */
    public void openMap(){
        Intent intent = new Intent(this, mapActivity.class);
        startActivity(intent);
    }

    /*
     * opens the bingo activity
     */
    public void openBingo(){
        Intent intent = new Intent(this, bingoActivity.class);
        startActivity(intent);
    }

    /*
     * opens the plant ID activity
     */
    public void openPlantID(){
        Intent intent = new Intent(this,plantIDActivity.class);
        startActivity(intent);
    }

    /*
     * opens the bird list activity
     */
    public void openBirdList(){
        Intent intent = new Intent(this, listBirdsScrollingActivity.class);
        startActivity(intent);
    }

    /*
     * opens the plant list activity
     */
    public void openPlantList(){
        Intent intent = new Intent(this, listPlantsScrollingActivity.class);
        startActivity(intent);
    }

    /*
     * opens the quiz activity
     */
    public void openQuiz(){
        Intent intent = new Intent(this, quizActivity.class);
        startActivity(intent);
    }
}