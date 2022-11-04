package com.example.weatherapp.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("weather?&appid=ba62d7ccd99b9d834fa76dfdeaca7ddf&units=metric")
    Call<Example> GetWeatherData(@Query("q") String name);

}
