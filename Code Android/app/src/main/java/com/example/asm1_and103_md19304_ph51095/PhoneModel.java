package com.example.asm1_and103_md19304_ph51095;

public class PhoneModel {
    private  String _id;
    private  String ten;
    private  String hang;
    private int namSX;
    private double gia;

    public PhoneModel(String ten,String hang, int namSX, double gia) {
        this.ten = ten;
        this.gia = gia;
        this.hang = hang;
        this.namSX = namSX;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public int getNamSX() {
        return namSX;
    }

    public void setNamSX(int namSX) {
        this.namSX = namSX;
    }


}
