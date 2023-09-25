package com.example.icnature;

import static android.text.TextUtils.substring;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File; 
import java.io.IOException;  
import java.io.FileWriter;   
import androidx.appcompat.app.AppCompatActivity;
import com.example.icnature.R;
import org.json.JSONObject;


/*
 * plantIDActivity allows the user to take an image and send it to the API for recognition. 
 */
public class plantIDActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private static final String IMAGE1 = "app/src/main/res/download.jpg";

    private ImageView imageView;

    static String resulttext = "";

    Bitmap photo;
    Button recognitionButton;

    /*
     * onCreate handles button clicks
     * clicking photoButton opens the camera for the user to take an image and send to the API
     * The backbutton allows the user to return the main menu
     * The recognitionButton allows the user to proceed to view the plant details 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_idactivity);
        this.imageView = (ImageView)this.findViewById(R.id.plantImage);
        Button photoButton = (Button) this.findViewById(R.id.buttonSnap);
        recognitionButton = this.findViewById(R.id.camera_button);

        // return to the main menu on button click
        Button btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(view -> openMain());

        // open camera and send the result to the API
        photoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, CAMERA_REQUEST);
                }
            }
        });

        // sends the result of the API request to the ViewPlant page and opens ViewPlant
        recognitionButton.setOnClickListener(view -> openViewPlant(resulttext));

    }

    /*
     * sends the result of the API request to the ViewPlant page as an intent
     * and opens ViewPlantActivity
     */
    public void openViewPlant(String pName){
        Intent intent = new Intent(this, plantViewActivity.class);
        intent.putExtra("keyPlantName", pName);
        startActivity(intent);
    }


    /*
     * sends the image to the API and recieves the response
     */
    private void  sendToAPI() {
        //creating a new ApiRequestTask object
        new ApiRequestTask() {

            //overriding the onPostExecute method to recieve the response and trim it down to only the scientific name
            @Override
            protected void onPostExecute(JSONObject result) {
                if (result != null) {
                    // Handling the JSON response
                    Log.d("plantIDActivity", "API response: " + result.toString());

                    //recieve the results and trim it down to just the scientific name
                    try {
                        // recieve the result
                        String value = result.getString("results"); 
                        resulttext = value; 

                        // removing excess
                        Log.d("plantIDActivity", "Value from JSON: " + value);
                        int strt = resulttext.indexOf("\"",resulttext.indexOf("Author")); //problem child
                        int en =  resulttext.indexOf("\"",resulttext.indexOf("Author")+9);

                        //the final result == scientific name
                        resulttext = substring(resulttext,strt+3,en);

                        // display the result as a popup
                        Context context = getApplicationContext();
                        CharSequence text = resulttext;
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);

                        //This one line is very helpful when we were conducting testing, it outputs the received name of the plant detected
                        //toast.show();

                        // allow the user to confirm
                        recognitionButton.setBackgroundResource(R.drawable.background_btn_choose);


                    } catch (Exception e) {
                        Log.e("plantIDActivity", "Error parsing JSON: " + e.getMessage());
                    }
                } else {
                    Log.e("plantIDActivity", "API request failed");
                }

            }

        }.execute(photo);

    }

    /*
     * overriding the onActvityResult method to set the imageView  to the photo taken 
     * and call the method to upload to the API 
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            photo = (Bitmap) extras.get("data");
            imageView.setImageBitmap(photo);
            sendToAPI();

        }
    }

    /*
     * opens the main menu
     */
    public void openMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}