package com.jieumjigi.sieum;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUEST_PERMISSION_CODE = 2;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    public static ImageView butterfly;
    //save, share, alarm, aboutus
    SlidingDrawer menu_space;
    ImageButton save, share, alarm, aboutus;
    String address;

    public static String title, poetName, introPoet,
            linkToBook, question, contents, pushDueDate, published_date;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Util.setGlobalFont(this, getWindow().getDecorView());

        Thread todayPoem = new JsonThread("poem/poemOfToday");
        todayPoem.start();

        permissionCheck();

        Handler settingHandler = new Handler();
        settingHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {

                poemOfToday poemToday = Singleton.getInstance().getSingleton_List1().get(0);
                title = poemToday.getTitle();
                poetName = poemToday.getPoetName();
                contents = poemToday.getContents();
                introPoet = poemToday.getIntroPoet();
                linkToBook = poemToday.getLinkToBook();
                question = poemToday.getQuestion();
                pushDueDate = poemToday.getPushDueDate();
                published_date = poemToday.getPublished_date();

            }
        }, 2000);

        //Intent intent = new Intent(MainActivity.this,MyService.class);
        //startService(intent);

        startActivity(new Intent(this, Splash.class));

        menu_space = (SlidingDrawer)findViewById(R.id.menu_space);

        bindSlidingDrawer();

        save = (ImageButton)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeScreenShot();
                Toast.makeText(MainActivity.this, "오늘의 시를 담습니다.", Toast.LENGTH_SHORT).show();

            }
        });
        share = (ImageButton)findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                takeScreenShot();
                Toast.makeText(MainActivity.this, "오늘의 시를 나눌 대상을 선택해주세요.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SEND);
                File file = new File(Environment.getExternalStorageDirectory(), address);
                Uri uri =Uri.fromFile(file);
                intent.setType("image/jpg");
                intent.putExtra(Intent.EXTRA_STREAM,uri );
                startActivity(Intent.createChooser(intent, "공유하기"));

            }
        });
        alarm = (ImageButton)findViewById(R.id.alarm);
        alarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent aboutIntent = new Intent(MainActivity.this, AboutusActivity.class);
                Toast.makeText(MainActivity.this, "버그 수정 중\n미안해용ㅠ.ㅠ ", Toast.LENGTH_SHORT).show();

            }
        });
        aboutus = (ImageButton)findViewById(R.id.aboutus);
        aboutus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               Intent aboutIntent = new Intent(MainActivity.this, AboutusActivity.class);
                startActivity(aboutIntent);


            }
        });


//        final Animation menu_up = AnimationUtils.loadAnimation(this.getActivity(),R.anim.menu_up);
//        final Animation menu_down = AnimationUtils.loadAnimation(this.getActivity(),R.anim.menu_down);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.admin_tab);
//        menu_space = (RelativeLayout)findViewById(R.id.menu_space);
//        menu_space.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v) {
//
//                if(menu_state){
//                    menu_space.startAnimation(menu_down);
//                }else{
//                    menu_space.startAnimation(menu_up);
//                }
//            }
//        });
        //프래그먼트 리스트에 추가
        List<Fragment> listFragments = new ArrayList<>();
        listFragments.add(new PoemFragment());
        listFragments.add(new AbbrevFragment());
        listFragments.add(new PoetFragment());

        //TabPagerAdapter에 리스트를 넘겨준 후 ViewPager와 연결함.
        TabPagerAdapter fragmentPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), listFragments);
        viewPager.setAdapter(fragmentPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                switch(tab.getPosition()){
                    case 0:
                        viewPager.setCurrentItem(tab.getPosition());
                        break;
                    case 1:
                        viewPager.setCurrentItem(tab.getPosition());
                        break;
                    case 2:
                        viewPager.setCurrentItem(tab.getPosition());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    private void bindSlidingDrawer(){

        menu_space.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {

            }
        });
        menu_space.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {

            }
        });

    }

    public void takeScreenShot(){
        // 현재 화면을 지정할 View 컨테이너 생성
        View container;
        container = getWindow().findViewById(R.id.viewpager);
        container.buildDrawingCache(); // 현재 pager 레이아웃의 상태 담기
        Bitmap captureView = container.getDrawingCache();

        address = "/Pictures/Sieum/tastePoem_"+String.valueOf(System.currentTimeMillis())+".jpg";

        //폴더 생성
        File sddir = new File( Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures/Sieum/");
        if (sddir.exists()){}
        else{
            if (!sddir.mkdirs()) {
                Log.i("tr", "폴더생성");
                return;
            }
        }
        FileOutputStream fos;
        File savings = new File(Environment.getExternalStorageDirectory(), address);
        Log.w("ADDRESS",savings.toString());

        try{

            fos = new FileOutputStream(savings);
            captureView.compress(Bitmap.CompressFormat.JPEG,120,fos);

        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+Environment.getExternalStorageDirectory().getAbsolutePath()+address)));

    }

    public void permissionCheck() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK) != PackageManager.PERMISSION_GRANTED
                ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    ||ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    ||ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WAKE_LOCK)
                    ) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_REQUEST_PERMISSION_CODE);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, MY_REQUEST_PERMISSION_CODE);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WAKE_LOCK,
                        Manifest.permission.WAKE_LOCK}, MY_REQUEST_PERMISSION_CODE);

            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WAKE_LOCK
                        },
                        MY_REQUEST_PERMISSION_CODE);
            }


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_REQUEST_PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "권한을 획득했습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "권한이 없습니다.", Toast.LENGTH_LONG);
                    finish();
                }

                return;
            }

        }
    }






}

