package com.example.a0306171362_baihat;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.HashMap;
import java.util.Map;

import static com.example.a0306171362_baihat.MainActivity.KEY_LIMIT;
import static com.example.a0306171362_baihat.MainActivity.KEY_PAGE;

public class BaiHatLoader extends AsyncTaskLoader<String> {

    private int page;
    private int limit;

    public BaiHatLoader(@NonNull Context context, int page, int limit) {
        super(context);
        this.page = page;
        this.limit = limit;
    }



    @Nullable
    @Override
    public String loadInBackground() {
        Object[] key = {KEY_PAGE,KEY_LIMIT};
        Object[] value = {page,limit};
        return  NetworkUtils.getJSONData("bai-hat","GET",key,value);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
