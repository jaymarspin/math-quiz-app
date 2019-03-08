package com.example.jaymardaligdig.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

public class customListView extends ArrayAdapter<String> {


    private String fruitName[];
    private String scores[];

    private Activity context;
    public customListView(Activity context, String fruitName[],String scores[]) {
        super(context, R.layout.listview_layout,fruitName);
        this.context = context;
        this.fruitName = fruitName;
        this.scores = scores;


    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        viewHolder viewHolderVar = null;
        if(r == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_layout,null,true);
            viewHolderVar = new viewHolder(r);
            r.setTag(viewHolderVar);
        }else{
            viewHolderVar = (viewHolder) r.getTag();
        }

        viewHolderVar.tv1.setText(fruitName[position]);
        viewHolderVar.tv2.setText(scores[position]);




        return r;

    }
    class viewHolder{
        TextView tv1;
        TextView tv2;

        viewHolder(View v){
            tv1 =  v.findViewById(R.id.scorelist);
            tv2 =  v.findViewById(R.id.namelist);

        }
    }
}
