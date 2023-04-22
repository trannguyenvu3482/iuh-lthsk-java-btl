package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.LoaiPhong_DAO;
import dao.NhanVien_DAO;
import dao.Phong_DAO;
import entity.NhanVien;
import entity.Phong;

public class GUI_NhanVien extends JFrame implements ActionListener {
	private final JPanel panelTwo = new JPanel();
	private final JTextField txtMaPhong = new JTextField();
	private final JTextField txtGiaPhong = new JTextField();
	private final JTextField txtGhiChu = new JTextField();
	private final JComboBox<String> cboChatLuong = new JComboBox<String>();
	private final JComboBox<String> cboLoaiPhong = new JComboBox<String>();
	private final JComboBox<String> cboFilterChatLuong = new JComboBox<String>();
	private final JComboBox<String> cboFilterLoaiPhong = new JComboBox<String>();
	private final JComboBox<String> cboFilterTinhTrang = new JComboBox<String>();
	private final JComboBox<String> cboFilterGia = new JComboBox<String>();
	private final JCheckBox chkDatPhong = new JCheckBox("");
	private final JLabel lblMaPhong = new JLabel("Mã phòng: ");
	private final JLabel lblLoaiPhong = new JLabel("Loại phòng: ");
	private final JLabel lblGiaPhong = new JLabel("Giá phòng: ");
	private final JLabel lblTinhTrang = new JLabel("Tình trạng: ");
	private final JLabel lblChatLuong = new JLabel("Chất lượng: ");
	private final JLabel lblGhiChu = new JLabel("Ghi chú: ");
	private final JButton btnThem = new JButton("Thêm");
	private final JButton btnXoa = new JButton("Xóa");
	private final JButton btnLuu = new JButton("Lưu");
	private final JButton btnHuy = new JButton("Xóa trắng");
	private final JButton btnTimKiem = new JButton("Tìm kiếm");
	private final JButton btnClearBoLoc = new JButton("Xóa bộ lọc");
	private final JButton btnInHoaDon = new JButton("In hóa đơn");
	private final JButton btnXacNhan_1 = new JButton("Xác nhận");
	private final JButton btnShow = new JButton("");
	private boolean isPasswordShown = false;
	private String currentMaNV = "";
	private JTable tbl;
	private DefaultTableModel model;

	private TableRowSorter<DefaultTableModel> rowSorter;
	private final JTextField txtMaNV_2;
	private final JTextField txtSDT_2;
	private final JTextField txtMaNV_1;
	private final JPasswordField txtMatKhau;
	private final JPasswordField txtMatKhauConfirm;
	private final JTextField txtCCCD;
	private final JPasswordField txtMatKhau_2;
	private final JTextField txtNgaySinh_2;
	private final JTextField txtHoTen_2;
	private final JPasswordField txtMatKhauOld;

	public GUI_NhanVien(String maNV) {
		setTitle("Phần mềm quản lý khách sạn - Nhóm 2");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		this.currentMaNV = maNV;

		getContentPane().setLayout(null);

		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		leftPanel.setBackground(new Color(204, 204, 255));
		leftPanel.setLocation(0, 0);
		leftPanel.setSize(271, 569);
		getContentPane().add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(null);

		JPanel logoPanel = new JPanel();
		logoPanel.setBackground(new Color(246, 97, 81));
		logoPanel.setBounds(0, 0, 271, 92);
		leftPanel.add(logoPanel);
		logoPanel.setLayout(null);

		JLabel logoLabel = new JLabel("Quản lý khách sạn");
		logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		logoLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		logoLabel.setBounds(5, 5, 266, 87);
		logoLabel.setIcon(new ImageIcon(GUI_NhanVien.class.getResource("/images/hotel-icon.png")));
		logoPanel.add(logoLabel);

		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(0, 0, 0, 0));
		menuPanel.setBounds(0, 90, 271, 479);
		leftPanel.add(menuPanel);
		menuPanel.setLayout(null);

		JPanel menuPanelItem1 = new JPanel();
		menuPanelItem1.setBackground(new Color(36, 31, 49));
		menuPanelItem1.setBounds(0, 34, 271, 80);
		menuPanel.add(menuPanelItem1);
		menuPanelItem1.setLayout(new BorderLayout(0, 0));

