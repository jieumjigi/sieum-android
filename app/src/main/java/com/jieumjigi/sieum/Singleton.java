package com.jieumjigi.sieum;

import java.util.ArrayList;

/**
 * Created by wjddk on 2017-02-06.
 */
public class Singleton {
    private static Singleton ourInstance = new Singleton();
    ArrayList <poemOfToday> Singleton_List1 = new ArrayList<>();
    ArrayList <poemOfPoet> Singleton_List2 = new ArrayList<>();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
    }

    public ArrayList<poemOfToday> getSingleton_List1() {
        return Singleton_List1;
    }

    public void setSingleton_List1(ArrayList<poemOfToday> singleton_List) {
        Singleton_List1 = singleton_List;
    }

    public ArrayList<poemOfPoet> getSingleton_List2() {
        return Singleton_List2;
    }

    public void setSingleton_List2(ArrayList<poemOfPoet> singleton_List) {
        Singleton_List2 = singleton_List;
    }
}
