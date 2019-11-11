package com.example.a0306171362_baihat;

import java.io.Serializable;

public class BaiHat implements Serializable {
    private int id;
    private String tieuDe;
    private String tacGia;
    private String loiBaiHat;

    public BaiHat() {
    }

    public BaiHat(int id, String tieuDe, String tacGia, String loiBaiHat) {
        this.id = id;
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        this.loiBaiHat = loiBaiHat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getLoiBaiHat() {
        return loiBaiHat;
    }

    public void setLoiBaiHat(String loiBaiHat) {
        this.loiBaiHat = loiBaiHat;
    }
}
