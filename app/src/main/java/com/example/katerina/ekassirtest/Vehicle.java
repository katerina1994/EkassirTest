package com.example.katerina.ekassirtest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Katerina on 30.09.2017.
 */
public class Vehicle {
    String regNumber;
    String modelName;
    String photo;
    String driverName;

    public Vehicle(JSONObject jsonVehicle) throws JSONException {
        this.regNumber = jsonVehicle.getString("regNumber");
        this.modelName = jsonVehicle.getString("modelName");
        this.photo = jsonVehicle.getString("photo");
        this.driverName = jsonVehicle.getString("driverName");
    }
}
