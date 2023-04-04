package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame implements ActionListener {
	private JPanel panelTwo = new JPanel();
	private JTextField txtMaPhong = new JTextField();
	private JTextField txtGiaPhong = new JTextField();

	private JTextField txtGhiChu = new JTextField();

	private JCheckBox chkTinhTrang = new JCheckBox("Đã đặt");

	private JComboBox<String> cboChatLuong = new JComboBox<String>();
	private JComboBox<String> cboLoaiPhong = new JComboBox<String>();

	private JLabel lblMaPhong = new JLabel("Mã phòng: ");
	private JLabel lblLoaiPhong = new JLabel("Loại phòng: ");
	private JLabel lblGiaPhong = new JLabel("Giá phòng: ");
	private JLabel lblTinhTrang = new JLabel("Tình trạng: ");
	private JLabel lblChatLuong = new JLabel("Chất lượng: ");

	private JLabel lblGhiChu = new JLabel("Ghi chú: ");

	private JButton btnThem = new JButton("Thêm");
	private JButton btnSua = new JButton("Sửa");
	private JButton btnXoa = new JButton("Xóa");
	private JButton btnLuu = new JButton("Lưu");
	private JButton btnHuy = new JButton("Hủy");
	private JButton btnTimKiem = new JButton("Tìm kiếm");

	private JTable tbl;
	private DefaultTableModel model;

	public GUI() {
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
		logoLabel.setIcon(new ImageIcon(GUI.class.getResource("/images/hotel-icon.png")));
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
		lblThngTinPhn.setIcon(new ImageIcon(GUI.class.getResource("/images/info-icon.png")));

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
		lblQunLPhng.setIcon(new ImageIcon(GUI.class.getResource("/images/magnify-icon.png")));
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
		lblThngKCc.setIcon(new ImageIcon(GUI.class.getResource("/images/chart-icon.png")));
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

		panelTwo.setBounds(0, 0, 725, 569);
		panelTwo.setBackground(Color.red);

		// Create table
		createTable();

		JPanel panelThree = new JPanel();
		panelThree.setBounds(0, 0, 725, 569);
		panelThree.setBackground(Color.yellow);
		panelThree.add(new JLabel("Panel three"));
		contentPanel.setLayout(null);

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
		contentPanel.add(panelTwo);

		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(new TitledBorder(null, "Th\u00F4ng tin ph\u00F2ng", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		inputPanel.setBounds(0, 0, 725, 343);
		panelTwo.add(inputPanel);
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

		// TODO: Box Layout
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		Box b6 = Box.createHorizontalBox();
		Box b7 = Box.createHorizontalBox();
		lblMaPhong.setFont(new Font("Dialog", Font.BOLD, 14));

		b1.add(lblMaPhong);
		b1.add(txtMaPhong);
		lblLoaiPhong.setFont(new Font("Dialog", Font.BOLD, 14));

		b2.add(lblLoaiPhong);
		cboLoaiPhong.addItem("Phòng đơn");
		cboLoaiPhong.addItem("Phòng đôi");
		b2.add(cboLoaiPhong);
		lblGiaPhong.setFont(new Font("Dialog", Font.BOLD, 14));

		b3.add(lblGiaPhong);
		b3.add(txtGiaPhong);
		lblTinhTrang.setFont(new Font("Dialog", Font.BOLD, 14));

		b4.add(lblTinhTrang);
		b4.add(chkTinhTrang);
		b4.add(Box.createHorizontalStrut(530));
		lblChatLuong.setFont(new Font("Dialog", Font.BOLD, 14));

		b5.add(lblChatLuong);
		cboChatLuong.addItem("Thường");
		cboChatLuong.addItem("Cao cấp");
		b5.add(cboChatLuong);
		lblGhiChu.setFont(new Font("Dialog", Font.BOLD, 14));

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

		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		b.add(b3);
		b.add(Box.createVerticalStrut(10));
		b.add(b4);
		b.add(Box.createVerticalStrut(10));
		b.add(b5);
		b.add(Box.createVerticalStrut(10));
		b.add(b6);
		b.add(Box.createVerticalStrut(10));
		b.add(b7);
		b.add(Box.createVerticalStrut(50));

		// Set font of labels
		lblMaPhong.setFont(new Font("Arial", Font.PLAIN, 20));
		lblLoaiPhong.setFont(new Font("Arial", Font.PLAIN, 20));
		lblGiaPhong.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTinhTrang.setFont(new Font("Arial", Font.PLAIN, 20));
		lblChatLuong.setFont(new Font("Arial", Font.PLAIN, 20));
		lblGhiChu.setFont(new Font("Arial", Font.PLAIN, 20));

		// Set font size of text fields
		txtMaPhong.setFont(new Font("Arial", Font.PLAIN, 20));
		txtGiaPhong.setFont(new Font("Arial", Font.PLAIN, 20));
		txtGhiChu.setFont(new Font("Arial", Font.PLAIN, 20));
		chkTinhTrang.setFont(new Font("Arial", Font.PLAIN, 20));

		// Set font size of combo box
		cboChatLuong.setFont(new Font("Arial", Font.PLAIN, 20));
		cboLoaiPhong.setFont(new Font("Arial", Font.PLAIN, 20));

		// Button font size
		btnThem.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSua.setFont(new Font("Arial", Font.PLAIN, 15));
		btnXoa.setFont(new Font("Arial", Font.PLAIN, 15));
		btnLuu.setFont(new Font("Arial", Font.PLAIN, 15));
		btnHuy.setFont(new Font("Arial", Font.PLAIN, 15));
		btnTimKiem.setFont(new Font("Arial", Font.PLAIN, 15));

		inputPanel.add(b);
		tbl = new JTable(model);
		JScrollPane tblPane = new JScrollPane(tbl);
		tblPane.setBorder(BorderFactory.createTitledBorder("Thông tin các phòng"));
		tblPane.setBounds(0, 340, 725, 229);

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

		contentPanel.add(panelThree);

		panelOne.setVisible(true);
		panelTwo.setVisible(false);
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
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelItem1.setBackground(new Color(36, 41, 49));
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
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelItem2.setBackground(new Color(36, 41, 49));
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
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelItem3.setBackground(new Color(36, 41, 49));
			}
		});

		// TODO: Table handlers
		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
			}
		});
	}

	private void createTable() {
		String[] tblCols = { "Mã phòng", "Loại phòng", "Giá phòng", "Tình trạng", "Chất lượng", "Ghi chú" };
		model = new DefaultTableModel(tblCols, 0);

		// Add data
		model.addRow(new Object[] { "P001", "Phòng đơn", "1000000", "Trống", "Thường", "Có wifi" });
		model.addRow(new Object[] { "P002", "Phòng đôi", "2000000", "Trống", "Thường", "" });
		model.addRow(new Object[] { "P003", "Phòng đơn", "1000000", "Trống", "Thường", "" });
		model.addRow(new Object[] { "P004", "Phòng đôi", "2000000", "Trống", "Cao cấp", "" });
		model.addRow(new Object[] { "P005", "Phòng đơn", "1000000", "Trống", "Cao cấp", "" });
		model.addRow(new Object[] { "P006", "Phòng đôi", "2000000", "Trống", "Cao cấp", "" });
		model.addRow(new Object[] { "P007", "Phòng đôi", "2000000", "Trống", "Cao cấp", "" });
		model.addRow(new Object[] { "P008", "Phòng đôi", "2000000", "Trống", "Cao cấp", "" });
		model.addRow(new Object[] { "P009", "Phòng đôi", "2000000", "Trống", "Cao cấp", "" });
		model.addRow(new Object[] { "P010", "Phòng đôi", "2000000", "Trống", "Cao cấp", "" });
		model.addRow(new Object[] { "P011", "Phòng đôi", "2000000", "Trống", "Cao cấp", "" });
		panelTwo.setLayout(null);
	}

	public static void main(String[] args) {
		// Connect to SQL Server
//		Connection conn = null;
//
//		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			conn = DriverManager
//					.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TestDB;user=sa;password=!Nguyenvu123");
//
//			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM Hotels");
//			ResultSet rs = pstm.executeQuery();
//
//			while (rs.next()) {
//				System.out.println(rs.getString("maPhong") + " | " + rs.getString("loaiPhong") + " | "
//						+ rs.getDouble("giaPhong") + " | " + rs.getBoolean("tinhTrang") + " | "
//						+ rs.getString("chatLuong") + " | " + rs.getString("ghiChu"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// Create GUI
		GUI g = new GUI();

		g.setTitle("Phần mềm quản lý khách sạn - Nhóm 2");
		g.setSize(1000, 600);
		g.setLocationRelativeTo(null);
		g.setDefaultCloseOperation(EXIT_ON_CLOSE);
		g.setResizable(false);
		g.setVisible(true);
	}

	// TODO: Button handlers
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
	}
}
