package com.example.bitmexburse;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingActivity extends AppCompatActivity {
    private static  int SLASH_TIME_OUT = 5000;
    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading);
        progressBar = findViewById(R.id.progressbar);
        textView = findViewById(R.id.textView);
        progressBar.setMax(100);
        progressBar.setScaleY(3f);
        progressAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(LoadingActivity.this, MyMenuActivity.class);
                startActivity(home);
                finish();
            }
        },SLASH_TIME_OUT);


    }

    public void progressAnimation(){
        ProgressBarAnimation anim = new ProgressBarAnimation(this,progressBar, textView, 0f, 100f);
        anim.setDuration(SLASH_TIME_OUT);
        progressBar.setAnimation(anim);
    }


}
