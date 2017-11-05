package com.jieumjigi.sieum;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by wjddk on 2017-02-18.
 */

public class Splash extends AppCompatActivity {
    ImageView logo;
    TextView subtitle;
    LinearLayout Llayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Util.setGlobalFont(this, getWindow().getDecorView());

        // splash.xml의 구성요소 선언부
        logo = (ImageView)findViewById(R.id.logo);
        subtitle = (TextView)findViewById(R.id.subtitle);
        Llayout = (LinearLayout)findViewById(R.id.Llayout);
        Llayout.setBackgroundColor(Color.rgb(255,255,255));


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                subtitle.setText(" Taste a poem ");
            }
        }, 1500);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();
            }
        }, 3000);// 3 초

    }

}
