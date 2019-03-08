package com.example.jaymardaligdig.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class player_info extends AppCompatActivity {
    Button submit;
    EditText name;
    database database;
    public static String playerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);
        global.theme((ConstraintLayout) findViewById(R.id.coninfo));
        submit = findViewById(R.id.submit);
        name = findViewById(R.id.name);
    }

    public void addName(View view){
        database = new database(this);
        String val = name.getText().toString().trim();
        boolean stash = database.addName(val);
        if(stash == true){
            playerName = val;
            startActivity(new Intent(this,category.class));
        }
    }
}
