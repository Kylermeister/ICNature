package com.example.icnature;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.icnature.databinding.ActivityListPlantsScrollingBinding;


/*
 * creates a scrollable list that displays all the plants.
 * The images are clickable, they will take the user to the plantView activity 
 * where they can see information on the plant
 */
public class listPlantsScrollingActivity extends AppCompatActivity {

    private ActivityListPlantsScrollingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListPlantsScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //return to mainmenu
        Button btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(view -> openMain());

         //search for a plant
        EditText SearchText = (EditText) findViewById(R.id.SearchText);
        Button Search = (Button) findViewById(R.id.Search);
        Search.setOnClickListener(view -> openViewPlant(String.valueOf(SearchText.getText())));

        //make the images clickable and pass the scientific name on click to the openViewPlant method
        String n = "Protea cynaroides";
        ImageView KingProtea = (ImageView) findViewById(R.id.imgKingProtea);
        KingProtea.setOnClickListener(view -> openViewPlant(n));

        ImageView WildAsparagus = (ImageView) findViewById(R.id.imgWildAsparagus);
        WildAsparagus.setOnClickListener(view -> openViewPlant("Asparagus aethiopicus"));

        ImageView HottentotsFig = (ImageView) findViewById(R.id.imgHottentotsFig);
        HottentotsFig.setOnClickListener(view -> openViewPlant("Carpobrotus edulis"));

        ImageView TortoiseBerry = (ImageView) findViewById(R.id.imgTortoiseBerry);
        TortoiseBerry.setOnClickListener(view -> openViewPlant("Muraltia spinosa"));

        ImageView GlossyCrowberry = (ImageView) findViewById(R.id.imgGlossyCrowberry);
        GlossyCrowberry.setOnClickListener(view -> openViewPlant("Searsia lucida"));
    }

    /*
     * on the plantViewActivity sending the plant's scientific name as an intent
     */
    public void openViewPlant(String pName){
        Intent intent = new Intent(this, plantViewActivity.class);
        intent.putExtra("keyPlantName", pName);
        startActivity(intent);
    }

    /*
     * return to the main menu
     */
    public void openMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}