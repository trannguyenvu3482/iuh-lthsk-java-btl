package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.TinhTrang;

public class TinhTrang_DAO {
	public TinhTrang_DAO() {
		// TODO Auto-generated constructor stub
	}

	public List<TinhTrang> getAllTinhTrang() {
		List<TinhTrang> list = new ArrayList<TinhTrang>();

		try {
			Connection conn = ConnectDB.getConnection();

			String sql = "SELECT * FROM TinhTrang";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String maPhong = rs.getString("maPhong");
				String maKH = rs.getString("maKH");
				LocalDate ngayDat = rs.getDate("ngayDat") == null ? null : rs.getDate("ngayDat").toLocalDate();
				LocalDate ngayTra = rs.getDate("ngayTra") == null ? null : rs.getDate("ngayTra").toLocalDate();

				list.add(new TinhTrang(maPhong, maKH, ngayDat, ngayTra));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public TinhTrang getTinhTrangByID(String idPhong) {
		try {
			Connection conn = ConnectDB.getConnection();

			String sql = "SELECT * FROM TinhTrang WHERE maPhong = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, idPhong);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String maPhong = rs.getString("maPhong");
				String maKH = rs.getString("maKH");
				LocalDate ngayDat = rs.getDate("ngayDat") == null ? null : rs.getDate("ngayDat").toLocalDate();
				LocalDate ngayTra = rs.getDate("ngayTra") == null ? null : rs.getDate("ngayTra").toLocalDate();

				return new TinhTrang(maPhong, maKH, ngayDat, ngayTra);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean editTinhTrang(String maPhong, TinhTrang tinhTrang) {
		try {
			Connection conn = ConnectDB.getConnection();

			String sql = "UPDATE TinhTrang SET maKH = ?, ngayDat = ?, ngayTra = ? WHERE maPhong = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, tinhTrang.getMaKH());
			stmt.setDate(2, tinhTrang.getNgayDat() == null ? null : java.sql.Date.valueOf(tinhTrang.getNgayDat()));
			stmt.setDate(3, tinhTrang.getNgayTra() == null ? null :  java.sql.Date.valueOf(tinhTrang.getNgayTra()));
			stmt.setString(4, maPhong);

			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
