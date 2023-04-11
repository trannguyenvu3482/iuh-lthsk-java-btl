package entity;

public class Phong {

    private String maPhong;
    private String maLoai;
    private Boolean tinhTrang;
    private double giaPhong;
    private String ghiChu;

    public Phong(String maPhong, String maLoai, Boolean tinhTrang, double giaPhong, String ghiChu) throws Exception {
        System.out.println("Phong: " + maPhong + " " + maLoai + " " + tinhTrang + " " + giaPhong + " " + ghiChu);

        setMaPhong(maPhong);
        setMaLoai(maLoai);
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

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) throws Exception {
        if (!(maLoai.trim().equals(""))) {
            this.maLoai = maLoai;
        } else throw new Exception("Loại phòng không được rỗng");
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
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

    public void setGiaPhong(double giaPhong) throws Exception {
        if (giaPhong >= 0) {
            this.giaPhong = giaPhong;
        } else throw new Exception("Giá phòng phải lớn hơn 0");
    }

}