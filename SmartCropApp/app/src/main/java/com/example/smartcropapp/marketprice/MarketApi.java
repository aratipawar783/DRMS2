package com.example.smartcropapp.marketprice;

// MarketApi.java
// MarketApi.java
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MarketApi {
    @GET("reports") // endpoint from USDA API
    Call<MarketResponse> getMarketReports(@Header("x-api-key") String apiKey);
}
