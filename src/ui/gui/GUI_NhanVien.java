package ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.LoaiPhong_DAO;
import dao.NhanVien_DAO;
import dao.Phong_DAO;
import entity.NhanVien;
import entity.Phong;
import ui.panels.PanelCheckin;
import ui.panels.PanelCheckout;

public class GUI_NhanVien extends JFrame implements ActionListener {
	private final JPanel panelRoom = new JPanel();
	private final JTextField txtMaPhong = new JTextField();
	private final JTextField txtGiaPhong = new JTextField();
	private final JTextField txtGhiChu = new JTextField();
	private final JComboBox<String> cboChatLuong = new JComboBox<String>();
	private final JComboBox<String> cboLoaiPhong = new JComboBox<String>();
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
	private final JButton btnXacNhan_1 = new JButton("Xác nhận");
	private final JButton btnShow = new JButton("");
	private boolean isPasswordShown = false;
	private String currentMaNV = "";
	private JTable tbl;
	private DefaultTableModel model;

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
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(new ImageIcon(GUI_NhanVien.class.getResource("/images/hotels-icon.png")).getImage());

		this.currentMaNV = maNV;

		getContentPane().setLayout(null);

		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		leftPanel.setBackground(new Color(204, 204, 255));
		leftPanel.setLocation(0, 0);
		leftPanel.setSize(271, 669);
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
		menuPanel.setBounds(0, 90, 271, 579);
		leftPanel.add(menuPanel);
		menuPanel.setLayout(null);

		JPanel menuPanelInfo = new JPanel();
		menuPanelInfo.setBackground(new Color(36, 31, 49));
		menuPanelInfo.setBounds(0, 30, 271, 80);
		menuPanel.add(menuPanelInfo);
		menuPanelInfo.setLayout(new BorderLayout(0, 0));

		JLabel lblThngTinPhn = new JLabel("Thông tin phần mềm");
		lblThngTinPhn.setIcon(new ImageIcon(GUI_NhanVien.class.getResource("/images/info-icon.png")));

		lblThngTinPhn.setForeground(new Color(255, 255, 255));
		lblThngTinPhn.setHorizontalAlignment(SwingConstants.CENTER);
		menuPanelInfo.add(lblThngTinPhn, BorderLayout.CENTER);
		lblThngTinPhn.setFont(new Font("Dialog", Font.BOLD, 20));

		JPanel menuPanelRoom = new JPanel();
		menuPanelRoom.setForeground(Color.WHITE);
		menuPanelRoom.setBackground(new Color(36, 31, 49));
		menuPanelRoom.setBounds(0, 130, 271, 80);
		menuPanel.add(menuPanelRoom);
		menuPanelRoom.setLayout(new BorderLayout(0, 0));

		JLabel lblQunLPhng_2 = new JLabel("Quản lý phòng");
		lblQunLPhng_2.setIcon(new ImageIcon(GUI_NhanVien.class.getResource("/images/magnify-icon.png")));
		lblQunLPhng_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLPhng_2.setForeground(Color.WHITE);
		lblQunLPhng_2.setFont(new Font("Dialog", Font.BOLD, 20));
		menuPanelRoom.add(lblQunLPhng_2, BorderLayout.CENTER);

		JPanel menuPanelCheckin = new JPanel();
		menuPanelCheckin.setForeground(new Color(255, 255, 255));
		menuPanelCheckin.setBackground(new Color(36, 31, 49));
		menuPanelCheckin.setBounds(0, 230, 271, 80);
		menuPanel.add(menuPanelCheckin);
		menuPanelCheckin.setLayout(new BorderLayout(0, 0));

		JLabel lblQunLPhng = new JLabel("Check-in");
		lblQunLPhng.setIcon(new ImageIcon(GUI_NhanVien.class.getResource("/images/door-closed-custom.png")));
		lblQunLPhng.setForeground(new Color(255, 255, 255));
		lblQunLPhng.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLPhng.setFont(new Font("Dialog", Font.BOLD, 20));
		menuPanelCheckin.add(lblQunLPhng);

		JPanel menuPanelCheckout = new JPanel();
		menuPanelCheckout.setForeground(Color.WHITE);
		menuPanelCheckout.setBackground(new Color(36, 31, 49));
		menuPanelCheckout.setBounds(0, 330, 271, 80);
		menuPanel.add(menuPanelCheckout);
		menuPanelCheckout.setLayout(new BorderLayout(0, 0));

		JLabel lblCheckout = new JLabel("Check-out");
		lblCheckout.setIcon(new ImageIcon(GUI_NhanVien.class.getResource("/images/door-open-custom.png")));
		lblCheckout.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckout.setForeground(Color.WHITE);
		lblCheckout.setFont(new Font("Dialog", Font.BOLD, 20));
		menuPanelCheckout.add(lblCheckout, BorderLayout.CENTER);

