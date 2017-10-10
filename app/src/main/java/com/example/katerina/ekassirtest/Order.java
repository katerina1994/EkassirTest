package com.example.katerina.ekassirtest;

//import android.annotation.SuppressLint;

import android.annotation.SuppressLint;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Katerina on 30.09.2017.
 */

public class Order {

    @SuppressLint("SimpleDateFormat")
    private final static DateFormat expectedDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    int id;
    Address startAddress;
    Address endAdrdress;
    Price price;
    Date orderTime;
    Vehicle vehicle;

    public Order(JSONObject json) throws JSONException, ParseException {

        this.id = json.getInt("id");
        this.endAdrdress = new Address(json.getJSONObject("endAddress"));
        this.startAddress = new Address(json.getJSONObject("startAddress"));
        this.price = new Price(json.getJSONObject("price"));
        this.vehicle = new Vehicle(json.getJSONObject("vehicle"));
        this.orderTime = expectedDateFormat.parse(json.getString("orderTime"));

    }

}
