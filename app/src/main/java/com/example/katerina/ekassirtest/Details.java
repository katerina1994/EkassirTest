package com.example.katerina.ekassirtest;

import android.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Currency;

/**
 * Created by Katerina on 02.10.2017.
 */

public class Details extends Fragment {
    Order orderDetails;

    Details(Order order) {
        orderDetails = order;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details, null);
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yy hh:mm");
        ((TextView) view.findViewById(R.id.dateDetails)).setText(formatForDateNow.format(orderDetails.orderTime));
        ((TextView) view.findViewById(R.id.startAddressDetails)).setText(orderDetails.startAddress.address);
        ((TextView) view.findViewById(R.id.startAddressCityDetails)).setText(orderDetails.startAddress.city);
        Log.d("start address", orderDetails.startAddress.city);
        ((TextView) view.findViewById(R.id.endAddressDetails)).setText(orderDetails.endAdrdress.address);
        ((TextView) view.findViewById(R.id.endAddressCityDetails)).setText(orderDetails.startAddress.city);
        int money1 = orderDetails.price.amount/100;
        int money2 = orderDetails.price.amount % 100;
        String currencySymbol = Currency.getInstance(orderDetails.price.currency).getSymbol();
        ((TextView) view.findViewById(R.id.priceDetails)).setText(money1 + "." + money2 + " " + currencySymbol);
        new DownloadImageTask((ImageView) view.findViewById(R.id.imageVehicleDetails), this.getActivity())
                .execute("http://careers.ekassir.com/test/images/" + orderDetails.vehicle.photo);
        ((TextView) view.findViewById(R.id.nameDriverDetails)).setText(orderDetails.vehicle.driverName);
        ((TextView) view.findViewById(R.id.vehicleDetails)).setText(orderDetails.vehicle.modelName + ",  " + orderDetails.vehicle.regNumber);

        return view;
    }


}
