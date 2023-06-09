package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHang_DAO {
	public KhachHang_DAO() {
	}

	public List<KhachHang> getAllKhachHang() throws Exception {
		Connection conn = ConnectDB.getConnection();
		List<KhachHang> dsKhachHang = new ArrayList<>();

		String sql = "SELECT * FROM KhachHang";
		PreparedStatement stm = conn.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();

		while (rs.next()) {
			String maKH = rs.getString("maKH");
			String tenKH = rs.getString("hoTenKH");
			LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
			String SDT = rs.getString("SDT");
			String CCCD = rs.getString("CCCD");

			KhachHang kh = new KhachHang(maKH, tenKH, ngaySinh, SDT, CCCD);
			dsKhachHang.add(kh);
		}

		return dsKhachHang;
	}

	public KhachHang getKhachHangByID(String ID) throws Exception {
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT * FROM KhachHang WHERE maKH = ?";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, ID);
		ResultSet rs = stm.executeQuery();

		if (rs.next()) {
			String maKH = rs.getString("maKH");
			String tenKH = rs.getString("hoTenKH");
			LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
			String SDT = rs.getString("SDT");
			String CCCD = rs.getString("CCCD");

			return new KhachHang(maKH, tenKH, ngaySinh, SDT, CCCD);
		}

		return null;
	}

	public KhachHang getKhachHangByCCCD(String CCCD) throws Exception {
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT * FROM KhachHang WHERE CCCD = ?";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, CCCD);
		ResultSet rs = stm.executeQuery();

		if (rs.next()) {
			String maKH = rs.getString("maKH");
			String tenKH = rs.getString("hoTenKH");
			LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
			String SDT = rs.getString("SDT");
			String CCCD1 = rs.getString("CCCD");

			return new KhachHang(maKH, tenKH, ngaySinh, SDT, CCCD1);
		}

		return null;
	}

	public List<KhachHang> getTopKhachHang() {
		List<KhachHang> dsKhachHang = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			String sql = "SELECT TOP 5 * FROM KhachHang ORDER BY tongTien DESC";
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				String maKH = rs.getString("maKH");
				String tenKH = rs.getString("hoTenKH");
				LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
				String SDT = rs.getString("SDT");
				String CCCD = rs.getString("CCCD");

				KhachHang kh = new KhachHang(maKH, tenKH, ngaySinh, SDT, CCCD);
				dsKhachHang.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}

	public boolean checkKhachHangByID(String ID) throws Exception {
		Connection conn = ConnectDB.getConnection();
		String sql = "SELECT * FROM KhachHang WHERE maKH = ?";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, ID);
		ResultSet rs = stm.executeQuery();

		return rs.next();
	}

	public void addKhachHang(KhachHang kh) throws Exception {
		Connection conn = ConnectDB.getConnection();
		String sql = "INSERT INTO KhachHang VALUES(?, ?, ?, ?, ?)";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, kh.getMaKH());
		stm.setString(2, kh.getTenKH());
		stm.setDate(3, Date.valueOf(kh.getNgaySinh()));
		stm.setString(4, kh.getSDT());
		stm.setString(5, kh.getCCCD());
		stm.executeUpdate();
	}

	public void updateKhachHang(KhachHang kh) throws Exception {
		Connection conn = ConnectDB.getConnection();
		String sql = "UPDATE KhachHang SET tenKH = ?, ngaySinh = ?, SDT = ?, CCCD = ? WHERE maKH = ?";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, kh.getTenKH());
		stm.setDate(2, Date.valueOf(kh.getNgaySinh()));
		stm.setString(3, kh.getSDT());
		stm.setString(4, kh.getCCCD());
		stm.setString(5, kh.getMaKH());
		stm.executeUpdate();
	}

	public void deleteKhachHang(String maKH) throws Exception {
		Connection conn = ConnectDB.getConnection();
		String sql = "DELETE FROM KhachHang WHERE maKH = ?";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, maKH);
		stm.executeUpdate();
	}
}
