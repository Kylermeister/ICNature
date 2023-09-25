package com.example.icnature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;


/*
 * allows the user to blackout images in order to immitate a classic bingo game
 */
public class bingoActivity extends AppCompatActivity{

    private ImageView Image1,Image2,Image3,Image4,Image5,Image6,Image7,Image8,Image9;


    /*
     * enables the imageViews to be ready to be blacked out
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingo);

        Button btnBack = (Button) findViewById(R.id.buttonBack);

        // ready the images to be blacked out
        this.Image1 = (ImageView)this.findViewById(R.id.imageView1);
        Image1.setOnClickListener(view -> clr(Image1));

        this.Image2 = (ImageView)this.findViewById(R.id.imageView2);
        Image2.setOnClickListener(view -> clr(Image2));

        this.Image3 = (ImageView)this.findViewById(R.id.imageView3);
        Image3.setOnClickListener(view -> clr(Image3));

        this.Image4 = (ImageView)this.findViewById(R.id.imageView4);
        Image4.setOnClickListener(view -> clr(Image4));

        this.Image5 = (ImageView)this.findViewById(R.id.imageView5);
        Image5.setOnClickListener(view -> clr(Image5));

        this.Image6 = (ImageView)this.findViewById(R.id.imageView6);
        Image6.setOnClickListener(view -> clr(Image6));

        this.Image7 = (ImageView)this.findViewById(R.id.imageView7);
        Image7.setOnClickListener(view -> clr(Image7));

        this.Image8 = (ImageView)this.findViewById(R.id.imageView8);
        Image8.setOnClickListener(view -> clr(Image8));

        this.Image9 = (ImageView)this.findViewById(R.id.imageView9);
        Image9.setOnClickListener(view -> clr(Image9));

        // return to main menu
        btnBack.setOnClickListener(view -> openMain());
        
    }

    /*
     * return to the main menu
     */   
    public void openMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    /*
     * set the image to greyscale (black & white)
     */
    private void clr(ImageView name) {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        name.setColorFilter(new ColorMatrixColorFilter(matrix));
    }
}
