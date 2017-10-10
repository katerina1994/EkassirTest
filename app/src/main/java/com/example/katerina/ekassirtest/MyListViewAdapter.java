package com.example.katerina.ekassirtest;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.List;

/**
 * Created by Katerina on 02.10.2017.
 */

public class MyListViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater lInflater;
    List<Order> ordersList;

    MyListViewAdapter (Context context, List<Order> orders) {
        this.context = context;
        ordersList = orders;
        lInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ordersList.size();
    }

    @Override
    public Object getItem(int position) {
        return ordersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item_order, parent, false);
        }

        final Order order = getOrder(position);
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yy hh:mm");
        ((TextView) view.findViewById(R.id.date)).setText(formatForDateNow.format(order.orderTime));
        ((TextView) view.findViewById(R.id.startAddress)).setText(order.startAddress.address);
        ((TextView) view.findViewById(R.id.startAddressCity)).setText(order.startAddress.city);
        Log.d("start address", order.startAddress.city);
        ((TextView) view.findViewById(R.id.endAddress)).setText(order.endAdrdress.address);
        ((TextView) view.findViewById(R.id.endAddressCity)).setText(order.startAddress.city);
        int money1 = order.price.amount / 100;
        int money2 = order.price.amount % 100;
        String currencySymbol = Currency.getInstance(order.price.currency).getSymbol();
        ((TextView) view.findViewById(R.id.price)).setText(money1 + "." + money2 + " " + currencySymbol);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutItem);
        linearLayout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Fragment fragment = new Details(order);
                FragmentTransaction transaction = ((Activity) context).getFragmentManager().beginTransaction();
                transaction.replace(R.id.details_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });
        return view;
    }
    // заказ по позиции
    Order getOrder(int position) {
        return ((Order) getItem(position));
        }


}
