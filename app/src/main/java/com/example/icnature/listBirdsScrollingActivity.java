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

import com.example.icnature.databinding.ActivityListBirdsScrollingBinding;

public class listBirdsScrollingActivity extends AppCompatActivity {

private ActivityListBirdsScrollingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityListBirdsScrollingBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());


        Button btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(view -> openMain());
    }

    public void openMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}