		JPanel menuPanelAdjustInfo = new JPanel();
		menuPanelAdjustInfo.setBackground(new Color(36, 31, 49));
		menuPanelAdjustInfo.setBounds(0, 430, 271, 80);
		menuPanel.add(menuPanelAdjustInfo);
		menuPanelAdjustInfo.setLayout(new BorderLayout(0, 0));

		JLabel lblThngKCc = new JLabel("Chỉnh sửa thông tin");
		lblThngKCc.setIcon(new ImageIcon(GUI_NhanVien.class.getResource("/images/account-cog-custom.png")));
		lblThngKCc.setForeground(new Color(255, 255, 255));
		lblThngKCc.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngKCc.setFont(new Font("Dialog", Font.BOLD, 20));
		menuPanelAdjustInfo.add(lblThngKCc);

		JLabel lblNhm = new JLabel("Welcome, " + currentMaNV);
		lblNhm.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhm.setBounds(0, 620, 271, 28);
		leftPanel.add(lblNhm);
		lblNhm.setFont(new Font("Dialog", Font.BOLD, 20));

		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(271, 0, 725, 669);
		getContentPane().add(contentPanel);

		// Create table
		createTable();

		JPanel panelAdjustInfo = new JPanel();
		panelAdjustInfo.setBounds(0, 0, 725, 669);
		panelAdjustInfo.setBackground(UIManager.getColor("Button.background"));
		contentPanel.setLayout(null);

		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Set size of columns and rows
		tbl.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(120);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(120);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(80);
		tbl.setRowHeight(30);

		// Set font size of table
		tbl.setFont(new Font("Arial", Font.PLAIN, 20));

		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoaiPhong_DAO lpDAO = new LoaiPhong_DAO();
				String maLoai = tbl.getValueAt(tbl.getSelectedRow(), 1).toString();
				int row = tbl.getSelectedRow();

