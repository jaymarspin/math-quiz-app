package com.example.jaymardaligdig.myapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ankushgrover.hourglass.Hourglass;

import net.steamcrafted.materialiconlib.MaterialIconView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class startGame2 extends AppCompatActivity implements View.OnClickListener{
    TextView questionLabel,high_name,high_score,pages,your_score_number,timerr,caller;
    RadioButton option1,option2,option3,option4;
    RadioGroup radioGroup;
    Button done;
    int itemNo;

    g5 question;
    database data;
    private String answer;
    Dialog dialog;
    private int counter;

    MaterialIconView icon,icon2;
    private int time;
    private boolean call,fifty;


    Hourglass downTimer = null;
    Hourglass callTime = null;
    ImageView star;
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        stopService(new Intent(this,musicPlayer.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        stopService(home.musicer);

        call = true;
        fifty = true;
        counter = 1;
        question = new g5();
        global.theme((ConstraintLayout) findViewById(R.id.constart));


        dialog = new Dialog(this);
        //dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.done);
        data = new database(this);
        home.music = R.raw.relax;
        questionLabel = findViewById(R.id.question_label);
        pages = findViewById(R.id.pages);
        icon = findViewById(R.id.icon);
        icon2 = findViewById(R.id.icon2);
        radioGroup = findViewById(R.id.radioGroup);
        pages.setText("1 of "+question.question.length);
        high_name = dialog.findViewById(R.id.high_name);
        high_score = dialog.findViewById(R.id.high_score);
        your_score_number = dialog.findViewById(R.id.your_score_number);
        timerr = findViewById(R.id.timer);
        caller = findViewById(R.id.call);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        done = findViewById(R.id.next);
        done.setOnClickListener(this);

        star = dialog.findViewById(R.id.stars2);
        star.setVisibility(View.INVISIBLE);


        Toast.makeText(this,question.question.length+"",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(com.example.jaymardaligdig.myapplication.settings.sound == true){
                    startService(new Intent(startGame2.this,musicPlayer.class));
                }

            }
        },3000);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Toast.makeText(startGame2.this,"Thank you for playing",Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(startGame2.this, home.class));
                    }
                },1000);

            }
        });
        itemNo = 0;
        if(dificulty.dificulties.equals("EASY")){
            time = 80000;
            questionLabel.setText(question.question[itemNo]);
            option1.setText(question.answers[itemNo][0]);
            option2.setText(question.answers[itemNo][1]);
            option3.setText(question.answers[itemNo][2]);
            option4.setText(question.answers[itemNo][3]);
        }else if(dificulty.dificulties.equals("AVERAGE")){
            time = 50000;
            questionLabel.setText(question.question2[itemNo]);
            option1.setText(question.answers2[itemNo][0]);
            option2.setText(question.answers2[itemNo][1]);
            option3.setText(question.answers2[itemNo][2]);
            option4.setText(question.answers2[itemNo][3]);
        }else if(dificulty.dificulties.equals("DIFFICULT")){
            time = 30000;
            questionLabel.setText(question.question3[itemNo]);
            option1.setText(question.answers3[itemNo][0]);
            option2.setText(question.answers3[itemNo][1]);
            option3.setText(question.answers3[itemNo][2]);
            option4.setText(question.answers3[itemNo][3]);
        }

        downTimer = new Hourglass(time, 1000) {


            @Override
            public void onTimerTick(long timeRemaining) {
                // Update UI

                timerr.setText("seconds remaining: " + timeRemaining / 1000);
            }

            @Override
            public void onTimerFinish() {
                your_score_number.setText(""+dificulty.scorer);
                no_more(dificulty.scorer);


            }
        };
        downTimer.startTimer();
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(call == true){
                    downTimer.pauseTimer();
                    callTime = new Hourglass(60000, 1000) {
                        @Override
                        public void onTimerTick(long timeRemaining) {
                            caller.setText("Call a friend time remaining "+ timeRemaining / 1000);

                        }
                        @Override
                        public void onTimerFinish() {
                            caller.setText("");
                            downTimer.resumeTimer();
                        }
                    };
                    callTime.startTimer();
                    call = false;
                }else{
                    Toast.makeText(startGame2.this,"Call a friend can only be used once!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fifty == true){
                    option1.setClickable(false);
                    option2.setClickable(false);
                    option3.setClickable(false);
                    option4.setClickable(false);
                    option1.setTextColor(Color.LTGRAY);
                    option3.setTextColor(Color.LTGRAY);
                    option4.setTextColor(Color.LTGRAY);
                    option2.setTextColor(Color.LTGRAY);
                    if(dificulty.dificulties.equals("EASY")){
                        time = 80000;

                        sammy(question.correct[itemNo]);

                    }else if(dificulty.dificulties.equals("AVERAGE")){
                        time = 50000;
                        sammy(question.correct2[itemNo]);

                    }else if(dificulty.dificulties.equals("DIFFICULT")){
                        time = 30000;
                        sammy(question.correct3[itemNo]);

                    }
                    fifty = false;
                }else Toast.makeText(startGame2.this,"You can only use 50/50 once",Toast.LENGTH_SHORT).show();


            }
        });


    }

    public void sammy(String correct){
        int tmp = 5;
        if(option1.getText() == correct){
            option1.setClickable(true);
            option1.setTextColor(Color.BLACK);
            tmp = 0;
        }else if(option2.getText() == correct){
            option2.setClickable(true);
            option2.setTextColor(Color.BLACK);
            tmp = 1;
        }else if(option3.getText() == correct){
            option3.setClickable(true);
            option3.setTextColor(Color.BLACK);
            tmp = 2;
        }
        else if(option4.getText() == correct){
            option4.setClickable(true);
            option4.setTextColor(Color.BLACK);
            tmp = 3;
        }
        Random random = new Random();
        boolean give = false;
        int n = 0;
        while (give == false){
            n = random.nextInt(3);
            if(n != tmp) {
                give = true;
            }
        }
        switch (n){
            case 0:
                option1.setClickable(true);
                option1.setTextColor(Color.BLACK);
                break;
            case 1:
                option2.setClickable(true);
                option2.setTextColor(Color.BLACK);
                break;
            case 2:
                option3.setClickable(true);
                option3.setTextColor(Color.BLACK);
                break;
            case 3:
                option4.setClickable(true);
                option4.setTextColor(Color.BLACK);
                break;
        }


    }



    public int no_more(int value){
        data.addScore(value);
        String namer = "";
        Cursor dat = data.data("SELECT * FROM scores WHERE category = 'g5' ORDER BY remarks ASC");
        int final_score = 0;
        while (dat.moveToNext()){
            namer = dat.getString(5);
            final_score = dat.getInt(1);
        }
        high_name.setText(namer);
        high_score.setText(""+final_score);
        dialog.show();
        stopService(new Intent(this,musicPlayer.class));
        if(value > final_score){
            star.setVisibility(View.VISIBLE);
        }
        return final_score;
    }




    @Override
    public void onClick(View v) {
        Toast.makeText(this,dificulty.scorer+"-"+question.question.length,Toast.LENGTH_SHORT).show();
        boolean pass = false;

        if(option1.isChecked()){
            pass = true;
            this.answer = option1.getText().toString();
        }else if(option2.isChecked()){
            pass = true;
            this.answer = option2.getText().toString();
        }else if(option3.isChecked()){
            pass = true;
            this.answer = option3.getText().toString();
        }else if(option4.isChecked()){
            pass = true;
            this.answer = option4.getText().toString();
        }else{
            Toast.makeText(this,"pls select",Toast.LENGTH_SHORT).show();
        }

        if(pass == true){
            if(call == false){
                downTimer.resumeTimer();
                callTime.stopTimer();
                caller.setText("");
            }

            option1.setClickable(true);
            option2.setClickable(true);
            option3.setClickable(true);
            option4.setClickable(true);
            option1.setTextColor(Color.WHITE);
            option2.setTextColor(Color.WHITE);
            option3.setTextColor(Color.WHITE);
            option4.setTextColor(Color.WHITE);
            if(dificulty.dificulties.equals("EASY")){
                if(itemNo < question.question.length - 2){
                    counter +=1;
                    pages.setText("Question # "+counter);
                    if(this.answer == question.correct[itemNo]){
                        dificulty.scorer += 1;
                        Toast.makeText(this, "Correct",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"Wrong",Toast.LENGTH_SHORT).show();
                    }
                    itemNo+=1;
                    questionLabel.setText(question.question[itemNo]);
                    option1.setText(question.answers[itemNo][0]);
                    option2.setText(question.answers[itemNo][1]);
                    option3.setText(question.answers[itemNo][2]);
                    option4.setText(question.answers[itemNo][3]);
                }
                else {
                    no_more(dificulty.scorer);
                    your_score_number.setText(""+dificulty.scorer);
                    Toast.makeText(this,"no more questions",Toast.LENGTH_SHORT).show();
                }
            }
            else if(dificulty.dificulties.equals("AVERAGE")){
                if(itemNo < question.question2.length - 2){
                    counter +=1;
                    pages.setText("Question # "+counter);
                    if(this.answer == question.correct2[itemNo]){
                        dificulty.scorer += 1;
                        Toast.makeText(this, "Correct",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"Wrong",Toast.LENGTH_SHORT).show();
                    }

                    itemNo+=1;
                    questionLabel.setText(question.question2[itemNo]);
                    option1.setText(question.answers2[itemNo][0]);
                    option2.setText(question.answers2[itemNo][1]);
                    option3.setText(question.answers2[itemNo][2]);
                    option4.setText(question.answers2[itemNo][3]);

                }
                else {

                    no_more(dificulty.scorer);
                    your_score_number.setText(""+dificulty.scorer);
                    Toast.makeText(this,"no more questions",Toast.LENGTH_SHORT).show();
                }
            }
            else if(dificulty.dificulties.equals("DIFFICULT")){
                if(itemNo < question.question3.length - 2){
                    counter +=1;
                    pages.setText("Question # "+counter);
                    if(this.answer == question.correct3[itemNo]){

                        dificulty.scorer += 1;
                        Toast.makeText(this, "Correct",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"Wrong",Toast.LENGTH_SHORT).show();
                    }

                    itemNo+=1;
                    questionLabel.setText(question.question3[itemNo]);
                    option1.setText(question.answers3[itemNo][0]);
                    option2.setText(question.answers3[itemNo][1]);
                    option3.setText(question.answers3[itemNo][2]);
                    option4.setText(question.answers3[itemNo][3]);

                }
                else {
                    no_more(dificulty.scorer);
                    your_score_number.setText(""+dificulty.scorer);
                    Toast.makeText(this,"no more questions",Toast.LENGTH_SHORT).show();
                }
            }

            radioGroup.clearCheck();
        }
    }
}
