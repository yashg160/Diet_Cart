package com.example.android.vegancarttest;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class Choice extends AppCompatActivity {

    private int vegFlag=0;
    private int grocFlag=0;
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
                if(vegFlag==0){
                    veggiesCardView.setCardElevation(40F);
                    vegFlag=1;
                }
                else{
                    veggiesCardView.setCardElevation(8F);
                    vegFlag=0;
                }
            }
        });

        final CardView groceryCardView = (CardView) findViewById(R.id.grocery_card_view);
        groceryCardView.setStateListAnimator(anim);
        groceryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grocFlag==0){
                    groceryCardView.setCardElevation(40F);
                    grocFlag=1;
                }
                else{
                    groceryCardView.setCardElevation(8F);
                    grocFlag=0;
                }
            }
        });
    }
}
