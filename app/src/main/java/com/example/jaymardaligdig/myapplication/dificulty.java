package com.example.jaymardaligdig.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class dificulty extends AppCompatActivity implements View.OnClickListener{
    TextView category,name;
    Button easy,average,dificult;
    public static String dificulties;
    public static int scorer;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dificulty);
        name = findViewById(R.id.nameLabel2);
        category = findViewById(R.id.category_title);
        global.theme((ConstraintLayout) findViewById(R.id.condif));
        name.setText("Player Name: "+player_info.playerName);
        category.setText(com.example.jaymardaligdig.myapplication.category.category.toUpperCase());
        easy = findViewById(R.id.easy);
        average = findViewById(R.id.average);
        dificult = findViewById(R.id.dificult);
        easy.setOnClickListener(this);
        average.setOnClickListener(this);
        dificult.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        scorer = 0;
       dificulties = ((Button)v).getText().toString();
       switch (com.example.jaymardaligdig.myapplication.category.category){
           case "g4":

               startActivity(new Intent(this,startGame.class));
               break;
           case "g5":
               startActivity(new Intent(this,startGame2.class));
               break;
           case "g6":

               startActivity(new Intent(this,startGame3.class));
               break;

       }
    }
}
