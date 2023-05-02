package entity;

import java.time.LocalDate;

public class TinhTrang {
	private String maPhong;
	private String maKH;
	private LocalDate ngayDat;
	private LocalDate ngayTra;

	public TinhTrang(String maPhong, String maKH, LocalDate ngayDat, LocalDate ngayTra) {
		this.maPhong = maPhong;
		this.maKH = maKH;
		this.ngayDat = ngayDat;
		this.ngayTra = ngayTra;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) throws Exception {
		if (maPhong.matches("P\\d{3}")) {
			this.maPhong = maPhong;
		} else
			throw new Exception("Mã phòng phải có dạng Pxxx, với x là số từ 0-9");
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) throws Exception {
		if (maKH.matches("KH\\d{3}")) {
			this.maKH = maKH;
		} else
			throw new Exception("Mã khách hàng phải có dạng KHxxx, với x là số từ 0-9");
	}

	public LocalDate getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(LocalDate ngayDat) {
		this.ngayDat = ngayDat;
	}

	public LocalDate getNgayTra() {
		return ngayTra;
	}

	public void setNgayTra(LocalDate ngayTra) {
		this.ngayTra = ngayTra;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
