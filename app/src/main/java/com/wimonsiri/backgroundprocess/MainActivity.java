package com.wimonsiri.backgroundprocess;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {
    Button click;
    TextView showTime, showClick;
    int count = 0;
    private Handler mHandler = new Handler(Looper.myLooper());
    private long timeOld = 0L;
    private long timeNew = 0L;
    private String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showClick = (TextView) findViewById(R.id.showClick);
        showTime = (TextView) findViewById(R.id.showTime);
        click = (Button) findViewById(R.id.buttonClick);
        click.setOnClickListener(this);
        timeOld = SystemClock.uptimeMillis();
        mHandler.postDelayed( mUpdateTimeTask, 1000);
    }
    @Override
    public void onClick(View v) {
        count++;
        showClick.setText("Click : " + count);
    }
    private Runnable mUpdateTimeTask = new Runnable () {
        public void run() {

            timeNew = SystemClock.uptimeMillis();
            mHandler.postDelayed( this, 1000);
            str = "Time Start : "+ timeOld + "\n"+"Time Call : "+ timeNew;
            str += "\n" + "Differ Time : " + (timeNew-timeOld);
            showTime.setText( str );
            timeOld = timeNew;

        }
    };
}