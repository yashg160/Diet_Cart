package com.example.android.vegancarttest;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class Choice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        StateListAnimator anim = AnimatorInflater.loadStateListAnimator(getApplicationContext(), R.animator.lift_on_touch);

        final CardView veggiesCardView = (CardView) findViewById(R.id.veggies_card_view);
        veggiesCardView.setStateListAnimator(anim);
        veggiesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Veg and Fruits", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        final CardView groceryCardView = (CardView) findViewById(R.id.groceryCard);
        groceryCardView.setStateListAnimator(anim);
        groceryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Groceries", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
