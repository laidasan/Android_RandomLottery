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
    private boolean running;      //控制要不要跑random
    private boolean wasrunning;   //紀錄是否正在跑Lottery
    private int count = 0;        //紀錄執行續執行次數  -> 控制隨機跑的動畫次數


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lettery);
        //String[] data = {"JJ","KK","YY"};
        //datas.addAll(Arrays.asList(data));
        startLettery();

    }

    public void onClickAdd(View view) {
        EditText editText = (EditText)findViewById(R.id.addData);
        //確認不再 run Lottery 與 加入的不是空字串的時候才可以加入樂透資料裡面
        if(!wasrunning && !editText.getText().toString().matches("")){
            datas.add(editText.getText().toString());
            editText.setText("");
        }

    }

    public void onClickClear(View view) {
        //不再 run Lottery的時候才能清除所有樂透資料
        if(!wasrunning) {
            datas.clear();
        }

    }

    public void onClickLettery(View view) {
        TextView textView = (TextView)findViewById(R.id.DataView);
        // 在start Lottery前先檢查datas裡是否有資料
        //有的話才能開始lottery
        //
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
                //利用亂數來達成樂透抽籤
                int randomInt = (int) (Math.random() * datas.size());

                //count 用來控制跑random動畫的次數(視覺上) 搭配者delayMillis使用
                //利用設定Textview的內容來達成跑Lottery動畫
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
