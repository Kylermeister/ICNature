package com.example.icnature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

public class birdIDActivity extends AppCompatActivity {

    private static final int pic_id = 123;

    Button camera_open_id;
    ImageView click_image_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_idactivity);

        Button btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(view -> openMain());

        Button btnSnap = (Button) findViewById(R.id.buttonSnap);
        btnSnap.setOnClickListener(view -> openViewBird());

        Button camera_open_id = findViewById(R.id.camera_button);
        ImageView click_image_id = findViewById(R.id.click_image);

        camera_open_id.setOnClickListener(v -> {   // invokes an intent to use the phone camera
            // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Start the activity with camera_intent, and request pic id
            startActivityForResult(camera_intent, pic_id);
        });
    }

    public void openMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void openViewBird(){
        Intent intent = new Intent(this, birdViewActivity.class);
        startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Match the request 'pic id with requestCode
        if (requestCode == pic_id) {
            // BitMap is data structure of image file which store the image in memory
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            // Set the image in imageview for display
            click_image_id.setImageBitmap(photo);
        }
    }
}