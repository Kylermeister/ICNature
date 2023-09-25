package com.example.icnature;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * displays the information and image of the plant
 */
public class plantViewActivity extends AppCompatActivity {

    String plantName;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String[]> csvData = new ArrayList<>();


    /*
     * populates the imageView and textView with the information of the plant
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_view);
        Intent intent = getIntent();

        // return to main menu
        Button btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(view -> openMain());

        if (intent != null) {

            //recieve the plant name from the intent
            String receivedData = intent.getStringExtra("keyPlantName");

            if (receivedData != null) {

                plantName = receivedData;
                listView = findViewById(R.id.plantListView);
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

                //read the database and populate the listView with the found information
                readCSVData();
                displayDataInListView();

                //set the image to the correct plant depending on the name recieved from the intent
                ImageView pIV = (ImageView) findViewById(R.id.plantImage);
                switch(plantName){
                    case "Protea cynaroides":
                        pIV.setImageResource(R.mipmap.proteacynaroides);
                        break;
                    case "King protea":
                        pIV.setImageResource(R.mipmap.proteacynaroides);
                        break;
                    case "Serruria florida":
                        pIV.setImageResource(R.mipmap.proteacynaroides);
                        break;
                    case "Asparagus racemosus":
                        pIV.setImageResource(R.mipmap.wildasparagus);
                        break;
                    case "Linospadix monostachyos":
                        pIV.setImageResource(R.mipmap.wildasparagus);
                        break;
                    case "Smilax walteri":
                        pIV.setImageResource(R.mipmap.wildasparagus);
                        break;
                    case "Aiphanes horrida":
                        pIV.setImageResource(R.mipmap.wildasparagus);
                        break;
                    case "Paullinia elegans":
                        pIV.setImageResource(R.mipmap.wildasparagus);
                        break;
                    case "Asparagus aethiopicus":
                        pIV.setImageResource(R.mipmap.wildasparagus);
                        break;
                    case "Wild asparagus":
                        pIV.setImageResource(R.mipmap.wildasparagus);
                        break;
                    case "Enchylaena tomentosa":
                        pIV.setImageResource(R.mipmap.wildasparagus);
                        break;
                    case "Carpobrotus edulis":
                        pIV.setImageResource(R.mipmap.hottentotsfig);
                        break;
                    case "Carpobrotus glaucescens":
                        pIV.setImageResource(R.mipmap.hottentotsfig);
                        break;
                    case "Hottentots fig":
                        pIV.setImageResource(R.mipmap.hottentotsfig);
                        break;
                    case "Tortoise berry":
                        pIV.setImageResource(R.mipmap.tortoiseberry);
                        break;
                    case "Erica canaliculata":
                        pIV.setImageResource(R.mipmap.tortoiseberry);
                        break;
                    case "Erica melanthera":
                        pIV.setImageResource(R.mipmap.tortoiseberry);
                        break;
                    case "Satyrium carneum":
                        pIV.setImageResource(R.mipmap.tortoiseberry);
                        break;
                    case "Muraltia spinosa":
                        pIV.setImageResource(R.mipmap.tortoiseberry);
                        break;
                    case "Searsia lucida":
                        pIV.setImageResource(R.mipmap.glossycrowberry);
                        break;
                    case "Glossy crowberry":
                        pIV.setImageResource(R.mipmap.glossycrowberry);
                        break;
                    case "Vaccinium floribundum":
                        pIV.setImageResource(R.mipmap.glossycrowberry);
                        break;
                    case "Empetrum nigrum":
                        pIV.setImageResource(R.mipmap.glossycrowberry);
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

                // display additional information when the more button is clicked
                Button btnMore = (Button) findViewById(R.id.buttonMore);
                btnMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showMore();
                    }
                });

            }
        }

    }

    /*
     * read the CSV database and store it in the csvData list
     */
    private void readCSVData() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.plants);
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
     * search the plant list for the correct plant and display its information
     */
    private void displayDataInListView() {

        //iterate through the list
        for (String[] rowData : csvData) {
            String CommonName = rowData[0];
            String ScientificName = rowData[1];
            String Family = rowData[2];
            String ShortDescription = rowData[3];
            String PossibleName = rowData[5];


            //check if the current plant matches the plant that needs to be displayed taking into account all possible names for the same plant
            if ((PossibleName.equalsIgnoreCase(plantName))||(CommonName.equalsIgnoreCase(plantName))) {
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
            String PossibleName = rowData[5];

            //check if the current plant matches the plant that needs to be displayed taking into account all possible names for the same plant
            if ((PossibleName.equalsIgnoreCase(plantName))||(CommonName.equalsIgnoreCase(plantName))) {
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