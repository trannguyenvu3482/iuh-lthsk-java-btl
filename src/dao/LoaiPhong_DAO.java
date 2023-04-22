package dao;

import connectDB.ConnectDB;
import entity.LoaiPhong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoaiPhong_DAO {
    public LoaiPhong_DAO() {
    }

    public List<LoaiPhong> getAllLoaiPhong() {
        ArrayList<LoaiPhong> dsLoaiPhong = new ArrayList<>();

        try {
            Connection con = ConnectDB.getConnection();

            String sql = "SELECT * FROM LoaiPhong";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String maLoai = rs.getString("maLoai");
                String tenLoai = rs.getString("tenLoai");
                String moTa = rs.getString("moTa");

                LoaiPhong loaiPhong = new LoaiPhong(maLoai, tenLoai, moTa);
                dsLoaiPhong.add(loaiPhong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsLoaiPhong;
    }

    public LoaiPhong getLoaiPhongByID(String ID) {
        Connection conn = ConnectDB.getConnection();

        try {
            String sql = "SELECT * FROM LoaiPhong WHERE maLoai = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ID);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                String maLoai = rs.getString("maLoai");
                String tenLoai = rs.getString("tenLoai");
                String chatLuong = rs.getString("chatLuong");
                
                return new LoaiPhong(maLoai, tenLoai, chatLuong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getMaFromLoai(String loaiPhong, String chatLuong) {
        Connection conn = ConnectDB.getConnection();
        LoaiPhong h;

        try {
            String sql = "SELECT maLoai FROM LoaiPhong WHERE tenLoai = ? AND chatLuong = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, loaiPhong);
            stm.setString(2, chatLuong);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getString("maLoai");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
