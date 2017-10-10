package com.example.katerina.ekassirtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    MyListViewAdapter myListViewAdapter;
    List<Order> orders;
    ListView listVMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(null, new Runnable() {
            @Override
            public void run() {
                orders = ApiHelper.getOrders();
                showOrders(orders);

            }
        }).start();


    }

    private void showOrders(final List<Order> orders) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listVMain = (ListView) findViewById(R.id.listVMain);
                myListViewAdapter = new MyListViewAdapter(MainActivity.this, orders);
                listVMain.setAdapter(myListViewAdapter);
                for (Order order : orders){

                }


            }
        });

    }
}
