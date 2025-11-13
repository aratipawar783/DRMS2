package com.example.smartcropapp.marketprice;

// MarketResponse.java
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MarketResponse {
    @SerializedName("reports")
    public List<Report> reports;

    public static class Report {
        @SerializedName("title")
        public String title;

        @SerializedName("market")
        public String market;

        @SerializedName("price")
        public double price;

        @SerializedName("date")
        public String date;
    }
}
