package dao;

import connectDB.ConnectDB;
import entity.Phong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Phong_DAO {
    public Phong_DAO() {
    }

    public List<Phong> getAllPhong() {
        ArrayList<Phong> dsPhong = new ArrayList<>();

        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "SELECT * FROM Phong";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String maLoai = rs.getString("maLoai");
                boolean tinhTrang = rs.getBoolean("tinhTrang");
                double giaPhong = rs.getDouble("giaPhong");
                String ghiChu = rs.getString("ghiChu");

                Phong phong = new Phong(maPhong, maLoai, tinhTrang, giaPhong, ghiChu);
                dsPhong.add(phong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsPhong;
    }

    public Phong getPhongByID(String ID) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        Phong h;

        try {
            String sql = "SELECT * FROM Phong WHERE maPhong = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ID);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String maLoai = rs.getString("maLoai");
                double giaPhong = rs.getDouble("giaPhong");
                boolean tinhTrang = rs.getBoolean("tinhTrang");
                String ghiChu = rs.getString("ghiChu");

                return new Phong(maPhong, maLoai, tinhTrang, giaPhong, ghiChu);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addPhong(Phong p) throws Exception {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();

        String sql = "INSERT INTO Phong VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, p.getMaPhong());
        stm.setString(2, p.getMaLoai());
        stm.setDouble(3, p.getGiaPhong());
        stm.setBoolean(4, p.getTinhTrang());
        stm.setString(5, p.getGhiChu());

        stm.executeUpdate();
    }

    public boolean editPhongByID(String ID, Phong p) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "UPDATE Phong SET maLoai = ?, giaPhong = ?, tinhTrang = ?, ghiChu = ? WHERE maPhong = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, p.getMaLoai());
            stm.setDouble(2, p.getGiaPhong());
            stm.setBoolean(3, p.getTinhTrang());
            stm.setString(4, p.getGhiChu());
            stm.setString(5, ID);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean deletePhongByID(String ID) {
        ConnectDB.getInstance();
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
