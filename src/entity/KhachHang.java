package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class KhachHang {
    private String maKH;
    private String tenKH;
    private LocalDate ngaySinh;
    private String SDT;
    private String CCCD;

    public KhachHang(String maKH, String tenKH, LocalDate ngaySinh, String SDT, String CCCD) throws Exception {
        this.setMaKH(maKH);
        this.setTenKH(tenKH);
        this.setNgaySinh(ngaySinh);
        this.setSDT(SDT);
        this.setCCCD(CCCD);
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


    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) throws Exception {
        // Kiểm tra tên khách hàng
        if (tenKH == null || tenKH.trim().length() < 2 || !Character.isUpperCase(tenKH.charAt(0))) {
            throw new Exception("Tên khách hàng phải có ít nhất 2 ký tự và bắt đầu bằng chữ hoa");
        }
        this.tenKH = tenKH;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) throws Exception {
        // Kiểm tra ngày sinh
        if (ngaySinh == null || ngaySinh.isBefore(LocalDate.now())) {
            throw new Exception("Ngày sinh không hợp lệ");
        }
        this.ngaySinh = ngaySinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) throws Exception {
        // Kiểm tra số điện thoại
        if (SDT == null || Double.parseDouble(SDT) < 0) {
            throw new Exception("Số điện thoại không hợp lệ");
        }
        this.SDT = SDT;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) throws Exception {
        // chỉ bao gồm số và nhập đúng 12 số
        if (CCCD.matches("\\d{12}")) {
            this.CCCD = CCCD;
        } else {
            throw new Exception("CCCD phải là chuỗi chứa đúng 12 chữ số!");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return maKH + " | " + tenKH + " | " + ngaySinh.format(formatter) + " | " + SDT + " | " + CCCD + " | ";
    }
}