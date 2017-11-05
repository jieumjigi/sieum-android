package com.jieumjigi.sieum;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by wjddk on 2017-05-31.
 */

public class AboutusActivity extends AppCompatActivity {

    Button facebook_link;
    Button complaint;
    Button review_app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        Util.setGlobalFont(this, getWindow().getDecorView());

        //facebook_link, complaint, review_app
        facebook_link = (Button)findViewById(R.id.facebook_link);

        facebook_link.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/tasteapoem")));
            }

        });

        complaint = (Button)findViewById(R.id.complaint);

        complaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // 메일 연동
                Uri uri = Uri.parse("mailto:" + "jeongah.h.shin@gmail.com");
                // 인수로 송신지를 설정하고 이 값은 setData로 설정해도 된다
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                // 설정할 메일 본문의 제목이 Text인 경우에는 text/plain을, HTML인 경우에는 text/html을 설정한다
                intent.setType("text/plain");
                // 제목을 설정한다
                intent.putExtra(Intent.EXTRA_SUBJECT, "시음 안드로이드 개발자에게 문의하기");
                // 본문을 설정한다
                intent.putExtra(Intent.EXTRA_TEXT, "");
                try {
                    startActivity(intent);
                } catch(ActivityNotFoundException e) {}
            }

        });
        review_app = (Button)findViewById(R.id.review_app);

        review_app.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://market.android.com/details?id=com.jieumjigi.sieum")));
            }

        });


    }
}
