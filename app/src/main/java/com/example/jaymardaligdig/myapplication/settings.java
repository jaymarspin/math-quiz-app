package com.example.jaymardaligdig.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.glomadrian.materialanimatedswitch.MaterialAnimatedSwitch;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class settings extends AppCompatActivity {

    Button button;
    MaterialAnimatedSwitch materialAnimatedSwitch;
    Spinner spinner;
    String theme;
    public static boolean sound = true;
    public static String backTheme = "Gradient Red";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        button = findViewById(R.id.save_settings);



        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Gradient Red");
        spinnerArray.add("Dark Theme");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.theme);
        sItems.setAdapter(adapter);


        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if(i == 1){
                    backTheme = "Dark Theme";
               }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        materialAnimatedSwitch = findViewById(R.id.pin);
        materialAnimatedSwitch.setOnCheckedChangeListener(new MaterialAnimatedSwitch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean b) {
                if(materialAnimatedSwitch.isChecked()){
                    sound = true;
                }else{
                    stopService(new Intent(settings.this,musicPlayer.class));
                    sound = false;
                }
            }
        });
        materialAnimatedSwitch.isChecked();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(settings.this,home.class));
            }
        });


    }
}
