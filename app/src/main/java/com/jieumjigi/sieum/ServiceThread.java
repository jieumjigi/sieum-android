package com.jieumjigi.sieum;

import android.os.Handler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wjddk on 2017-05-17.
 */

public class ServiceThread extends Thread{
    Handler handler;
    boolean isRun = true;

    public ServiceThread(Handler handler){
        this.handler = handler;
    }

    public void stopForever(){
        synchronized (this) {
            this.isRun = false;
        }
    }

    public void run(){
        // 현재 시간을 msec으로 구한다.
        long now = System.currentTimeMillis();
        // 현재 시간을 저장 한다.
        Date date = new Date(now);
        // 시간 포맷으로 만든다.
        SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm");
        String strNow = sdfNow.format(date);

        //반복적으로 수행할 작업을 한다.
        while(isRun&&(strNow.equals("06:13"))){
            handler.sendEmptyMessage(0);//쓰레드에 있는 핸들러에게 메세지를 보냄
            try{
                Thread.sleep(1000*60*60*24); //10초씩 쉰다.
            }catch (Exception e) {}
        }
    }
}


