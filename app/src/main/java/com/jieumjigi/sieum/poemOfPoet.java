package com.jieumjigi.sieum;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wjddk on 2017-02-06.
 */

public class poemOfPoet {
    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public Map<String, String> getPoems() {
        return poems;
    }

    public void setPoems(Map<String, String> poems) {
        this.poems = poems;
    }

    /**
     * poem/poemOfPoet
     */

    @SerializedName("isSuccess")
    Boolean isSuccess;

    @SerializedName("poems")
    public Map<String,String> poems = new HashMap<String, String>();

}