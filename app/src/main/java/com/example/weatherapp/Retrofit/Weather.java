package com.example.weatherapp.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("description")
    String description;
    @SerializedName("icon")
    String icon;
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
