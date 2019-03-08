package com.example.jaymardaligdig.myapplication;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class category extends AppCompatActivity implements View.OnClickListener
{
    TextView label;
    Button g4,g5,g6;
    public static String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        label = findViewById(R.id.nameLabel);
        global.theme((ConstraintLayout) findViewById(R.id.concat));
        label.setText("Player Name:  "+player_info.playerName);
        g4 = findViewById(R.id.g4);
        g5 = findViewById(R.id.g5);
        g6 = findViewById(R.id.g6);
        g4.setOnClickListener(this);
        g5.setOnClickListener(this);
        g6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.g4:
                category = "g4";
                break;
            case R.id.g5:
                category = "g5";
                break;
            case R.id.g6:
                category = "g6";
                break;

        }
        startActivity(new Intent(this,dificulty.class));
    }
}
