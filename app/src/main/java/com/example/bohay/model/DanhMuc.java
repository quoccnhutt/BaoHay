package com.example.bohay.model;


public class DanhMuc {

    private int id_danhmuc;
    private String tendanhmuc;
    private int image;

    public DanhMuc(int id_danhmuc, String tendanhmuc, int image) {
        this.id_danhmuc = id_danhmuc;
        this.tendanhmuc = tendanhmuc;
        this.image = image;
    }

    public int getId_danhmuc() {
        return id_danhmuc;
    }

    public void setId_danhmuc(int id_danhmuc) {
        this.id_danhmuc = id_danhmuc;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
