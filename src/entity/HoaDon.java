package entity;

import java.time.LocalDate;

public class HoaDon {
    private String maHD;
    private String maNV;
    private String maKH;
    private double tongHD;
    private LocalDate ngayTaoHD;

    public HoaDon(String maHD, String maNV, String maKH, double tongHD, LocalDate ngayTaoHD) throws Exception {
        this.setMaHD(maHD);
        this.setMaNV(maNV);
        this.setMaKH(maKH);
        this.setTongHD(tongHD);
        this.setNgayTaoHD(ngayTaoHD);
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) throws Exception {
        if (!maHD.equalsIgnoreCase("")) {
            this.maHD = maHD;
        } else
            throw new Exception("Mã HD không được rỗng");
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) throws Exception {
        if (!maNV.equalsIgnoreCase("")) {
            this.maNV = maNV;
        } else
            throw new Exception("Mã NV không được rỗng");
    }
    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) throws Exception {
        if (!maKH.equalsIgnoreCase("")) {
            this.maKH = maKH;
        } else
            throw new Exception("Mã KH không được rỗng");
    }
    public double getTongHD() {
        return tongHD;
    }

    public void setTongHD(double tongHD) throws Exception {
        if (tongHD < 0) {
            throw new Exception("Tổng hóa đơn không được nhỏ hơn 0");
        }
        this.tongHD = tongHD;
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