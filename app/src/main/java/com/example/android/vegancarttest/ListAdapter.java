package com.example.android.vegancarttest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ListAdapter extends ArrayAdapter<DistributionCenter> {
    private Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
    private List<Address> addresses;
    private String TAG = "Firestore";

    public ListAdapter(Context context, List<DistributionCenter> list){
        super(context, 0, list);
    }


    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.distribution_list_item, parent, false);

        TextView nameTextView = convertView.findViewById(R.id.distribution_name);
        TextView locationTextView = convertView.findViewById(R.id.distribution_location);

        DistributionCenter dc = getItem(position);
        try {
            addresses = geocoder.getFromLocation(dc.getLatitude(), dc.getLongitude(), 1);
        } catch (Exception e) {
            Log.i(TAG, "ListAdapter --> getView", e);
        }

        nameTextView.setText(dc.getName());
        locationTextView.setText(addresses.get(0).getSubAdminArea());
        Log.i(TAG, addresses.toString());
        return convertView;
    }
}
