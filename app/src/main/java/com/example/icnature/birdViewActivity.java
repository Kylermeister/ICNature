package com.example.icnature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import Data.Bird;
import Data.Plant;


/*
 * displays the information and image of the selected bird
 */
public class birdViewActivity extends AppCompatActivity {
    String birdName;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String[]> csvData = new ArrayList<>();

    /*
     * populates the imageView and textView with the information of the selected bird
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_view);

        Intent intent = getIntent();

        // return to main menu
        Button btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(view -> openMain());

        if (intent != null) {

            //recieve the bird name from the intent
            String receivedData = intent.getStringExtra("keyBirdName");

            if (receivedData != null) {
                birdName = receivedData;
                listView = findViewById(R.id.birdListView);
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

                //read the database and populate the listView with the found information
                readCSVData();
                displayDataInListView();

                // display additional information when the more button is clicked
                Button btnMore = (Button) findViewById(R.id.buttonMore);
                btnMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showMore();
                    }
                });

                //set the image to the correct bird depending on the name recieved from the intent
                ImageView bIV = (ImageView) findViewById(R.id.birdImage);
                switch(birdName) {
                    case "Alopochen aegyptiacus":
                        bIV.setImageResource(R.mipmap.egyptian_goose);
                        break;
                    case "Egyptian goose":
                        bIV.setImageResource(R.mipmap.egyptian_goose);
                        break;
                    case "Acrocephalus baeticatus":
                        bIV.setImageResource(R.mipmap.african_reed_warbler);
                        break;
                    case "African reed warbler":
                        bIV.setImageResource(R.mipmap.african_reed_warbler);
                        break;
                    case "Falco rupicolus":
                        bIV.setImageResource(R.mipmap.rock_kestrel);
                        break;
                    case "Rock kestrel":
                        bIV.setImageResource(R.mipmap.rock_kestrel);
                        break;
                    case "Streptopelia capicola":
                        bIV.setImageResource(R.mipmap.cape_turtle_dove);
                        break;
                    case "Cape turtle dove":
                        bIV.setImageResource(R.mipmap.cape_turtle_dove);
                        break;
                    case "Aquila nipalensis":
                        bIV.setImageResource(R.mipmap.steppe_eagle);
                        break;
                    case "Steppe eagle":
                        bIV.setImageResource(R.mipmap.steppe_eagle);
                        break;
                    default:
                        Context context = getApplicationContext();
                        CharSequence text = "Plant not found";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        openMain();
                        break;
                }

            }
        }
    }

    /*
     * read the CSV database and store it in the csvData list
     */
    private void readCSVData() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.birds);
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
     * search the bird list for the correct bird and display its information
     */
    private void displayDataInListView() {

        //iterate through the list
        for (String[] rowData : csvData) {
            String CommonName = rowData[0];
            String ScientificName = rowData[1];
            String Family = rowData[2];
            String ShortDescription = rowData[3];

            //check if the current bird matches the bird  that needs to be displayed
            if ((ScientificName.equalsIgnoreCase(birdName))||(CommonName.equalsIgnoreCase(birdName))) {
                //Formatted string to display in the ListView
                String displayText = "Common Name: " + CommonName + "\nScientific Name: " + ScientificName + "\nFamily: " + Family + "\nDescription: " + ShortDescription;
                adapter.add(displayText);
            }


        }

        // Set the adapter for the ListView
        listView.setAdapter(adapter);
    }

    /*
     * displays the information for the bird + the additional information
     */
    private void showMore() {
        adapter.clear(); // Clear the previous data

        //iterate through the list
        for (String[] rowData : csvData) {
            String CommonName = rowData[0];
            String ScientificName = rowData[1];
            String Family = rowData[2];
            String ShortDescription = rowData[3];
            String LongDescription = rowData[4];

            //check if the current bird matches the bird that needs to be displayed
            if ((ScientificName.equalsIgnoreCase(birdName))||(CommonName.equalsIgnoreCase(birdName))) {
                // Formatted string to display in the ListView
                String displayText = "Common Name: " + CommonName + "\nScientific Name: " + ScientificName + "\nFamily: " + Family + "\nDescription: " + ShortDescription + LongDescription;
                adapter.add(displayText);
            }


        }

        // Set the adapter for the ListView
        listView.setAdapter(adapter);
    }

    /*
     * return to the main menu
     */
    public void openMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}