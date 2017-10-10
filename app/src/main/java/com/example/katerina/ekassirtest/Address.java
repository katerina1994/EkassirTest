package com.example.katerina.ekassirtest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Katerina on 30.09.2017.
 */
public class Address {
    String city;
    String address;

    public Address(JSONObject jsonAddress) throws JSONException {

        this.address = jsonAddress.getString("address");
        this.city = jsonAddress.getString("city");
    }
}
