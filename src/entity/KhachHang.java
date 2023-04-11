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
        if (maKH.matches("KH\\d{3}")) {
            this.maKH = maKH;
        } else {
            throw new Exception("Mã KH không hợp lệ");
        }
    }


    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) throws Exception {
        if (tenKH == null || !tenKH.matches("([A-Z](.)+){2,}")) {
            throw new Exception("Tên khách hàng phải chứa ít nhất 2 chữ và viết hoa mỗi chữ cái đầu");
        }
        this.tenKH = tenKH;
    }


    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) throws Exception {
        // Kiểm tra ngày sinh
        if (ngaySinh == null || ngaySinh.isAfter(LocalDate.now())) {
            throw new Exception("Ngày sinh không hợp lệ");
        }
        this.ngaySinh = ngaySinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) throws Exception {
        // Kiểm tra số điện thoại
        if (SDT == null || !SDT.matches("^0\\d{9}$")) {
            throw new Exception("SDT phải là chuỗi chứa đúng 10 chữ số và bắt đầu bằng số 0!");
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