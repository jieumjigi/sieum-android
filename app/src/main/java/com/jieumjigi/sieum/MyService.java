package com.jieumjigi.sieum;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;

public class MyService extends Service {
    NotificationManager Notifi_M;
    ServiceThread thread;
    Notification Notifi ;

    String title, poetName;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notifi_M = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        myServiceHandler handler = new myServiceHandler();
        thread = new ServiceThread(handler);
        thread.start();
        return START_STICKY;
    }

    //서비스가 종료될 때 할 작업

    public void onDestroy() {
        thread.stopForever();
        thread = null;//쓰레기 값을 만들어서 빠르게 회수하라고 null을 넣어줌.
    }

    class myServiceHandler extends Handler {
        @Override
        public void handleMessage(android.os.Message msg) {
            Thread todayPoem = new JsonThread("poem/poemOfToday");
            todayPoem.start();

            Intent intent = new Intent(MyService.this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);

            poemOfToday poemToday = Singleton.getInstance().getSingleton_List1().get(0);
            title = poemToday.getTitle();
            poetName = poemToday.getPoetName();

            Notifi = new Notification.Builder(getApplicationContext())
                    .setContentTitle("오늘의 시를 만나보세요")
                    .setContentText(title+"/"+poetName)
                    .setSmallIcon(R.drawable.quotation1)
                    .setTicker("오늘의 시 도착")
                    .setContentIntent(pendingIntent)
                    .build();

            //소리추가
//            Notifi.defaults = Notification.DEFAULT_SOUND;
            Notifi.sound = Uri.parse("android.resource://com.jieumjigi.sieum/"+R.raw.noti_sound);

            //알림 소리를 한번만 내도록
            Notifi.flags = Notification.FLAG_ONLY_ALERT_ONCE;

            //확인하면 자동으로 알림이 제거 되도록
            Notifi.flags = Notification.FLAG_AUTO_CANCEL;
            Notifi_M.notify( 777 , Notifi);
        }
    };
}

