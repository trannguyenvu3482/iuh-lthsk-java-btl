package dao;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.Phong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Phong_DAO {
    public Phong_DAO() {
    }

    public List<Phong> getAllPhong() {
        ArrayList<Phong> dsPhong = new ArrayList<>();

        try {
            Connection con = ConnectDB.getConnection();

            String sql = "SELECT maPhong, maLoai, giaPhong, ghiChu FROM Phong";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String maLoai = rs.getString("maLoai");
                double giaPhong = rs.getDouble("giaPhong");
                String ghiChu = rs.getString("ghiChu");

                Phong phong = new Phong(maPhong, maLoai, giaPhong, ghiChu);
                dsPhong.add(phong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsPhong;
    }

    public Phong getPhongByID(String ID) {
        Connection conn = ConnectDB.getConnection();

        try {
            String sql = "SELECT * FROM Phong WHERE maPhong = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ID);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String maLoai = rs.getString("maLoai");
                double giaPhong = rs.getDouble("giaPhong");
                String ghiChu = rs.getString("ghiChu");

                return new Phong(maPhong, maLoai, giaPhong, ghiChu);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Phong> getTopPhong() {
        List<Phong> dsPhong = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "SELECT TOP 5 * FROM Phong ORDER BY soLanDatPhong DESC";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String maLoai = rs.getString("maLoai");
                double giaPhong = rs.getDouble("giaPhong");
                String ghiChu = rs.getString("ghiChu");

                Phong phong = new Phong(maPhong, maLoai, giaPhong, ghiChu);
                dsPhong.add(phong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsPhong;
    }

    public void addPhong(Phong p) throws Exception {
        Connection conn = ConnectDB.getConnection();

        String sql = "INSERT INTO Phong VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, p.getMaPhong());
        stm.setString(2, p.getMaLoai());
        stm.setDouble(3, p.getGiaPhong());
        stm.setString(4, p.getGhiChu());
        stm.setInt(5, 0);

        stm.executeUpdate();
    }

    public boolean editPhongByID(String ID, Phong p) {
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "UPDATE Phong SET maLoai = ?, giaPhong = ?, ghiChu = ? WHERE maPhong = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, p.getMaLoai());
            stm.setDouble(2, p.getGiaPhong());
            stm.setString(3, p.getGhiChu());
            stm.setString(4, ID);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean deletePhongByID(String ID) {
        Connection conn = ConnectDB.getConnection();

        try {
            String sql = "DELETE FROM Phong WHERE maPhong = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ID);
            stm.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