				txtMaPhong.setText(tbl.getValueAt(row, 0).toString());
				cboLoaiPhong.setSelectedItem(lpDAO.getLoaiPhongByID(maLoai).getTenLoai());
				cboChatLuong.setSelectedItem(lpDAO.getLoaiPhongByID(maLoai).getChatLuong());
				txtGiaPhong.setText(tbl.getValueAt(row, 2).toString());
				txtGhiChu.setText(tbl.getValueAt(row, 3).toString());
			}
		});

		JPanel panelInfo = new JPanel();
		panelInfo.setBounds(0, 0, 725, 669);
		panelInfo.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 12, 725, 68);
		panelInfo.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lblPhnMmQun = new JLabel("Phần mềm quản lý khách sạn");
		panel.add(lblPhnMmQun);
		lblPhnMmQun.setForeground(new Color(237, 51, 59));
		lblPhnMmQun.setFont(new Font("Dialog", Font.BOLD, 30));

		contentPanel.add(panelInfo);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 72, 725, 45);
		panelInfo.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNhm_1 = new JLabel("Nhóm 2:");
		panel_1.add(lblNhm_1);
		lblNhm_1.setFont(new Font("Dialog", Font.BOLD, 25));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 135, 725, 45);
		panelInfo.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNhm_1_1 = new JLabel("Trần Ngọc Phát");
		panel_2.add(lblNhm_1_1);
		lblNhm_1_1.setFont(new Font("Dialog", Font.BOLD, 25));

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 205, 725, 45);
		panelInfo.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNhm_1_1_1 = new JLabel("Trần Nguyên Vũ");
		panel_3.add(lblNhm_1_1_1);
		lblNhm_1_1_1.setFont(new Font("Dialog", Font.BOLD, 25));

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 275, 725, 45);
		panelInfo.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNhm_1_1_1_1 = new JLabel("Mai Nhật Hào");
		panel_4.add(lblNhm_1_1_1_1);
		lblNhm_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 25));

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 345, 725, 45);
		panelInfo.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNhm_1_1_1_1_1 = new JLabel("Võ Phước Hậu");
		panel_5.add(lblNhm_1_1_1_1_1);
		lblNhm_1_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 25));

		panelInfo.setVisible(true);
		lblTinhTrang.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTinhTrang.setFont(new Font("Arial", Font.PLAIN, 15));

		panelRoom.setBounds(0, 0, 725, 669);
		panelRoom.setBackground(UIManager.getColor("Button.background"));
		contentPanel.add(panelRoom);

		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(
				new TitledBorder(null, "Thông tin phòng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		inputPanel.setBounds(0, 0, 715, 284);
		panelRoom.add(inputPanel);
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

		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		b.add(b3);
		b.add(Box.createVerticalStrut(10));
		b.add(b6);
		b.add(Box.createVerticalStrut(10));
		b.add(b7);
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

		// Button font size
		btnThem.setFont(new Font("Arial", Font.BOLD, 15));
		btnXoa.setFont(new Font("Arial", Font.BOLD, 15));
		btnLuu.setFont(new Font("Arial", Font.BOLD, 15));
		btnHuy.setFont(new Font("Arial", Font.BOLD, 15));
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 15));

		inputPanel.add(b);
		panelRoom.setVisible(false);
		JScrollPane tblPane = new JScrollPane(tbl);
		tblPane.setBorder(BorderFactory.createTitledBorder("Thông tin các phòng"));
		tblPane.setBounds(0, 285, 715, 384);

		panelRoom.add(tblPane);

		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnXacNhan_1.addActionListener(this);

		contentPanel.add(panelAdjustInfo);
		panelAdjustInfo.setLayout(new BoxLayout(panelAdjustInfo, BoxLayout.Y_AXIS));

		Box b10_1 = Box.createVerticalBox();
		b10_1.setBorder(
				new TitledBorder(null, "Chỉnh sửa thông tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAdjustInfo.add(b10_1);

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
		panelAdjustInfo.add(verticalStrut);

		Box b10_2 = Box.createVerticalBox();
		b10_2.setBorder(
				new TitledBorder(null, "Thông tin nhân viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAdjustInfo.add(b10_2);

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

		PanelCheckin panelCheckin = new PanelCheckin(currentMaNV);
		panelCheckin.setBounds(0, 0, 725, 669);
		contentPanel.add(panelCheckin);

		PanelCheckout panelCheckout = new PanelCheckout(currentMaNV);
		panelCheckout.setSize(725, 619);
		contentPanel.add(panelCheckout);

		panelAdjustInfo.setVisible(false);
		panelCheckin.setVisible(false);
		panelCheckout.setVisible(false);

		// Main menu handler
		menuPanelInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelInfo.setVisible(true);
				panelRoom.setVisible(false);
				panelAdjustInfo.setVisible(false);
				panelCheckout.setVisible(false);
				panelCheckin.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelInfo.setBackground(new Color(246, 97, 81));
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelInfo.setBackground(new Color(36, 41, 49));
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		menuPanelRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelInfo.setVisible(false);
				panelRoom.setVisible(true);
				panelAdjustInfo.setVisible(false);
				panelCheckout.setVisible(false);
				panelCheckin.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelRoom.setBackground(new Color(246, 97, 81));
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelRoom.setBackground(new Color(36, 41, 49));
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		menuPanelCheckin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelInfo.setVisible(false);
				panelRoom.setVisible(false);
				panelAdjustInfo.setVisible(false);
				panelCheckout.setVisible(false);

				panelCheckin.setVisible(true);
				panelCheckin.refreshTable();

				refreshTable();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelCheckin.setBackground(new Color(246, 97, 81));
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelCheckin.setBackground(new Color(36, 41, 49));
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		menuPanelCheckout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelInfo.setVisible(false);
				panelRoom.setVisible(false);
				panelAdjustInfo.setVisible(false);
				panelCheckout.setVisible(true);
				panelCheckout.refreshTable();
				panelCheckin.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelCheckout.setBackground(new Color(246, 97, 81));
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelCheckout.setBackground(new Color(36, 41, 49));
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		menuPanelAdjustInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelInfo.setVisible(false);
				panelRoom.setVisible(false);
				panelAdjustInfo.setVisible(true);
				panelCheckout.setVisible(false);
				panelCheckin.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelAdjustInfo.setBackground(new Color(246, 97, 81));
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelAdjustInfo.setBackground(new Color(36, 41, 49));
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		loadDataToPanelThree();
	}

	// Prevent editing on all cells
	private void createTable() {

		String[] tblCols = { "Mã phòng", "Mã loại phòng", "Giá phòng", "Ghi chú" };
		model = new DefaultTableModel(tblCols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tbl = new JTable(model);

		Phong_DAO hotels = new Phong_DAO();
		List<Phong> list = hotels.getAllPhong();

		for (Phong p : list) {
			model.addRow(new Object[] { p.getMaPhong(), p.getMaLoai(), p.getGiaPhong(), p.getGhiChu() });
		}

		panelRoom.setLayout(null);
	}

	private void refreshTable() {
		model.setRowCount(0);
		Phong_DAO hotels = new Phong_DAO();
		List<Phong> list = hotels.getAllPhong();

		for (Phong p : list) {
			model.addRow(
					new Object[] { p.getMaPhong(), p.getMaLoai(), Double.toString(p.getGiaPhong()), p.getGhiChu() });
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

				Phong p = new Phong(txtMaPhong.getText(), "L001", Double.parseDouble(txtGiaPhong.getText()),
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

					Phong p = new Phong(txtMaPhong.getText(), maLoai, Double.parseDouble(txtGiaPhong.getText()),
							txtGhiChu.getText());

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
						nv.setNgaySinh(nv.getNgaySinh());
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