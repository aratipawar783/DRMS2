package com.example.smartcropapp.marketprice;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcropapp.R;
import com.example.smartcropapp.marketprice.MarketAdapter;
import com.example.smartcropapp.marketprice.MarketApi;
import com.example.smartcropapp.marketprice.MarketResponse;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarketPriceActivity extends AppCompatActivity {

    private LineChart lineChart;
    private RecyclerView recyclerView;
    private MarketAdapter adapter;

    private static final String TAG = "MarketModule";
    private static final String BASE_URL = "https://marsapi.ams.usda.gov/services/v1.2/";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_price);

        lineChart = findViewById(R.id.lineChart);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MarketAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        fetchMarketData();
    }

    private void fetchMarketData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MarketApi api = retrofit.create(MarketApi.class);

        api.getMarketReports("EfRXA5cTyE8acfI0ndyNaDOzRigZkas29vkCNdDG").enqueue(new Callback<MarketResponse>() {
            @Override
            public void onResponse(Call<MarketResponse> call, Response<MarketResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MarketResponse.Report> reports = response.body().reports;
                    Log.d(TAG, "Fetched " + reports.size() + " reports");

                    adapter.updateData(reports);
                    showChart(reports);
                } else {
                    Toast.makeText(MarketPriceActivity.this, "No data received", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Response unsuccessful or empty");
                }
            }

            @Override
            public void onFailure(Call<MarketResponse> call, Throwable t) {
                Toast.makeText(MarketPriceActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, "API failure", t);
            }
        });
    }

    private void showChart(List<MarketResponse.Report> reports) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < reports.size(); i++) {
            entries.add(new Entry(i, (float) reports.get(i).price));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Market Prices");
        dataSet.setColor(getResources().getColor(R.color.purple_500));
        dataSet.setValueTextColor(getResources().getColor(R.color.black));
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }
}
