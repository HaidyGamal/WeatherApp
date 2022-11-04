package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.weatherapp.Retrofit.ApiClient;
import com.example.weatherapp.Retrofit.ApiInterface;
import com.example.weatherapp.Retrofit.Example;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details extends AppCompatActivity {
    TextView cityName,dateTV,tempTV,temp_minTV,temp_maxTV,decsTV,humTV,windTV,feels_likeTV;
    Intent incoming;
    ConstraintLayout layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        cityName=findViewById(R.id.city);
        dateTV=findViewById(R.id.date);
        tempTV=findViewById(R.id.temp);
        temp_minTV=findViewById(R.id.min);
        temp_maxTV=findViewById(R.id.max);
        decsTV=findViewById(R.id.description);
        humTV=findViewById(R.id.humidity);
        windTV=findViewById(R.id.wind);
        feels_likeTV=findViewById(R.id.feels);
        layout=findViewById(R.id.L);
        Date currentTime = Calendar.getInstance().getTime();


        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);



        incoming=getIntent();
        cityName.setText(incoming.getStringExtra(MainActivity.CITY));
        dateTV.setText(dayOfTheWeek);

        getWeatherData(cityName.getText().toString().trim());



    }
    private void getWeatherData(String name){
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<Example> call =apiInterface.GetWeatherData(name);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                temp_minTV.setText(response.body().getMain().getTemp_min()+"°");
                temp_maxTV.setText(response.body().getMain().getTemp_max()+"°");
                humTV.setText(response.body().getMain().getHumidity()+" %");
                windTV.setText(response.body().getMain().getPressure()+" mm");
                feels_likeTV.setText(response.body().getMain().getFeels_like()+"°");
                tempTV.setText(response.body().getMain().getTemp()+"°");
                decsTV.setText("sunny");


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}
