package com.example.android.vegancarttest;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DistributionActivity extends AppCompatActivity {

    private String TAG = "Firestore";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<DistributionCenter> centersList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribution);

        db.collection("distribution_centers")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        centersList = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                DistributionCenter dc = document.toObject(DistributionCenter.class);
                                centersList.add(dc);
                                Log.i(TAG, document.getId() + " => " + document.getData());
                            }

                            Log.i(TAG, centersList.toString());
                            ListView listView = findViewById(R.id.center_list);
                            ListAdapter listAdapter = new ListAdapter(getApplicationContext(), centersList);
                            listView.setAdapter(listAdapter);

                        } else {
                            Log.i(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        listView = findViewById(R.id.center_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Toast.makeText(getApplicationContext(),"You can buy apples, chillies, ladyfingers and papayas", Toast.LENGTH_LONG).show();
                }
                else if(position==1){
                    Toast.makeText(getApplicationContext(), "You can buy bananas, onions, potatoes, tomatoes", Toast.LENGTH_LONG).show();

                }
                else if(position==2){
                    Toast.makeText(getApplicationContext(), "You can buy apples, cabbages, oranges and turnips", Toast.LENGTH_LONG).show();
                }
                else if(position==3){
                    Toast.makeText(getApplicationContext(), "You can buy apples, oranges, potatoes, tomatoes", Toast.LENGTH_LONG).show();
                }
            }
        });
        }

}
