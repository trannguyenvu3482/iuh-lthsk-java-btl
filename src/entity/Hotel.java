package entity;

public class Hotel {
    // TODO: Add all the constraints to the fields
    private String maPhong;
    private String loaiPhong;
    private double giaPhong;
    private boolean tinhTrang;
    private String chatLuong;
    private String ghiChu;

    public Hotel(String maPhong, String loaiPhong, double giaPhong, boolean tinhTrang, String chatLuong, String ghiChu) {
        this.maPhong = maPhong;
        this.loaiPhong = loaiPhong;
        this.giaPhong = giaPhong;
        this.tinhTrang = tinhTrang;
        this.chatLuong = chatLuong;
        this.ghiChu = ghiChu;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public double getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(double giaPhong) {
        this.giaPhong = giaPhong;
    }

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getChatLuong() {
        return chatLuong;
    }

    public void setChatLuong(String chatLuong) {
        this.chatLuong = chatLuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        String s = "";
        s += getMaPhong() + " | " + getLoaiPhong() + " | " + getGiaPhong() + " | " + isTinhTrang() + " | " + getChatLuong() + " | " + getGhiChu() + " | ";
        s += "\n";

        return s;
    }
}
