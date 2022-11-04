package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.weatherapp.Retrofit.ApiClient;
import com.example.weatherapp.Retrofit.ApiInterface;
import com.example.weatherapp.Retrofit.Example;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout homeRL;
    private ProgressBar loadingPB;
    private TextView cityNameTV,temperatureTV,conditionTV,Date,TodayDg,today;
    private TextInputEditText cityEdt;
    private ImageView icon1IV,icon2IV,icon3IV,icon4IV,searchIV;
    String date,txt;
    Intent intent;
    public static final String CITY="city";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeRL= findViewById(R.id.idRLHome);
        cityNameTV= findViewById(R.id.idTVCityName);
        temperatureTV= findViewById(R.id.idTVTemperature);
        conditionTV= findViewById(R.id.idTVCondition);
        cityEdt= findViewById(R.id.idEdtCity);
        icon1IV= findViewById(R.id.Icon1);
        icon2IV= findViewById(R.id.Icon2);
        icon3IV= findViewById(R.id.Icon3);
        icon4IV= findViewById(R.id.Icon4);
        searchIV= findViewById(R.id.idIVSearch);
        Date=findViewById(R.id.idTVDate);
        TodayDg=findViewById(R.id.TVTodayDg);
        Date d = new Date();
        date=(d.getDay()+1)+"/"+(d.getMonth()+1)+"/"+(d.getYear()+1900);
        Date.setText(date);
        today=findViewById(R.id.TVToday);
        getWeatherData(cityNameTV.getText().toString().trim());
        
        searchIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt=cityEdt.getText().toString().trim();
                getWeatherData(txt);
                cityNameTV.setText(cityEdt.getText().toString().trim()) ;


            }
        });

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,Details.class);
                intent.putExtra(CITY,cityNameTV.getText());
                startActivity(intent);

            }
        });




    }
    private void getWeatherData(String name){
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call <Example> call =apiInterface.GetWeatherData(name);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                temperatureTV.setText(response.body().getMain().getTemp()+"°");
                TodayDg.setText(response.body().getMain().getTemp()+"°");



//                conditionTV.setText(response.body().getWeather().getDescription());
//                icon1IV.setImage(response.body().getWeather().getIcon());

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

}