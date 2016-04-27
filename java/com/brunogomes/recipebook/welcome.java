package com.brunogomes.recipebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class welcome extends AppCompatActivity {

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setTimerStartActvity(3);
    }

    public void goToMainActivity(){
        Intent gomain = new Intent(this, MainActivity.class);
        startActivity(gomain);
        timer.cancel();
    }

    class goToMainTask extends TimerTask {
        public void run() {
            goToMainActivity();
            timer.cancel(); //Terminate the timer thread
        }
    }

    public void setTimerStartActvity(int sec){
        timer = new Timer();
        timer.schedule(new goToMainTask(), sec*1000);
    }

}