		JLabel lblThngTinPhn = new JLabel("Thông tin phần mềm");
		lblThngTinPhn.setIcon(new ImageIcon(GUI_NhanVien.class.getResource("/images/info-icon.png")));

		lblThngTinPhn.setForeground(new Color(255, 255, 255));
		lblThngTinPhn.setHorizontalAlignment(SwingConstants.CENTER);
		menuPanelItem1.add(lblThngTinPhn, BorderLayout.CENTER);
		lblThngTinPhn.setFont(new Font("Dialog", Font.BOLD, 20));

		JPanel menuPanelItem2 = new JPanel();
		menuPanelItem2.setForeground(new Color(255, 255, 255));
		menuPanelItem2.setBackground(new Color(36, 31, 49));
		menuPanelItem2.setBounds(0, 144, 271, 80);
		menuPanel.add(menuPanelItem2);
		menuPanelItem2.setLayout(new BorderLayout(0, 0));

		JLabel lblQunLPhng = new JLabel("Quản lý phòng");
		lblQunLPhng.setIcon(new ImageIcon(GUI_NhanVien.class.getResource("/images/magnify-icon.png")));
		lblQunLPhng.setForeground(new Color(255, 255, 255));
		lblQunLPhng.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLPhng.setFont(new Font("Dialog", Font.BOLD, 20));
		menuPanelItem2.add(lblQunLPhng);

		JPanel menuPanelItem3 = new JPanel();
		menuPanelItem3.setBackground(new Color(36, 31, 49));
		menuPanelItem3.setBounds(0, 254, 271, 80);
		menuPanel.add(menuPanelItem3);
		menuPanelItem3.setLayout(new BorderLayout(0, 0));

		JLabel lblThngKCc = new JLabel("Chỉnh sửa thông tin");
		lblThngKCc.setIcon(new ImageIcon(GUI_NhanVien.class.getResource("/images/account-cog-custom.png")));
		lblThngKCc.setForeground(new Color(255, 255, 255));
		lblThngKCc.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngKCc.setFont(new Font("Dialog", Font.BOLD, 20));
		menuPanelItem3.add(lblThngKCc);

		JLabel lblNhm = new JLabel("Welcome, " + currentMaNV);
		lblNhm.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNhm.setBounds(12, 439, 259, 28);
		menuPanel.add(lblNhm);

		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(271, 0, 725, 569);
		getContentPane().add(contentPanel);

		// Create table
		createTable();

		JPanel panelThree = new JPanel();
		panelThree.setBounds(0, 0, 725, 569);
		panelThree.setBackground(UIManager.getColor("Button.background"));
		contentPanel.setLayout(null);

		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Set size of columns and rows
		tbl.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(120);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(120);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(80);
		tbl.getColumnModel().getColumn(4).setPreferredWidth(80);
		tbl.getColumnModel().getColumn(4).setPreferredWidth(80);
		tbl.setRowHeight(30);

		// Set font size of table
		tbl.setFont(new Font("Arial", Font.PLAIN, 20));

		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoaiPhong_DAO lpDAO = new LoaiPhong_DAO();
				String maLoai = tbl.getValueAt(tbl.getSelectedRow(), 1).toString();
				int row = tbl.getSelectedRow();

				if (tbl.getValueAt(row, 3).equals("Đã thuê")) {
					chkDatPhong.setSelected(true);
				} else {
					chkDatPhong.setSelected(false);
				}

				txtMaPhong.setText(tbl.getValueAt(row, 0).toString());
				cboLoaiPhong.setSelectedItem(lpDAO.getLoaiPhongByID(maLoai).getTenLoai());
				cboChatLuong.setSelectedItem(lpDAO.getLoaiPhongByID(maLoai).getChatLuong());
				txtGiaPhong.setText(tbl.getValueAt(row, 2).toString());
				txtGhiChu.setText(tbl.getValueAt(row, 4).toString());

