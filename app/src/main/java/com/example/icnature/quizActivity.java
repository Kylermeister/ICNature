package com.example.icnature;

import static com.example.icnature.R.drawable.background_btn_incorrect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * a playable quiz for the user to play
 */
public class quizActivity extends AppCompatActivity {
    String[] questions;
    String[] options;
    String[] answers;

    TextView cpt_question , text_question;
    Button btn_choose1 , btn_choose2 , btn_choose3 , btn_choose4 , btn_next;


    int currentQuestion =  0  ;
    int scorePlayer =  0  ;
    boolean isclickBtn = false;
    String valueChoose = "";
    Button btn_click;

    private List<String[]> csvData = new ArrayList<>();

    /*
     * the game reads the questions from the database and displays them with their options and checks the user answer
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        cpt_question = findViewById(R.id.cpt_question);
        text_question = findViewById(R.id.text_question);

        btn_choose1 = findViewById(R.id.btn_choose1);
        btn_choose2 = findViewById(R.id.btn_choose2);
        btn_choose3 = findViewById(R.id.btn_choose3);
        btn_choose4 = findViewById(R.id.btn_choose4);
        btn_next = findViewById(R.id.btn_next);

        //return to main menu
        findViewById(R.id.image_back).setOnClickListener(a-> finish());

        //read the database, populate the question, options and answers arrays
        readCSVData();
        generateQuestions();

        // get the game ready for the first question
        prepareQuestion();

        //when the next button is clicked
        btn_next.setOnClickListener(
                view -> {

                    
                    if (isclickBtn){ //check if the button has been clicked AND an option is selected
                        isclickBtn = false; //reset it

                        // check if the answer is correct
                        if(!valueChoose.equals(answers[currentQuestion])){  //WRONG
                            //display that it is incorrect
                            Toast.makeText(quizActivity.this , "incorrect",Toast.LENGTH_SHORT).show();
                            btn_click.setBackgroundResource(background_btn_incorrect);

                        }else {// CORRECT

                            //display that it is incorrect
                            Toast.makeText(quizActivity.this , "correct",Toast.LENGTH_SHORT).show();
                            btn_click.setBackgroundResource(R.drawable.background_btn_correct);

                            //increase the score
                            scorePlayer++;
                        }

                        //display the next question of there is one
                        new Handler().postDelayed(() -> {
                            if(currentQuestion!=questions.length-1){
                                currentQuestion = currentQuestion + 1;
                                prepareQuestion(); //display question

                                //reset
                                valueChoose = "";
                                btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
                                btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
                                btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
                                btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);

                            }else { //NO NEXT QUESTION

                                //display the results page
                                Intent intent  = new Intent(this , resultActivity.class);
                                intent.putExtra("Result" , scorePlayer);
                                startActivity(intent);
                                finish();
                            }

                        },1000);

                    }else { // IF THE USER DID NOT MAKE A CHOICE
                        Toast.makeText(quizActivity.this ,  "You must choose one",Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

    /*
     * fetch the next question and its batch of options
     */
    void prepareQuestion(){
        cpt_question.setText((currentQuestion+1) + "/" + questions.length);
        text_question.setText(questions[currentQuestion]);

        //options are stored in blocks of 4 therefore the index can be used to split them between questions
        btn_choose1.setText(options[4 * currentQuestion]);
        btn_choose2.setText(options[4 * currentQuestion+1]);
        btn_choose3.setText(options[4 * currentQuestion+2]);
        btn_choose4.setText(options[4 * currentQuestion+3]);

    }

    /*
     * the user clicked
     */
    public void ClickChoose(View view) {
        btn_click = (Button)view;

        if (isclickBtn) {
            btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);
        }
        chooseBtn();


    }

    /*
     * handle the user choice and set the button to clicked
     */
    void chooseBtn(){
        btn_click.setBackgroundResource(R.drawable.background_btn_choose_color);
        //btn_click.setBackgroundCo;
        isclickBtn = true;
        valueChoose = btn_click.getText().toString();
    }

    /*
     * reads the CSV file and populates the list
     */
    private void readCSVData() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.quiz);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVReader csvReader = new CSVReader(inputStreamReader);
            String[] nextLine;

            while ((nextLine = csvReader.readNext()) != null) {
                // Store the CSV data in the list
                csvData.add(nextLine);
            }

            csvReader.close();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }


    /*
     * seperates the questions, options and answers into their respective arrays
     */
    private void generateQuestions() {
        for (String[] rowData : csvData) {

            String Question = rowData[0];
            String option1 = rowData[1];
            String option2 = rowData[2];
            String option3 = rowData[3];
            String option4 = rowData[4];
            String Answer = rowData[5];

            // add the data to their arrays
            if (questions != null) {
                questions = Arrays.copyOf(questions, questions.length + 1);
                questions[questions.length - 1] = Question;

                options = Arrays.copyOf(options, options.length + 4);
                options[options.length - 1] = option4;
                options[options.length - 2] = option3;
                options[options.length - 3] = option2;
                options[options.length - 4] = option1;


                answers = Arrays.copyOf(answers, answers.length + 1);
                answers[answers.length - 1] = Answer;
            }
            else{ //throw away the first iteration as it is blank
                questions = new String[1];
                questions[0] = Question;

                options = new String[4];
                options[0] = option1;
                options[1] = option2;
                options[2] = option3;
                options[3] = option4;

                answers = new String[1];
                answers[0] = Answer;
            }

        }

    }
}