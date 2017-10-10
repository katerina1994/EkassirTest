package com.example.katerina.ekassirtest;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ApiHelper {
    private static final String serverURL = "http://careers.ekassir.com/test";
    private static final String ordersParam = "/orders.json";

    static List<Order> getOrders() {
        String url = serverURL + ordersParam;
        String jsonResult = ProcessRequest(url);
        Log.d("jsonResult", String.valueOf(jsonResult));
        List<Order> orders = new ArrayList<>();
        if (jsonResult == "") {
            System.out.println(jsonResult);
            return orders;
        } else {

            try {
                JSONArray jsonOrders = new JSONArray(jsonResult);
                int objectLength = jsonOrders.length();
                for (int i = 0; i < objectLength; i++) {
                    orders.add(new Order(jsonOrders.getJSONObject(i)));
                }
            } catch (JSONException | ParseException e) {
                e.printStackTrace();
            }

            Collections.sort(orders, new Comparator<Order>() {
                @Override
                public int compare(Order order1, Order order2) {
                    return order1.orderTime.compareTo(order2.orderTime);
                }
            });

            return orders;

        }
    }


    @NonNull
    private static String ProcessRequest(String url) {
        HttpURLConnection connection = null;
        StringBuilder response = new StringBuilder();
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response.toString();
    }

}
