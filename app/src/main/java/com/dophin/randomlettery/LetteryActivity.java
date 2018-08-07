package com.dophin.randomlettery;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.*;

public class LetteryActivity extends Activity {
    private List<String> datas = new ArrayList<>();
    private boolean running;
    private boolean wasrunning;
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lettery);
        String[] data = {"JJ","KK","YY"};
        datas.addAll(Arrays.asList(data));
        startLettery();

    }

    public void onClickAdd(View view) {
        EditText editText = (EditText)findViewById(R.id.addData);
        if(!wasrunning && !editText.getText().toString().matches("")){
            datas.add(editText.getText().toString());
        }

    }

    public void onClickClear(View view) {
        if(!wasrunning) {
            datas.clear();
        }

    }

    public void onClickLettery(View view) {
        TextView textView = (TextView)findViewById(R.id.DataView);
        if(datas.iterator().hasNext()){
            count = 0;
            running = true;
        }
        else {
            textView.setText("Your Data is null!!");

        }


        //int ram = (int) (Math.random() * datas.size() );
        //TextView textvies = (TextView)findViewById(R.id.DataView);
        //textvies.setText(datas.get(ram));
    }

    private void startLettery() {
        final TextView textview = (TextView)findViewById(R.id.DataView);
        final Handler handler = new Handler();
        handler.post( new Runnable(){
            @Override
                    public void run() {

                int randomInt = (int) (Math.random() * datas.size());
                if (running && (count < 50)) {
                    count++;
                    textview.setText(datas.get(randomInt));
                    wasrunning = true;
                }else {
                    wasrunning = false;
                }

                handler.postDelayed(this,50);
            }



        });
    }
}
