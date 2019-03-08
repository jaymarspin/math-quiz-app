package com.example.jaymardaligdig.myapplication;

import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


public class score extends AppCompatActivity {

    TextView textView,namer,textView2,namer2,textView3,namer3,textView4,namer4;
    database data;

    String scorelist[] = new String[5];
    String namelist[] = new String[5];
    ListView listView,listView2,listView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);




        listView = findViewById(R.id.list1);
        listView2 = findViewById(R.id.list2);
        listView3 = findViewById(R.id.list3);

        data = new database(this);




       try {
           Cursor d1 = data.data("SELECT * FROM scores WHERE category = 'g4' ORDER BY remarks DESC");
            int num = 0;
           while (d1.moveToNext()){
                if (num < scorelist.length){
                    scorelist[num] = d1.getInt(1)+"";
                    namelist[num] = d1.getString(5);
                    num += 1;
                }
           }
       }catch (SQLException e){

       }
        customListView customListView = new customListView(this,scorelist,namelist);
        listView.setAdapter(customListView);

        try {
            Cursor d1 = data.data("SELECT * FROM scores WHERE category = 'g5' ORDER BY remarks DESC");
            int num = 0;
            while (d1.moveToNext()){
                if (num < scorelist.length){
                    scorelist[num] = d1.getInt(1)+"";
                    namelist[num] = d1.getString(5);
                    num += 1;
                }
            }
        }catch (SQLException e){

        }

        customListView = new customListView(this,scorelist,scorelist);
        listView2.setAdapter(customListView);

        try {
            Cursor d1 = data.data("SELECT * FROM scores WHERE category = 'g6' ORDER BY remarks DESC");
            int num = 0;
            while (d1.moveToNext()){
                if (num < scorelist.length){
                    scorelist[num] = d1.getInt(1)+"";
                    namelist[num] = d1.getString(5);
                    num += 1;
                }
            }
        }catch (SQLException e){

        }

        customListView = new customListView(this,scorelist,scorelist);
        listView2.setAdapter(customListView);










        TabHost tabHost = findViewById(R.id.tabber);
        tabHost.setup();
        TabHost.TabSpec spec = tabHost.newTabSpec("g4");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Grade 4");
        tabHost.addTab(spec);


        TabHost.TabSpec spec2 = tabHost.newTabSpec("g5");
        spec2.setContent(R.id.tab1);
        spec2.setIndicator("Grade 5");
        tabHost.addTab(spec2);

        TabHost.TabSpec spec3 = tabHost.newTabSpec("g6");
        spec3.setContent(R.id.tab2);
        spec3.setIndicator("Grade 6");
        tabHost.addTab(spec3);



    }
}
