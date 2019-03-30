package com.example.android.vegancarttest;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Choice extends AppCompatActivity {

    private CheckBox fruitsCheck;
    private CheckBox vegetablesCheck;
    private int isFruitsFlag;
    private int isVegetablesFlag;
    private Button proceedButtonChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        fruitsCheck = (CheckBox) findViewById(R.id.fruits_check_box);
        vegetablesCheck = (CheckBox) findViewById(R.id.vegetables_check_box);

        final CardView fruitsCardView = (CardView) findViewById(R.id.fruits_card_view);

        fruitsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fruitsCheck.toggle();
                if(isFruitsFlag == 1) isFruitsFlag = 0;
                else isFruitsFlag = 1;
            }
        });

        final CardView veggiesCardView = (CardView) findViewById(R.id.veggies_card_view);
        veggiesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vegetablesCheck.toggle();
                if(isVegetablesFlag == 1) isVegetablesFlag = 0;
                else isVegetablesFlag = 1;
            }
        });

        proceedButtonChoice = findViewById(R.id.proceed_button_choice);
        proceedButtonChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFruitsFlag==0 && isVegetablesFlag==0){
                    Toast.makeText(getApplicationContext(), "Please select at least one option", Toast.LENGTH_LONG).show();
                }
                else {
                    startActivity(new Intent(Choice.this, DistributionActivity.class).setFlags(isFruitsFlag).setFlags(isVegetablesFlag));

                }
            }
        });



    }
}
