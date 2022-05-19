package com.example.bohay.model;

public class TinTuc {
    private int id_tin;
    private String tieude;
//    private String noidung;
    private String anhminhhoa;
    private String ngaydang;
    private String tendanhmuc;
    private String nguontin;

    public TinTuc(int id_tin, String tieude, String anhminhhoa, String ngaydang, String tendanhmuc, String nguontin) {
        this.id_tin = id_tin;
        this.tieude = tieude;
//        this.noidung = noidung;
        this.anhminhhoa = anhminhhoa;
        this.ngaydang = ngaydang;
        this.tendanhmuc = tendanhmuc;
        this.nguontin = nguontin;
    }

    public int getId_tin() {
        return id_tin;
    }

    public void setId_tin(int id_tin) {
        this.id_tin = id_tin;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }


    public String getAnhminhhoa() {
        return anhminhhoa;
    }

    public void setAnhminhhoa(String anhminhhoa) {
        this.anhminhhoa = anhminhhoa;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public String getNguontin() {
        return nguontin;
    }

    public void setNguontin(String nguontin) {
        this.nguontin = nguontin;
    }
}
