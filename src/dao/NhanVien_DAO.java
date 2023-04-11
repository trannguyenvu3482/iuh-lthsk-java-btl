package dao;

import connectDB.ConnectDB;
import entity.NhanVien;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class NhanVien_DAO {
    public NhanVien_DAO() {
    }

    public boolean checkLogin(String maNhanVien, String password) {
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "SELECT * FROM NhanVien WHERE maNV = '" + maNhanVien + "' AND matkhau = '" + password + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> dsNhanVien = new ArrayList<>();

        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "SELECT * FROM NhanVien";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String maNV = rs.getString("maNV");
                String password = rs.getString("matkhau");
                String hoTen = rs.getString("hoTenNV");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String sdt = rs.getString("SDT");
                String cccd = rs.getString("CCCD");

                NhanVien nhanVien = new NhanVien(maNV, password, hoTen, ngaySinh, sdt, cccd);
                dsNhanVien.add(nhanVien);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsNhanVien;
    }
}
