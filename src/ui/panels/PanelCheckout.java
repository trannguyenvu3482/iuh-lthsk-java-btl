package ui.panels;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import dao.*;
import entity.HoaDon;
import entity.KhachHang;
import entity.Phong;
import entity.TinhTrang;

public class PanelCheckout extends JPanel {
	private JTable tbl;
	private DefaultTableModel model;
	private JButton btnCheckout = new JButton("Checkout");
	private String currentMaNV = "";

	public PanelCheckout(String currentMaNV) {
		this.currentMaNV = currentMaNV;
		setBounds(0, 0, 725, 569);
		setVisible(false);
		setLayout(null);

		JLabel lblCheckout_1 = new JLabel("Check-out");
		lblCheckout_1.setBounds(305, 5, 100, 28);
		lblCheckout_1.setFont(new Font("Dialog", Font.BOLD, 20));
		add(lblCheckout_1);

		btnCheckout.setFont(new Font("DIalog", Font.BOLD, 20));

		createTable();

		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selection = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thanh toán phòng này?",
						"Thanh toán", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (selection == JOptionPane.YES_OPTION) {
					handleHoaDon(model.getValueAt(tbl.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "Thanh toán thành công!", "Thanh toán",
							JOptionPane.INFORMATION_MESSAGE);
					refreshTable();
				}
			}
		});
	}

	@SuppressWarnings("resource")
	public void handleHoaDon(String maPhong) {
		try {
			HoaDon_DAO hoaDonDAO = new HoaDon_DAO();
			Phong_DAO phongDAO = new Phong_DAO();

			int countHoaDon = hoaDonDAO.countHoaDon();
			Phong p = phongDAO.getPhongByID(maPhong);

			String maHD = "";

			if (countHoaDon == 0) {
				maHD = "HD001";
			} else {
				maHD = "HD" + String.format("%03d", countHoaDon + 1);
			}

			File file = new File("./src/txt/HoaDon" + maHD + ".txt");
			FileWriter fw = new FileWriter(file);

			if (file.exists()) {
				LoaiPhong_DAO loaiPhongDAO = new LoaiPhong_DAO();
				KhachHang_DAO khachHangDAO = new KhachHang_DAO();
				TinhTrang_DAO tinhTrangDAO = new TinhTrang_DAO();

				String maLoai = p.getMaLoai();
				TinhTrang tinhTrang = tinhTrangDAO.getTinhTrangByID(maPhong);
				KhachHang k = khachHangDAO.getKhachHangByID(tinhTrang.getMaKH());

				String loaiPhong = loaiPhongDAO.getLoaiPhongByID(maLoai).getTenLoai();
				String chatLuong = loaiPhongDAO.getLoaiPhongByID(maLoai).getChatLuong();

				double giaPhong;
				if (LocalDate.now().isBefore(tinhTrang.getNgayTra())) {
					if (ChronoUnit.DAYS.between(tinhTrang.getNgayDat(), LocalDate.now()) == 0) {
						giaPhong = p.getGiaPhong();
					} else {
						giaPhong = p.getGiaPhong() * ChronoUnit.DAYS.between(tinhTrang.getNgayDat(), LocalDate.now());
					}
				} else {
					giaPhong = p.getGiaPhong() * ChronoUnit.DAYS.between(tinhTrang.getNgayDat(), tinhTrang.getNgayTra());
				}

				HoaDon hd = new HoaDon(maHD, maPhong, currentMaNV, tinhTrang.getMaKH(), giaPhong, LocalDate.now());

				hoaDonDAO.addNewHoaDon(hd);

				fw.write("Hóa đơn thuê phòng: ");
				fw.write("\n");

				fw.write("Mã hóa đơn: " + hd.getMaHD());
				fw.write("\n");

				fw.write("----------------------------------------");
				fw.write("\n");

				fw.write("Mã khách hàng: " + hd.getMaKH());
				fw.write("\n");

				fw.write("Họ tên khách hàng: " + k.getTenKH());
				fw.write("\n");

				fw.write("Ngày sinh: " + k.getNgaySinh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				fw.write("\n");

				fw.write("Số điện thoại: " + k.getSDT());
				fw.write("\n");

				fw.write("Số CCCD: " + k.getCCCD());
				fw.write("\n");

				fw.write("----------------------------------------");
				fw.write("\n");

				fw.write("Mã phòng: " + hd.getMaPhong());
				fw.write("\n");

				fw.write("Loại phòng: " + loaiPhong);
				fw.write("\n");

				fw.write("Chất lượng: " + chatLuong);
				fw.write("\n");

				NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

				fw.write("Giá phòng: " + nf.format(hd.getTongTien()));
				fw.write("\n");

				fw.write("----------------------------------------");
				fw.write("\n");

				fw.write("Ngày thuê: " + hd.getNgayTaoHD().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

				fw.close();

				// Set tinh trang phong
				tinhTrangDAO.editTinhTrang(maPhong, new TinhTrang(tinhTrang.getMaPhong(), "KH000", null, null));
			} else {
				file.createNewFile();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
		}
	}

	private void createTable() {
		String[] tblCols = { "Mã phòng", "Giá phòng", "Khách hàng đặt", "Ngày đặt", "Ngày trả" };
		model = new DefaultTableModel(tblCols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tbl = new JTable(model);

		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Set size of columns and rows
		tbl.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(80);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(100);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(80);
		tbl.setRowHeight(30);

		// Set font size of table
		tbl.setFont(new Font("Arial", Font.PLAIN, 20));

		JScrollPane tblPane_1 = new JScrollPane(tbl);
		tblPane_1.setBounds(0, 36, 725, 533);
		tblPane_1.setBorder(BorderFactory.createTitledBorder("Thông tin các phòng"));
		add(tblPane_1);

		refreshTable();
	}

	public void refreshTable() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		Phong_DAO hotels = new Phong_DAO();
		List<Phong> list = hotels.getAllPhong();
		TinhTrang_DAO ttDao = new TinhTrang_DAO();

		// TODO: FIX THIS
		for (Phong p : list) {
			TinhTrang tt = ttDao.getTinhTrangByID(p.getMaPhong());
			if (!tt.getMaKH().equals("KH000")) {
				model.addRow(new Object[] { p.getMaPhong(), p.getGiaPhong(), tt.getMaKH(), tt.getNgayDat(), tt.getNgayTra() });
			}
		}
	}

}
