package entity;

import java.time.LocalDate;

public class NhanVien {
    private String maNV;
    private String matKhau;
    private String tenNV;
    private LocalDate ngaySinh;
    private String CCCD;
    private String SDT;

    public NhanVien(String maNV, String matKhau, String tenNV, LocalDate ngaySinh, String CCCD, String SDT) throws Exception {
        this.setCCCD(CCCD);
        this.setMatKhau(matKhau);
        this.setMaNV(maNV);
        this.setNgaySinh(ngaySinh);
        this.setSdt(SDT);
        this.setTenNV(tenNV);
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) throws Exception {
        if (maNV.matches("NV\\d{3}")) {
            this.maNV = maNV;
        }
        throw new Exception("Mã nhân viên phải có dạng NV-XXX với X là số nguyên dương");
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) throws Exception {
        if (matKhau.matches("\\w{6,}")) {
            this.matKhau = matKhau;
        } else throw new Exception("Mật khẩu phải có ít nhất 6 ký tự");
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) throws Exception {
        if (tenNV.matches("[A-Z].+( [A-Z].+)")) {
            this.tenNV = tenNV;
        } else throw new Exception("Tên nhân viên phải có ít nhất 2 từ, mỗi từ bắt đầu bằng chữ hoa");
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) throws Exception {
        if (ngaySinh.isBefore(LocalDate.now())) {
            this.ngaySinh = ngaySinh;
        } else throw new Exception("Ngày sinh không hợp lệ");
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) throws Exception {
        if (CCCD.matches("\\d{12}")) {
            this.CCCD = CCCD;
        } else throw new Exception("CCCD phải có 12 chữ số");
    }

    public String getSdt() {
        return SDT;
    }

    public void setSdt(String SDT) throws Exception {
        if (SDT.matches("0\\d{9}")) {
            this.SDT = SDT;
        } else throw new Exception("Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0");
    }
}