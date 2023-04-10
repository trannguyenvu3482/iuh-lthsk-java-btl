package entity;

public class Phong {

    private String maPhong;
    private String loaiPhong;
    private String ghiChu;
    private Boolean tinhTrang;
    private double giaPhong;

    public Phong(String maPhong, String loaiPhong, String ghiChu, Boolean tinhTrang, double giaPhong) throws Exception {
        setMaPhong(maPhong);
        setLoaiPhong(loaiPhong);
        setGhiChu(ghiChu);
        setTinhTrang(tinhTrang);
        setGiaPhong(giaPhong);
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) throws Exception {
        if (maPhong.matches("P[0-9]{3}")) {
            this.maPhong = maPhong;
        } else throw new Exception("Mã phòng phải có dạng Pxxx (x là số từ 0-9)");
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) throws Exception {
        if (!(loaiPhong.trim().equals(""))) {
            this.loaiPhong = loaiPhong;
        } else throw new Exception("Loại phòng không được rỗng");
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) throws Exception {
        if (!(ghiChu.trim().equals(""))) {
            this.ghiChu = ghiChu;
        } else throw new Exception("Ghi chú không được rỗng");
    }

    public Boolean getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public double getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(double giaPhong) {
        if (giaPhong < 0) {
            this.giaPhong = 0;
        } else
            this.giaPhong = giaPhong;
    }

}