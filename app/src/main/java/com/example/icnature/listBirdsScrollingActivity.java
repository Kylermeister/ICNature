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

import com.example.icnature.databinding.ActivityListBirdsScrollingBinding;


/*
 * creates a scrollable list that displays all the birds.
 * The images are clickable, they will take the user to the viewBird activity 
 * where they can see information on the bird
 */
public class listBirdsScrollingActivity extends AppCompatActivity {

private ActivityListBirdsScrollingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityListBirdsScrollingBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

     //return to mainmenu
     Button btnBack = (Button) findViewById(R.id.buttonBack);
     btnBack.setOnClickListener(view -> openMain());
     
     //search for a bird
     EditText SearchText = (EditText) findViewById(R.id.SearchText);
     Button Search = (Button) findViewById(R.id.Search);
     Search.setOnClickListener(view -> openViewBird(String.valueOf(SearchText.getText())));

     //make the images clickable and pass the scientific name on click to the openViewBird method
     ImageView EgyptianGoose = (ImageView) findViewById(R.id.imgEgyptianGoose);
     EgyptianGoose.setOnClickListener(view -> openViewBird("Alopochen aegyptiacus"));

     ImageView AfricanReedWarbler = (ImageView) findViewById(R.id.imgAfricanReedWarbler);
     AfricanReedWarbler.setOnClickListener(view -> openViewBird("Acrocephalus baeticatus"));

     ImageView RockKestrels = (ImageView) findViewById(R.id.imgRockKestrels);
     RockKestrels.setOnClickListener(view -> openViewBird("Falco rupicolus"));

     ImageView CapeTurtleDove = (ImageView) findViewById(R.id.imgCapeTurtleDove);
     CapeTurtleDove.setOnClickListener(view -> openViewBird("Streptopelia capicola"));

     ImageView SteppeEagle = (ImageView) findViewById(R.id.imgSteppeEagle);
     SteppeEagle.setOnClickListener(view -> openViewBird("Aquila nipalensis"));

    }

    /*
     * on the birdViewActivity sending the bird's scientific name as an intent
     */
    public void openViewBird(String bName){
        Intent intent = new Intent(this, birdViewActivity.class);
        intent.putExtra("keyBirdName", bName);
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