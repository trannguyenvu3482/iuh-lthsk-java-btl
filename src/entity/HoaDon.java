package entity;

import java.time.LocalDate;

public class HoaDon {
    private String maHD;
    private String maNV;
    private String maKH;
    private double tongTien;
    private LocalDate ngayTaoHD;

    public HoaDon(String maHD, String maNV, String maKH, double tongTien, LocalDate ngayTaoHD) throws Exception {
        this.setMaHD(maHD);
        this.setMaNV(maNV);
        this.setMaKH(maKH);
        this.setTongTien(tongTien);
        this.setNgayTaoHD(ngayTaoHD);
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) throws Exception {
        if (maHD.matches("^HD\\d{3}")) {
            this.maHD = maHD;
        } else {
            throw new Exception("Mã HD không hợp lệ");
        }
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) throws Exception {
        if (maNV.matches("^NV\\d{3}")) {
            this.maNV = maNV;
        } else {
            throw new Exception("Mã NV không hợp lệ");
        }
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) throws Exception {
        if (maKH.matches("^KH\\d{3}")) {
            this.maKH = maKH;
        } else {
            throw new Exception("Mã KH không hợp lệ");
        }
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) throws Exception {
        if (tongTien < 0) {
            throw new Exception("Tổng hóa đơn không được nhỏ hơn 0");
        }
        this.tongTien = tongTien;
    }

    public LocalDate getNgayTaoHD() {
        return ngayTaoHD;
    }

    public void setNgayTaoHD(LocalDate ngayTaoHD) throws Exception {
        if (ngayTaoHD.isAfter(LocalDate.now())) {
            throw new Exception("Ngày tạo hóa đơn không được lớn hơn ngày hiện tại");
        }
        this.ngayTaoHD = ngayTaoHD;
    }
}