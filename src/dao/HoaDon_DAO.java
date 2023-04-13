package dao;

import connectDB.ConnectDB;
import entity.HoaDon;

import java.sql.*;
import java.util.List;

public class HoaDon_DAO {
    public HoaDon_DAO() {
    }

    public List<HoaDon> getAllHoaDon() throws Exception {
        List<HoaDon> dsHoaDon = null;

        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();

        String sql = "SELECT * FROM HoaDon";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String maHoaDon = rs.getString("maHD");
            String maPhong = rs.getString("maPhong");
            String maNV = rs.getString("maNV");
            String maKH = rs.getString("maKH");
            double tongTien = rs.getDouble("tongTien");
            Date ngayTaoHD = rs.getDate("ngayTaoHD");

            HoaDon hoaDon = new HoaDon(maHoaDon, maPhong, maNV, maKH, tongTien, ngayTaoHD.toLocalDate());
            dsHoaDon.add(hoaDon);
        }

        return dsHoaDon;
    }

    public int countHoaDon() {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        int count = 0;

        try {
            String sql = "SELECT COUNT(*) FROM HoaDon";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public void addNewHoaDon(HoaDon hd) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();

        try {
            String sql = "INSERT INTO HoaDon VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, hd.getMaHD());
            stm.setString(2, hd.getMaPhong());
            stm.setString(3, hd.getMaNV());
            stm.setString(4, hd.getMaKH());
            stm.setDouble(5, hd.getTongTien());
            stm.setDate(6, Date.valueOf(hd.getNgayTaoHD()));

            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
