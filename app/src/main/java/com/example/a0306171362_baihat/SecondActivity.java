package com.example.a0306171362_baihat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.a0306171362_baihat.DSBaiHatAdapter.KEY_BAI_HAT;

public class SecondActivity extends AppCompatActivity {
    private TextView tvLoiBaiHat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tvLoiBaiHat = findViewById(R.id.tvLoiBaiHat);
        BaiHat baiHat = (BaiHat) getIntent().getExtras().getSerializable(KEY_BAI_HAT);
        getSupportActionBar().setTitle(baiHat.getTieuDe());
        getSupportActionBar().setSubtitle(baiHat.getTacGia());

        tvLoiBaiHat.setText(baiHat.getLoiBaiHat());


    }
}
