package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.Hotels_DAO;
import entity.Hotel;

public class GUI_NhanVien extends JFrame implements ActionListener {
	private final JPanel panelTwo = new JPanel();
	private final JTextField txtMaPhong = new JTextField();
	private final JTextField txtGiaPhong = new JTextField();

	private final JTextField txtGhiChu = new JTextField();

	private final JCheckBox chkTinhTrang = new JCheckBox("Đã đặt");

	private final JComboBox<String> cboChatLuong = new JComboBox<String>();
	private final JComboBox<String> cboLoaiPhong = new JComboBox<String>();
	private final JComboBox<String> cboFilterChatLuong = new JComboBox<String>();
	private final JComboBox<String> cboFilterLoaiPhong = new JComboBox<String>();

	private final JComboBox<String> cboFilterTinhTrang = new JComboBox<String>();
	private final JComboBox<String> cboFilterGia = new JComboBox<String>();

	private final JLabel lblMaPhong = new JLabel("Mã phòng: ");
	private final JLabel lblLoaiPhong = new JLabel("Loại phòng: ");
	private final JLabel lblGiaPhong = new JLabel("Giá phòng: ");
	private final JLabel lblTinhTrang = new JLabel("Tình trạng: ");
	private final JLabel lblChatLuong = new JLabel("Chất lượng: ");

	private final JLabel lblGhiChu = new JLabel("Ghi chú: ");

	private final JButton btnThem = new JButton("Thêm");
	private final JButton btnSua = new JButton("Sửa");
	private final JButton btnXoa = new JButton("Xóa");
	private final JButton btnLuu = new JButton("Lưu");
	private final JButton btnHuy = new JButton("Hủy");
	private final JButton btnTimKiem = new JButton("Tìm kiếm");

	private final JTable tbl;
	private DefaultTableModel model;

