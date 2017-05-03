package com.timefeel.rxmovies.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.timefeel.rxmovies.ui.select.SelectActivity;


public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, SelectActivity.class);
        startActivity(intent);
        finish();
    }
}
