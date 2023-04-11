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
            ConnectDB.getInstance();
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
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        LoaiPhong h;

        try {
            String sql = "SELECT * FROM LoaiPhong WHERE maLoai = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ID);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                String maLoai = rs.getString("maLoai");
                String tenLoai = rs.getString("tenLoai");
                String chatLuong = rs.getString("chatLuong");

                h = new LoaiPhong(maLoai, tenLoai, chatLuong);
                return h;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
