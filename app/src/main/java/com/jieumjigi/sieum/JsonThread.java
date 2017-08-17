package com.jieumjigi.sieum;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wjddk on 2017-02-06.
 */

public class JsonThread extends Thread {
    private String url;
    private OkHttpClient mClient;
    String base_url = "https://si-eum-169901.appspot.com/";

    public JsonThread(String keyword) {
        this.url = base_url+keyword;
        mClient = new OkHttpClient();
    }

    @Override
    public void run() {
        super.run();

        try {
            Request request = new Request.Builder().url(url).build();
            Response response = mClient.newCall(request).execute();

            while (true) {
                if (response.isSuccessful()) {
                    Message msg = Message.obtain();
                    msg.what = 0;
                    msg.obj = response.body().string();
                    jHandler.sendMessage(msg);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Handler jHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
//                JsonElement root1 = new JsonParser().parse(msg.obj.toString()).getAsJsonObject().get("poem");
//                JsonElement root2 = new JsonParser().parse(msg.obj.toString()).getAsJsonObject().get("poems");
                JsonElement root1 = new JsonParser().parse(msg.obj.toString()).getAsJsonObject().getAsJsonArray("poem");
                JsonElement root2 = new JsonParser().parse(msg.obj.toString()).getAsJsonObject().getAsJsonArray("poems");

                ArrayList<poemOfToday> TagData_List1 = new Gson().fromJson(root1, new TypeToken<ArrayList<poemOfToday>>() {}.getType());
                Singleton.getInstance().setSingleton_List1(TagData_List1);

                ArrayList<poemOfPoet> TagData_List2 = new Gson().fromJson(root2, new TypeToken<ArrayList<poemOfPoet>>() {}.getType());
                Singleton.getInstance().setSingleton_List2(TagData_List2);
            }
        }
    };

}
