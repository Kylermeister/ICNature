package com.example.icnature;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.File;
import java.io.IOException;

/*
 * ApiRequestTask handles sending the photo in bitmap format to the plantnet API 
 * The API will attempt to recognize the plant
 *  The class recieves the API output
 */
public class ApiRequestTask extends AsyncTask<Bitmap, Void, JSONObject> {
    private static final String API_URL = "https://my-api.plantnet.org/v2/identify/all?include-related-images=false&no-reject=false&lang=en&type=legacy&api-key=2b109aGdvmSD27ImkdofSfk9te";
    
    /*
     * doInBackground handles connecting to the API, sending the image and then returning the response.
     * 
     * Returns -> JSONObject jsonResponse - result of the image recognition
     */
    @Override
    protected JSONObject doInBackground(Bitmap... bitmaps) {
        try {
            URL url = new URL(API_URL);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Setting up the request headers
            String boundary = "Boundary-" + System.currentTimeMillis();
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            // Converting the bitmap to a byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmaps[0].compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Writing the image data to the request body
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes("--" + boundary + "\r\n");
            outputStream.writeBytes("Content-Disposition: form-data; name=\"images\"; filename=\"image.jpg\"\r\n");
            outputStream.writeBytes("Content-Type: image/jpeg\r\n\r\n");
            outputStream.write(imageBytes);
            outputStream.writeBytes("\r\n");
            outputStream.writeBytes("--" + boundary + "--\r\n");
            outputStream.flush();
            outputStream.close();

            // Read the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Close the connection
            connection.disconnect();

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Return the parsed JSON object
            return jsonResponse;

        } catch (Exception e) {
            Log.e("ApiRequestTask", "Error making API request: " + e.getMessage());
            return null;
        }
    }

    /*
     * checks the output if to see if there was an error and sends the output of the API request
     * 
     * param -> JSONOnbject result - the API output/result
     */
    @Override
    protected void onPostExecute(JSONObject result) {
        if (result != null) {
            // Handle the JSON response here
            Log.d("ApiRequestTask", "API response: " + result.toString());
            // You can process the JSON data further here
        } else {
            // Handle the error
            Log.e("ApiRequestTask", "API request failed");
        }
    }
}
