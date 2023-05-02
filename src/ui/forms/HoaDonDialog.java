package ui.forms;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.Serial;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import dao.KhachHang_DAO;
import dao.LoaiPhong_DAO;
import dao.Phong_DAO;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.Phong;

public class HoaDonDialog extends JDialog {
	@Serial
	private static final long serialVersionUID = 1L;
	private HoaDon hd;
	private String maKH;
	private String hoTen;
	private String ngaySinh;
	private String sdt;
	private String cccd;
	private String maPhong;
	private String loaiPhong;
	private String chatLuong;
	private String giaPhong;
	private String ngayThue;

	public HoaDonDialog(HoaDon hd) {
		setTitle("Thông tin hóa đơn " + hd.getMaHD());
		setModal(true);
		setSize(600, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);

		this.hd = hd;

		getData();

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 600, 465);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		JPanel panelTitle = new JPanel();
		panelTitle.setBounds(0, 0, 600, 40);
		mainPanel.add(panelTitle);
		panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblThngTinHa = new JLabel("Thông tin hóa đơn " + hd.getMaHD());
		lblThngTinHa.setForeground(Color.RED);
		panelTitle.add(lblThngTinHa);
		lblThngTinHa.setFont(new Font("Dialog", Font.BOLD, 25));

		JPanel panelKhachHang = new JPanel();
		panelKhachHang.setBounds(0, 50, 600, 415);
		mainPanel.add(panelKhachHang);
		panelKhachHang.setLayout(new BoxLayout(panelKhachHang, BoxLayout.Y_AXIS));

		Box bTop = Box.createVerticalBox();
		bTop.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Thông tin khách hàng",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelKhachHang.add(bTop);

		Box b1Top = Box.createHorizontalBox();
		b1Top.setAlignmentX(LEFT_ALIGNMENT);
		bTop.add(b1Top);

		JLabel lblMaKH = new JLabel("Mã khách hàng: " + hd.getMaKH());
		b1Top.add(lblMaKH);
		lblMaKH.setFont(new Font("Dialog", Font.BOLD, 20));

		Component horizontalGlue = Box.createHorizontalGlue();
		b1Top.add(horizontalGlue);

		Box b2Top = Box.createHorizontalBox();
		b2Top.setAlignmentX(0.0f);
		bTop.add(b2Top);

		JLabel lblHoTen = new JLabel("Họ tên khách hàng: " + hoTen);
		lblHoTen.setFont(new Font("Dialog", Font.BOLD, 20));
		b2Top.add(lblHoTen);

		Box b3Top = Box.createHorizontalBox();
		b3Top.setAlignmentX(0.0f);
		bTop.add(b3Top);

		JLabel lblNgaySinh = new JLabel("Ngày sinh: " + ngaySinh);
		lblNgaySinh.setFont(new Font("Dialog", Font.BOLD, 20));
		b3Top.add(lblNgaySinh);

		Box b4Top = Box.createHorizontalBox();
		b4Top.setAlignmentX(0.0f);
		bTop.add(b4Top);

		JLabel lblSDT = new JLabel("Số điện thoại: " + sdt);
		lblSDT.setFont(new Font("Dialog", Font.BOLD, 20));
		b4Top.add(lblSDT);

		Box b5Top = Box.createHorizontalBox();
		b5Top.setAlignmentX(LEFT_ALIGNMENT);
		bTop.add(b5Top);

		JLabel lblSoCCCD = new JLabel("Số CCCD: " + cccd);
		b5Top.add(lblSoCCCD);
		lblSoCCCD.setFont(new Font("Dialog", Font.BOLD, 20));

		Component verticalStrut = Box.createVerticalStrut(20);
		panelKhachHang.add(verticalStrut);

		Box bTop_1 = Box.createVerticalBox();
		bTop_1.setBorder(new TitledBorder(null, "Thông tin phòng", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelKhachHang.add(bTop_1);

		Box b1Top_1 = Box.createHorizontalBox();
		b1Top_1.setAlignmentX(0.0f);
		bTop_1.add(b1Top_1);

		JLabel lblMaPhong = new JLabel("Mã phòng: " + maPhong);
		lblMaPhong.setFont(new Font("Dialog", Font.BOLD, 20));
		b1Top_1.add(lblMaPhong);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		b1Top_1.add(horizontalGlue_1);

		Box b2Top_1 = Box.createHorizontalBox();
		b2Top_1.setAlignmentX(0.0f);
		bTop_1.add(b2Top_1);

		JLabel lblLoaiPhong = new JLabel("Loại phòng: " + loaiPhong);
		lblLoaiPhong.setFont(new Font("Dialog", Font.BOLD, 20));
		b2Top_1.add(lblLoaiPhong);

		Box b3Top_1 = Box.createHorizontalBox();
		b3Top_1.setAlignmentX(0.0f);
		bTop_1.add(b3Top_1);

		JLabel lblChatLuong = new JLabel("Chất lượng: " + chatLuong);
		lblChatLuong.setFont(new Font("Dialog", Font.BOLD, 20));
		b3Top_1.add(lblChatLuong);

		Box b4Top_1 = Box.createHorizontalBox();
		b4Top_1.setAlignmentX(0.0f);
		bTop_1.add(b4Top_1);

		JLabel lblGiaPhong = new JLabel("Giá phòng: " + giaPhong);
		lblGiaPhong.setFont(new Font("Dialog", Font.BOLD, 20));
		b4Top_1.add(lblGiaPhong);

		Box b5Top_1 = Box.createHorizontalBox();
		b5Top_1.setAlignmentX(0.0f);
		bTop_1.add(b5Top_1);

		JLabel lblNgayLap = new JLabel("Ngày lập hóa đơn: " + ngayThue);
		lblNgayLap.setFont(new Font("Dialog", Font.BOLD, 20));
		b5Top_1.add(lblNgayLap);
	}

	private void getData() {
		KhachHang_DAO khDAO = new KhachHang_DAO();
		Phong_DAO phDAO = new Phong_DAO();
		LoaiPhong_DAO lpDAO = new LoaiPhong_DAO();

		KhachHang kh;

		try {
			kh = khDAO.getKhachHangByID(hd.getMaKH());
			Phong p = phDAO.getPhongByID(hd.getMaPhong());
			LoaiPhong lp = lpDAO.getLoaiPhongByID(p.getMaLoai());

			NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

			this.maKH = hd.getMaKH();
			this.hoTen = kh.getTenKH();
			this.ngaySinh = kh.getNgaySinh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			this.sdt = kh.getSDT();
			this.cccd = kh.getCCCD();
			this.maPhong = hd.getMaPhong();
			this.loaiPhong = lp.getTenLoai();
			this.chatLuong = lp.getChatLuong();
			this.giaPhong = nf.format(hd.getTongTien());
			this.ngayThue = hd.getNgayTaoHD().plusDays(2).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
