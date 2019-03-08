package com.example.jaymardaligdig.myapplication;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;

public class global {


    public static void theme(ConstraintLayout constraintLayout){
        if(settings.backTheme.equals("Dark Theme")){
            constraintLayout.setBackgroundColor(Color.GRAY);
        }else{
            return;
        }
    }

}
