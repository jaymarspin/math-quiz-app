package com.example.jaymardaligdig.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class home extends AppCompatActivity {
    Button play,scorer,settings,quit;
    public static int music;
    public static Intent musicer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        global.theme((ConstraintLayout) findViewById(R.id.conhome));
        music = R.raw.fatrat;
        musicer = new Intent(this,musicPlayer.class);
        if(com.example.jaymardaligdig.myapplication.settings.sound == true){
            startService(musicer);
        }

        scorer = findViewById(R.id.highScore);
        play = findViewById(R.id.play);
        settings = findViewById(R.id.settings);
        quit = findViewById(R.id.quit);

    }

    public void sample(View view){
        startActivity(new Intent(this, player_info.class));
    }

    public void score(View view){
        startActivity(new Intent(this, score.class));
    }

    public void settings(View view){
        startActivity(new Intent(this, settings.class));
    }

    public void instruct(View view){
        startActivity(new Intent(this, instruction.class));
    }

    public void quit(View view){
        stopService(new Intent(home.this,musicPlayer.class));
        finish();
        moveTaskToBack(true);
    }



    @Override
    public void onBackPressed() {

    }
}
