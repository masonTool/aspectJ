package com.mapeiyu.aspectj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int a = 0;
        int b = 100;
        try {
            int c = b / a;
        } catch (Exception e) {
            e.printStackTrace();
        }

        aaa();

//        onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void aaa() {

    }
}