	public GUI_NhanVien() {
		setTitle("Phần mềm quản lý khách sạn - Nhóm 2");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// Connect to SQL Server
		try {
			ConnectDB.getInstance().connect();

			System.out.println("Connect success");
		} catch (Exception e) {
			e.printStackTrace();
		}

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

		JLabel lblThngKCc = new JLabel("Thống kê các phòng");
		lblThngKCc.setIcon(new ImageIcon(GUI_NhanVien.class.getResource("/images/chart-icon.png")));
		lblThngKCc.setForeground(new Color(255, 255, 255));
		lblThngKCc.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngKCc.setFont(new Font("Dialog", Font.BOLD, 20));
		menuPanelItem3.add(lblThngKCc);

		JLabel lblNhm = new JLabel("Nhóm 2 - Bài tập lớn Java");
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
		panelThree.setBackground(Color.yellow);
		panelThree.add(new JLabel("Panel three"));
		contentPanel.setLayout(null);

		JPanel panelOne = new JPanel();
		panelOne.setBounds(0, 0, 715, 569);
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

		panelTwo.setBounds(0, 0, 715, 569);
		panelTwo.setBackground(UIManager.getColor("Button.background"));
		contentPanel.add(panelTwo);

		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(new TitledBorder(null, "Th\u00F4ng tin ph\u00F2ng", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		inputPanel.setBounds(0, 0, 715, 284);
		panelTwo.add(inputPanel);
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

		// TODO: Box Layout
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b6 = Box.createHorizontalBox();
		Box b7 = Box.createHorizontalBox();
		Box b8 = Box.createHorizontalBox();
		lblMaPhong.setFont(new Font("Dialog", Font.BOLD, 14));

		b1.add(lblMaPhong);
		b1.add(txtMaPhong);
		lblLoaiPhong.setFont(new Font("Dialog", Font.BOLD, 14));

		b2.add(lblLoaiPhong);
		cboLoaiPhong.addItem("Phòng đơn");
		cboLoaiPhong.addItem("Phòng đôi");
		b2.add(cboLoaiPhong);
		lblGiaPhong.setFont(new Font("Dialog", Font.BOLD, 14));

		b2.add(lblChatLuong);
		cboChatLuong.addItem("Thường");
		cboChatLuong.addItem("Cao cấp");
		b2.add(cboChatLuong);
		lblGhiChu.setFont(new Font("Dialog", Font.BOLD, 14));

		b2.add(lblTinhTrang);
		b2.add(chkTinhTrang);
		lblChatLuong.setFont(new Font("Dialog", Font.BOLD, 14));

		b3.add(lblGiaPhong);
		b3.add(txtGiaPhong);
		lblTinhTrang.setFont(new Font("Dialog", Font.BOLD, 14));

		b6.add(lblGhiChu);
		b6.add(txtGhiChu);

		b7.add(btnThem);
		b7.add(Box.createHorizontalStrut(10));
		b7.add(btnSua);
		b7.add(Box.createHorizontalStrut(10));
		b7.add(btnXoa);
		b7.add(Box.createHorizontalStrut(10));
		b7.add(btnLuu);
		b7.add(Box.createHorizontalStrut(10));
		b7.add(btnHuy);
		b7.add(Box.createHorizontalStrut(10));
		b7.add(btnTimKiem);

		b8.setBorder(BorderFactory.createTitledBorder("Bộ lọc"));
		cboFilterLoaiPhong.addItem("Phòng đơn");
		cboFilterLoaiPhong.addItem("Phòng đôi");
		cboFilterChatLuong.addItem("Thường");
		cboFilterChatLuong.addItem("Cao cấp");

		JLabel lblNewLabel = new JLabel("Loại phòng: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		b8.add(lblNewLabel);
		b8.add(cboFilterChatLuong);
		b8.add(Box.createHorizontalStrut(10));

		JLabel lblNewLabel_1 = new JLabel("Chất lượng: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		b8.add(lblNewLabel_1);
		b8.add(cboFilterLoaiPhong);
		b8.add(Box.createHorizontalStrut(10));

		JLabel lblNewLabel_2 = new JLabel("Tình trạng: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		b8.add(lblNewLabel_2);
		cboFilterTinhTrang.setFont(new Font("Tahoma", Font.PLAIN, 15));

		cboFilterTinhTrang.addItem("Trống");
		cboFilterTinhTrang.addItem("Đã đặt");
		b8.add(cboFilterTinhTrang);
		b8.add(Box.createHorizontalStrut(10));

		JLabel lblNewLabel_3 = new JLabel("Giá: ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		b8.add(lblNewLabel_3);
		cboFilterGia.setFont(new Font("Tahoma", Font.PLAIN, 15));

		cboFilterGia.addItem("Tăng dần");
		cboFilterGia.addItem("Giảm dần");
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
		lblMaPhong.setFont(new Font("Arial", Font.PLAIN, 15));
		lblLoaiPhong.setFont(new Font("Arial", Font.PLAIN, 15));
		lblGiaPhong.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTinhTrang.setFont(new Font("Arial", Font.PLAIN, 15));
		lblChatLuong.setFont(new Font("Arial", Font.PLAIN, 15));
		lblGhiChu.setFont(new Font("Arial", Font.PLAIN, 15));

		// Set font size of text fields
		txtMaPhong.setFont(new Font("Arial", Font.PLAIN, 15));
		txtGiaPhong.setFont(new Font("Arial", Font.PLAIN, 15));
		txtGhiChu.setFont(new Font("Arial", Font.PLAIN, 15));
		chkTinhTrang.setFont(new Font("Arial", Font.PLAIN, 15));

		// Set font size of combo box
		cboChatLuong.setFont(new Font("Arial", Font.PLAIN, 15));
		cboLoaiPhong.setFont(new Font("Arial", Font.PLAIN, 15));
		cboFilterChatLuong.setFont(new Font("Arial", Font.PLAIN, 15));
		cboFilterLoaiPhong.setFont(new Font("Arial", Font.PLAIN, 15));

		// Button font size
		btnThem.setFont(new Font("Arial", Font.BOLD, 15));
		btnSua.setFont(new Font("Arial", Font.BOLD, 15));
		btnXoa.setFont(new Font("Arial", Font.BOLD, 15));
		btnLuu.setFont(new Font("Arial", Font.BOLD, 15));
		btnHuy.setFont(new Font("Arial", Font.BOLD, 15));
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 15));

		inputPanel.add(b);
		panelTwo.setVisible(false);

		tbl = new JTable(model);
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane tblPane = new JScrollPane(tbl);
		tblPane.setBorder(BorderFactory.createTitledBorder("Thông tin các phòng"));
		tblPane.setBounds(0, 285, 715, 284);

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

		panelTwo.add(tblPane);

		// TODO: Table handlers
		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
			}
		});

		contentPanel.add(panelThree);
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
	}

	// Prevent editing on all cells
	private void createTable() {
		String[] tblCols = { "Mã phòng", "Loại phòng", "Giá phòng", "Tình trạng", "Chất lượng", "Ghi chú" };
		model = new DefaultTableModel(tblCols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Add data
		Hotels_DAO hotels = new Hotels_DAO();
		List<Hotel> list = hotels.getAllHotels();

		for (Hotel hotel : list) {
			String tinhTrang = hotel.isTinhTrang() ? "Còn trống" : "Đã thuê";
			model.addRow(new Object[] { hotel.getMaPhong(), hotel.getLoaiPhong(), hotel.getGiaPhong(), tinhTrang,
					hotel.getChatLuong(), hotel.getGhiChu() });
		}

		panelTwo.setLayout(null);
	}

	// TODO: Button handlers
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
	}
}
