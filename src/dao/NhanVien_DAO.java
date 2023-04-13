package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.Phong;

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
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();

                String sdt = rs.getString("SDT");
                String cccd = rs.getString("CCCD");

                System.out.println(maNV);

                NhanVien nhanVien = new NhanVien(maNV, password, hoTen, ngaySinh, sdt, cccd);
                dsNhanVien.add(nhanVien);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsNhanVien;
    }

    public NhanVien getNhanVienByID(String ID) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        Phong h;

        try {
            String sql = "SELECT * FROM NhanVien WHERE maNV = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ID);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                String maNV = rs.getString("maNV");

                String password = rs.getString("matKhau");
                String hoTen = rs.getString("hoTenNV");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                String sdt = rs.getString("SDT");
                String cccd = rs.getString("CCCD");

                return new NhanVien(maNV, password, hoTen, ngaySinh, sdt, cccd);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addNhanVien(NhanVien nv) throws Exception {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();

        String sql = "INSERT INTO NhanVien VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, nv.getMaNV());
        statement.setString(2, nv.getMatKhau());
        statement.setString(3, nv.getTenNV());
        statement.setDate(4, java.sql.Date.valueOf(nv.getNgaySinh()));
        statement.setString(5, nv.getSdt());
        statement.setString(6, nv.getCCCD());

        statement.executeUpdate();
    }

    public boolean editNhanVienByID(String ID, NhanVien nv) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "UPDATE NhanVien SET matKhau = ?, hoTenNV = ?, ngaySinh = ?, SDT = ?, CCCD = ? WHERE maNV = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nv.getMatKhau());
            stm.setString(2, nv.getTenNV());
            stm.setDate(3, java.sql.Date.valueOf(nv.getNgaySinh()));
            stm.setString(4, nv.getSdt());
            stm.setString(5, nv.getCCCD());
            stm.setString(6, ID);

            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public void deletePhongByID(String ID) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "DELETE FROM NhanVien WHERE maNV = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ID);
            stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
