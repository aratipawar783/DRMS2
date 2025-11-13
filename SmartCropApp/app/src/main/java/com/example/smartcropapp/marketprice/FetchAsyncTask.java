package com.example.smartcropapp.marketprice;

import android.os.AsyncTask;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class FetchAsyncTask extends AsyncTask<Void, Void, String> {

    private static final String API_KEY = "YOUR_API_KEY_HERE";
    private static final String API_URL = "https://apps.fas.usda.gov/opendata/api/endpoint"; // Replace with actual endpoint

    @Override
    protected String doInBackground(Void... voids) {
        try {
            OkHttpClient client = new OkHttpClient();

            // Build request with API key in header or query param as required
            Request request = new Request.Builder()
                    .url(API_URL + "?api_key=" + API_KEY)  // or add header .addHeader("Authorization", API_KEY)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String jsonData) {
        if (jsonData != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonData);
                // Parse JSON data here according to USDA API response structure
                // For example, extract commodity prices, dates, etc.
                // Update UI or notify user with the parsed data

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Handle error: no data received
        }
    }
}