				btnInHoaDon.setVisible(true);
			}
		});

		JPanel panelOne = new JPanel();
		panelOne.setBounds(0, 0, 725, 569);
		panelOne.setLayout(null);
		JLabel lblPhnMmQun = new JLabel("Phần mềm quản lý khách sạn");
		lblPhnMmQun.setForeground(new Color(237, 51, 59));
		lblPhnMmQun.setFont(new Font("Dialog", Font.BOLD, 30));
		lblPhnMmQun.setBounds(161, 12, 441, 68);
		panelOne.add(lblPhnMmQun);

		contentPanel.add(panelOne);

		JLabel lblNhm_1 = new JLabel("Nhóm 2:");
		lblNhm_1.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNhm_1.setBounds(325, 78, 112, 45);
		panelOne.add(lblNhm_1);

		JLabel lblNhm_1_1 = new JLabel("Trần Ngọc Phát - 12345678");
		lblNhm_1_1.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNhm_1_1.setBounds(210, 135, 331, 45);
		panelOne.add(lblNhm_1_1);

		JLabel lblNhm_1_1_1 = new JLabel("Trần Nguyên Vũ - 12345678");
		lblNhm_1_1_1.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNhm_1_1_1.setBounds(205, 205, 356, 45);
		panelOne.add(lblNhm_1_1_1);

		JLabel lblNhm_1_1_1_1 = new JLabel("Mai Nhật Hào - 12345678");
		lblNhm_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNhm_1_1_1_1.setBounds(220, 275, 331, 45);
		panelOne.add(lblNhm_1_1_1_1);

		JLabel lblNhm_1_1_1_1_1 = new JLabel("Võ Phước Hậu - 12345678");
		lblNhm_1_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNhm_1_1_1_1_1.setBounds(220, 345, 331, 45);
		panelOne.add(lblNhm_1_1_1_1_1);

		panelOne.setVisible(true);
		lblTinhTrang.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTinhTrang.setFont(new Font("Arial", Font.PLAIN, 15));

		panelTwo.setBounds(0, 0, 725, 569);
		panelTwo.setBackground(UIManager.getColor("Button.background"));
		contentPanel.add(panelTwo);

		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(new TitledBorder(null, "Th\u00F4ng tin ph\u00F2ng", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		inputPanel.setBounds(0, 0, 715, 284);
		panelTwo.add(inputPanel);
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b6 = Box.createHorizontalBox();
		Box b7 = Box.createHorizontalBox();
		Box b8 = Box.createHorizontalBox();
		lblMaPhong.setFont(new Font("Dialog", Font.PLAIN, 14));

		b1.add(lblMaPhong);
		b1.add(txtMaPhong);
		lblLoaiPhong.setFont(new Font("Dialog", Font.BOLD, 14));

		b2.add(lblLoaiPhong);
		cboLoaiPhong.addItem("Phòng đơn");
		cboLoaiPhong.addItem("Phòng đôi");
		b2.add(cboLoaiPhong);
		lblGiaPhong.setFont(new Font("Dialog", Font.BOLD, 14));

		Component horizontalStrut = Box.createHorizontalStrut(10);
		b2.add(horizontalStrut);

		b2.add(lblChatLuong);
		cboChatLuong.addItem("Thường");
		cboChatLuong.addItem("Cao cấp");
		b2.add(cboChatLuong);
		lblGhiChu.setFont(new Font("Dialog", Font.BOLD, 14));

		lblChatLuong.setFont(new Font("Dialog", Font.BOLD, 14));

		b3.add(lblGiaPhong);
		b3.add(txtGiaPhong);

		b6.add(lblGhiChu);
		b6.add(txtGhiChu);

		b7.add(btnThem);
		b7.add(Box.createHorizontalStrut(10));
		b7.add(btnXoa);
		b7.add(Box.createHorizontalStrut(10));
		b7.add(btnLuu);
		b7.add(Box.createHorizontalStrut(10));
		b7.add(btnHuy);
		b7.add(Box.createHorizontalStrut(10));
		b7.add(btnTimKiem);
		b7.add(Box.createHorizontalStrut(10));
		b7.add(btnClearBoLoc);
		b7.add(Box.createHorizontalStrut(10));
		b7.add(btnInHoaDon);

		b8.setBorder(BorderFactory.createTitledBorder("Bộ lọc"));
		cboFilterLoaiPhong.addItem("Phòng đơn");
		cboFilterLoaiPhong.addItem("Phòng đôi");

		cboFilterChatLuong.addItem("Thường");
		cboFilterChatLuong.addItem("Cao cấp");

		JLabel lblNewLabel = new JLabel("Loại phòng: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		b8.add(lblNewLabel);
		b8.add(cboFilterLoaiPhong);
		b8.add(Box.createHorizontalStrut(10));

		JLabel lblNewLabel_1 = new JLabel("Chất lượng: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		b8.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Tình trạng: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		b8.add(cboFilterChatLuong);
		b8.add(Box.createHorizontalStrut(10));
		b8.add(lblNewLabel_2);

		cboFilterTinhTrang.addItem("Còn trống");
		cboFilterTinhTrang.addItem("Đã thuê");
		b8.add(cboFilterTinhTrang);
		b8.add(Box.createHorizontalStrut(10));

		JLabel lblNewLabel_3 = new JLabel("Giá: ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		b8.add(lblNewLabel_3);
		cboFilterGia.setFont(new Font("Tahoma", Font.PLAIN, 15));

		cboFilterGia.addItem("Tăng");
		cboFilterGia.addItem("Giảm");
		b8.add(cboFilterGia);

		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		b.add(b3);
		b.add(Box.createVerticalStrut(10));
		b.add(b6);
		b.add(Box.createVerticalStrut(10));
		b.add(b7);
		b.add(b8);
		b.add(Box.createVerticalStrut(20));

		// Set font of labels
		lblLoaiPhong.setFont(new Font("Arial", Font.PLAIN, 15));
		lblGiaPhong.setFont(new Font("Arial", Font.PLAIN, 15));
		lblChatLuong.setFont(new Font("Arial", Font.PLAIN, 15));
		lblGhiChu.setFont(new Font("Arial", Font.PLAIN, 15));

		// Set font size of text fields
		txtMaPhong.setFont(new Font("Arial", Font.PLAIN, 15));
		txtGiaPhong.setFont(new Font("Arial", Font.PLAIN, 15));
		txtGhiChu.setFont(new Font("Arial", Font.PLAIN, 15));

		// Set font size of combo box
		cboChatLuong.setFont(new Font("Arial", Font.PLAIN, 15));
		cboLoaiPhong.setFont(new Font("Arial", Font.PLAIN, 15));

		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		b2.add(horizontalStrut_1);

		JLabel lblDatPhong = new JLabel("Đã đặt:");
		lblDatPhong.setFont(new Font("Dialog", Font.PLAIN, 14));
		b2.add(lblDatPhong);

		b2.add(chkDatPhong);
		cboFilterChatLuong.setFont(new Font("Arial", Font.PLAIN, 15));
		cboFilterLoaiPhong.setFont(new Font("Arial", Font.PLAIN, 15));
		cboFilterTinhTrang.setFont(new Font("Arial", Font.PLAIN, 15));

		// Button font size
		btnThem.setFont(new Font("Arial", Font.BOLD, 15));
		btnXoa.setFont(new Font("Arial", Font.BOLD, 15));
		btnLuu.setFont(new Font("Arial", Font.BOLD, 15));
		btnHuy.setFont(new Font("Arial", Font.BOLD, 15));
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 15));
		btnClearBoLoc.setFont(new Font("Arial", Font.BOLD, 15));
		btnInHoaDon.setFont(new Font("Arial", Font.BOLD, 15));

		inputPanel.add(b);
		panelTwo.setVisible(false);
		JScrollPane tblPane = new JScrollPane(tbl);
		tblPane.setBorder(BorderFactory.createTitledBorder("Thông tin các phòng"));
		tblPane.setBounds(0, 285, 715, 284);

		btnInHoaDon.setVisible(false);
		panelTwo.add(tblPane);

		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnClearBoLoc.addActionListener(this);
		btnInHoaDon.addActionListener(this);
		btnXacNhan_1.addActionListener(this);

		// Filter handler
		cboFilterLoaiPhong.addActionListener(this);
		cboFilterChatLuong.addActionListener(this);
		cboFilterTinhTrang.addActionListener(this);
		cboFilterGia.addActionListener(this);

		contentPanel.add(panelThree);
		panelThree.setLayout(new BoxLayout(panelThree, BoxLayout.Y_AXIS));

		Box b10_1 = Box.createVerticalBox();
		b10_1.setBorder(new TitledBorder(null, "Chỉnh sửa thông tin", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelThree.add(b10_1);

		Box b11_1 = Box.createHorizontalBox();
		b10_1.add(b11_1);

		JLabel lblMaNV_1 = new JLabel("Mã nhân viên: ");
		lblMaNV_1.setFont(new Font("Dialog", Font.BOLD, 20));
		b11_1.add(lblMaNV_1);

		Component horizontalStrut_2_1_1_2 = Box.createHorizontalStrut(52);
		b11_1.add(horizontalStrut_2_1_1_2);

		txtMaNV_1 = new JTextField();
		txtMaNV_1.setText(currentMaNV);
		txtMaNV_1.setEditable(false);
		txtMaNV_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtMaNV_1.setColumns(10);
		b11_1.add(txtMaNV_1);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		b10_1.add(verticalStrut_1);

		Box b12_1_1 = Box.createHorizontalBox();
		b10_1.add(b12_1_1);

		JLabel lblMatKhauCu = new JLabel("Mật khẩu cũ: ");
		lblMatKhauCu.setFont(new Font("Dialog", Font.BOLD, 20));
		b12_1_1.add(lblMatKhauCu);

		Component horizontalStrut_2_1_1_1_1_1 = Box.createHorizontalStrut(64);
		b12_1_1.add(horizontalStrut_2_1_1_1_1_1);

		txtMatKhauOld = new JPasswordField();
		txtMatKhauOld.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtMatKhauOld.setColumns(20);
		b12_1_1.add(txtMatKhauOld);

		Component horizontalStrut_2_1_1_3_1 = Box.createHorizontalStrut(20);
		b12_1_1.add(horizontalStrut_2_1_1_3_1);
		b12_1_1.add(btnShow);
		btnShow.addActionListener(this);

		btnShow.setPreferredSize(new Dimension(40, 30));
		btnShow.setIcon(new ImageIcon(GUI_NhanVien.class.getResource("/images/eye-off-custom.png")));

		Component verticalStrut_1_2 = Box.createVerticalStrut(20);
		b10_1.add(verticalStrut_1_2);

		Box b12_1 = Box.createHorizontalBox();
		b10_1.add(b12_1);

		JLabel lblNewLabel_4_1_1 = new JLabel("Mật khẩu mới: ");
		lblNewLabel_4_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		b12_1.add(lblNewLabel_4_1_1);

		Component horizontalStrut_2_1_1_1_1 = Box.createHorizontalStrut(48);
		b12_1.add(horizontalStrut_2_1_1_1_1);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtMatKhau.setColumns(20);
		b12_1.add(txtMatKhau);

		Component verticalStrut_1_1 = Box.createVerticalStrut(20);
		b10_1.add(verticalStrut_1_1);

		Box b13_1 = Box.createHorizontalBox();
		b10_1.add(b13_1);

		JLabel lblMatKhauConfirm_1 = new JLabel("Xác nhận mật khẩu:");
		lblMatKhauConfirm_1.setFont(new Font("Dialog", Font.BOLD, 20));
		b13_1.add(lblMatKhauConfirm_1);

		txtMatKhauConfirm = new JPasswordField();
		txtMatKhauConfirm.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtMatKhauConfirm.setColumns(20);
		b13_1.add(txtMatKhauConfirm);

		Component verticalStrut_1_1_1 = Box.createVerticalStrut(15);
		b10_1.add(verticalStrut_1_1_1);

		Box b14_1 = Box.createHorizontalBox();
		b10_1.add(b14_1);

		btnXacNhan_1.setFont(new Font("Dialog", Font.BOLD, 20));
		b14_1.add(btnXacNhan_1);

		Component verticalStrut = Box.createVerticalStrut(15);
		panelThree.add(verticalStrut);

		Box b10_2 = Box.createVerticalBox();
		b10_2.setBorder(new TitledBorder(null, "Thông tin nhân viên", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelThree.add(b10_2);

		Box b11 = Box.createHorizontalBox();
		b10_2.add(b11);

		JLabel lblMaNV = new JLabel("Mã nhân viên: ");
		lblMaNV.setFont(new Font("Dialog", Font.BOLD, 20));
		b11.add(lblMaNV);

		Component horizontalStrut_2_1_1 = Box.createHorizontalStrut(90);
		b11.add(horizontalStrut_2_1_1);

		txtMaNV_2 = new JTextField();
		txtMaNV_2.setEditable(false);
		txtMaNV_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtMaNV_2.setColumns(10);
		b11.add(txtMaNV_2);

		Box b12_2_1 = Box.createHorizontalBox();
		b10_2.add(b12_2_1);

		JLabel lblNewLabel_4_1_2_1 = new JLabel("Mật khẩu: ");
		lblNewLabel_4_1_2_1.setFont(new Font("Dialog", Font.BOLD, 20));
		b12_2_1.add(lblNewLabel_4_1_2_1);

		Component horizontalStrut_2_1_1_1_2_1 = Box.createHorizontalStrut(130);
		b12_2_1.add(horizontalStrut_2_1_1_1_2_1);

		txtMatKhau_2 = new JPasswordField();
		txtMatKhau_2.setEditable(false);
		txtMatKhau_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtMatKhau_2.setColumns(20);
		b12_2_1.add(txtMatKhau_2);

		Box b12_2_1_1_1 = Box.createHorizontalBox();
		b10_2.add(b12_2_1_1_1);

		JLabel lblNewLabel_4_1_2_1_1_1 = new JLabel("Họ tên:");
		lblNewLabel_4_1_2_1_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		b12_2_1_1_1.add(lblNewLabel_4_1_2_1_1_1);

		Component horizontalStrut_2_1_1_1_2_1_1_1 = Box.createHorizontalStrut(164);
		b12_2_1_1_1.add(horizontalStrut_2_1_1_1_2_1_1_1);

		txtHoTen_2 = new JTextField();
		txtHoTen_2.setEditable(false);
		txtHoTen_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtHoTen_2.setColumns(20);
		b12_2_1_1_1.add(txtHoTen_2);

		Box b12_2_1_1 = Box.createHorizontalBox();
		b10_2.add(b12_2_1_1);

		JLabel lblNewLabel_4_1_2_1_1 = new JLabel("Ngày sinh:");
		lblNewLabel_4_1_2_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		b12_2_1_1.add(lblNewLabel_4_1_2_1_1);

		Component horizontalStrut_2_1_1_1_2_1_1 = Box.createHorizontalStrut(130);
		b12_2_1_1.add(horizontalStrut_2_1_1_1_2_1_1);

		txtNgaySinh_2 = new JTextField();
		txtNgaySinh_2.setEditable(false);
		txtNgaySinh_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtNgaySinh_2.setColumns(20);
		b12_2_1_1.add(txtNgaySinh_2);

		Box b12 = Box.createHorizontalBox();
		b10_2.add(b12);

		JLabel lblNewLabel_4_1 = new JLabel("Số điện thoại:");
		lblNewLabel_4_1.setFont(new Font("Dialog", Font.BOLD, 20));
		b12.add(lblNewLabel_4_1);

		Component horizontalStrut_2_1_1_1 = Box.createHorizontalStrut(100);
		b12.add(horizontalStrut_2_1_1_1);

		txtSDT_2 = new JTextField();
		txtSDT_2.setEditable(false);
		txtSDT_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtSDT_2.setColumns(20);
		b12.add(txtSDT_2);

		Box b12_2 = Box.createHorizontalBox();
		b10_2.add(b12_2);

		JLabel lblNewLabel_4_1_2 = new JLabel("CCCD:");
		lblNewLabel_4_1_2.setFont(new Font("Dialog", Font.BOLD, 20));
		b12_2.add(lblNewLabel_4_1_2);

		Component horizontalStrut_2_1_1_1_2 = Box.createHorizontalStrut(175);
		b12_2.add(horizontalStrut_2_1_1_1_2);

		txtCCCD = new JTextField();
		txtCCCD.setEditable(false);
		txtCCCD.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtCCCD.setColumns(20);
		b12_2.add(txtCCCD);
		panelThree.setVisible(false);

		// Main menu handler
		menuPanelItem1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOne.setVisible(true);
				panelTwo.setVisible(false);
				panelThree.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelItem1.setBackground(new Color(246, 97, 81));
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelItem1.setBackground(new Color(36, 41, 49));
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		menuPanelItem2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOne.setVisible(false);
				panelTwo.setVisible(true);
				panelThree.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelItem2.setBackground(new Color(246, 97, 81));
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelItem2.setBackground(new Color(36, 41, 49));
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		menuPanelItem3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOne.setVisible(false);
				panelTwo.setVisible(false);
				panelThree.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelItem3.setBackground(new Color(246, 97, 81));
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelItem3.setBackground(new Color(36, 41, 49));
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		loadDataToPanelThree();
	}

	// Prevent editing on all cells
	private void createTable() {

		String[] tblCols = { "Mã phòng", "Mã loại phòng", "Giá phòng", "Tình trạng", "Ghi chú" };
		model = new DefaultTableModel(tblCols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tbl = new JTable(model);

		rowSorter = new TableRowSorter<>(model);
		tbl.setRowSorter(rowSorter);

		Phong_DAO hotels = new Phong_DAO();
		List<Phong> list = hotels.getAllPhong();

		for (Phong p : list) {
			String tinhTrang = p.getTinhTrang() ? "Đã thuê" : "Còn trống";
			model.addRow(new Object[] { p.getMaPhong(), p.getMaLoai(), p.getGiaPhong(), tinhTrang, p.getGhiChu() });
		}

		panelTwo.setLayout(null);
	}

	private void refreshTable() {
		model.setRowCount(0);
		Phong_DAO hotels = new Phong_DAO();
		List<Phong> list = hotels.getAllPhong();

		for (Phong p : list) {
			String tinhTrang = p.getTinhTrang() ? "Đã thuê" : "Còn trống";
			model.addRow(new Object[] { p.getMaPhong(), p.getMaLoai(), Double.toString(p.getGiaPhong()), tinhTrang,
					p.getGhiChu() });
		}
	}

	private void loadDataToPanelThree() {
		NhanVien_DAO nvDAO = new NhanVien_DAO();

		NhanVien nv = nvDAO.getNhanVienByID(currentMaNV);

		txtMaNV_2.setText(nv.getMaNV());
		txtMatKhau_2.setText(nv.getMatKhau());
		txtHoTen_2.setText(nv.getTenNV());
		txtNgaySinh_2.setText(nv.getNgaySinh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		txtSDT_2.setText(nv.getSdt());
		txtCCCD.setText(nv.getCCCD());
	}

	private void clearInputs() {
		txtMaPhong.setText("");
		txtGiaPhong.setText("");
		txtGhiChu.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnThem)) {
			try {
				Phong_DAO hotels = new Phong_DAO();

				if (hotels.getPhongByID(txtMaPhong.getText().trim()) != null) {
					throw new Exception("Mã phòng đã tồn tại");
				}

				if (!txtGiaPhong.getText().matches("\\d+(\\.\\d+)?"))
					throw new Exception("Giá phòng phải là số");

				Phong p = new Phong(txtMaPhong.getText(), "L001", false, Double.parseDouble(txtGiaPhong.getText()),
						txtGhiChu.getText());

				hotels.addPhong(p);
				clearInputs();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
				refreshTable();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Lỗi: " + e2.getMessage());
			}
		} else if (o.equals(btnXoa)) {
			int row = tbl.getSelectedRow();
			if (row == -1)
				JOptionPane.showMessageDialog(this, "Phải chọn một dòng để xoá");
			else {
				if (JOptionPane.showConfirmDialog(this,
						"Bạn có chắc chắn xoá phòng " + tbl.getModel().getValueAt(row, 0).toString() + " không ?",
						"Cảnh Báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					Phong_DAO hotels = new Phong_DAO();
					hotels.deletePhongByID(tbl.getModel().getValueAt(row, 0).toString());
					clearInputs();
					refreshTable();
					JOptionPane.showMessageDialog(this, "Đã xoá thành công");
				}
			}
		} else if (o.equals(btnLuu)) {
			Phong_DAO hotels = new Phong_DAO();
			LoaiPhong_DAO loaiPhongDAO = new LoaiPhong_DAO();
			int row = tbl.getSelectedRow();
			if (row != -1) {
				try {
					String maLoai = loaiPhongDAO.getMaFromLoai(cboLoaiPhong.getSelectedItem().toString(),
							cboChatLuong.getSelectedItem().toString());

					if (!txtGiaPhong.getText().matches("\\d+(\\.\\d+)?"))
						throw new Exception("Giá phòng phải là số");

					Phong p = new Phong(txtMaPhong.getText(), maLoai, chkDatPhong.isSelected(),
							Double.parseDouble(txtGiaPhong.getText()), txtGhiChu.getText());

					hotels.editPhongByID(model.getValueAt(row, 0).toString(), p);
					clearInputs();
					JOptionPane.showMessageDialog(null, "Cập nhật thành công");
					refreshTable();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Lỗi: " + e2.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Chọn một phòng để cập nhật");
			}
		} else if (o.equals(btnHuy)) {
			clearInputs();

		} else if (o.equals(btnTimKiem)) {
			String inputValue = JOptionPane.showInputDialog(null, "Nhập mã phòng cần tìm: ");

			if (!inputValue.equals("")) {
				Phong_DAO hotels = new Phong_DAO();
				Phong p = hotels.getPhongByID(inputValue);

				if (p != null) {
					for (int i = 0; i < tbl.getRowCount(); i++) {
						if (tbl.getValueAt(i, 0).toString().equalsIgnoreCase(inputValue)) {
							tbl.setRowSelectionInterval(i, i);
							break;
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy phòng");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Mã phòng không được để trống");
			}
		} else if (o.equals(cboFilterLoaiPhong) || o.equals(cboFilterChatLuong)) {
			LoaiPhong_DAO loaiPhongDAO = new LoaiPhong_DAO();

			String maLoai = loaiPhongDAO.getMaFromLoai(cboFilterLoaiPhong.getSelectedItem().toString(),
					cboFilterChatLuong.getSelectedItem().toString());

			rowSorter.setRowFilter(RowFilter.regexFilter(maLoai, 1));
		} else if (o.equals(cboFilterTinhTrang)) {
			if (cboFilterTinhTrang.getSelectedIndex() == 0) {
				rowSorter.setRowFilter(RowFilter.regexFilter("Còn trống", 3));
			} else {
				rowSorter.setRowFilter(RowFilter.regexFilter("Đã thuê", 3));
			}
		} else if (o.equals(cboFilterGia)) {
			List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);

			if (cboFilterGia.getSelectedIndex() == 0) {
				sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
			} else if (cboFilterGia.getSelectedIndex() == 1) {
				sortKeys.add(new RowSorter.SortKey(2, SortOrder.DESCENDING));
			}

			rowSorter.setSortKeys(sortKeys);
		} else if (o.equals(btnClearBoLoc)) {
			cboFilterLoaiPhong.setSelectedIndex(0);
			cboFilterChatLuong.setSelectedIndex(0);
			cboFilterTinhTrang.setSelectedIndex(0);
			cboFilterGia.setSelectedIndex(0);
			rowSorter.setRowFilter(null);
			rowSorter.setSortKeys(null);
		} else if (o.equals(btnInHoaDon)) {
			int row = tbl.getSelectedRow();
			Phong_DAO hotels = new Phong_DAO();

			if (row != -1) {
				FormInHoaDon form = new FormInHoaDon(model.getValueAt(row, 0).toString(), currentMaNV);
				form.setVisible(true);

				if (!form.isActive()) {
					// Set lại trạng thái phòng
					try {
						hotels.editPhongByID(model.getValueAt(row, 0).toString(),
								new Phong(model.getValueAt(row, 0).toString(), model.getValueAt(row, 1).toString(),
										true, Double.parseDouble(model.getValueAt(row, 2).toString()),
										model.getValueAt(row, 4).toString()));

						refreshTable();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Chọn một phòng để in hóa đơn");
			}
		} else if (o.equals(btnShow)) {
			isPasswordShown = !isPasswordShown;
			if (isPasswordShown) {
				btnShow.setIcon(new ImageIcon(GUI_NhanVien.class.getResource("/images/eye-custom.png")));
				txtMatKhauOld.setEchoChar((char) 0);

			} else {
				btnShow.setIcon(new ImageIcon(GUI_NhanVien.class.getResource("/images/eye-off-custom.png")));
				txtMatKhauOld.setEchoChar('*');
			}
		} else if (o.equals(btnXacNhan_1)) {
			if (txtMatKhau.getText().equals(txtMatKhauConfirm.getText())) {
				NhanVien_DAO nvDAO = new NhanVien_DAO();
				if (nvDAO.checkLogin(currentMaNV, txtMatKhauOld.getText())) {
					try {
						NhanVien nv = nvDAO.getNhanVienByID(currentMaNV);
						nv.setMatKhau(txtMatKhau.getText());
						nv.setNgaySinh(nv.getNgaySinh().plusDays(2));
						nvDAO.editNhanVienByID(currentMaNV, nv);

						JOptionPane.showMessageDialog(null, "Đã đổi mật khẩu thành công!");

						txtMatKhauOld.setText("");
						txtMatKhau.setText("");
						txtMatKhauConfirm.setText("");

						loadDataToPanelThree();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(null, "Mật khẩu cũ sai, hãy kiểm tra lại!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Mật khẩu xác nhận không khớp, hãy kiểm tra lại!");
			}
		}
	}
}