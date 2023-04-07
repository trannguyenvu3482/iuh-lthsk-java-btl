package dao;

import connectDB.ConnectDB;
import entity.Hotel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Hotels_DAO {
    public Hotels_DAO() {
    }

    public List<Hotel> getAllHotels() {
        ArrayList<Hotel> dsPhong = new ArrayList<>();

        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "SELECT * FROM Hotels";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String loaiPhong = rs.getString("loaiPhong");
                double giaPhong = rs.getDouble("giaPhong");
                boolean tinhTrang = rs.getBoolean("tinhTrang");
                String chatLuong = rs.getString("chatLuong");
                String ghiChu = rs.getString("ghiChu");

                Hotel hotel = new Hotel(maPhong, loaiPhong, giaPhong, tinhTrang, chatLuong, ghiChu);
                dsPhong.add(hotel);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectDB.getInstance().disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dsPhong;
    }
}
