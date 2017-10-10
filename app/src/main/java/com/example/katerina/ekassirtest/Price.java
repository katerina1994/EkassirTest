package com.example.katerina.ekassirtest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Katerina on 30.09.2017.
 */
public class Price {

    int amount;
    String currency;

    public Price(JSONObject jsonPrice) throws JSONException {
        this.amount = jsonPrice.getInt("amount");
        this.currency = jsonPrice.getString("currency");

    }
}
