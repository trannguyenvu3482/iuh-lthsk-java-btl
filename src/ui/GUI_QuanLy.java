package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import dao.NhanVien_DAO;
import dao.Phong_DAO;
import entity.NhanVien;
import entity.Phong;

public class GUI_QuanLy extends JFrame implements ActionListener {
	private final JPanel panelTwo = new JPanel();
	private final JTextField txtMaNV = new JTextField();
	private final JTextField txtHoTen = new JTextField();

	private final JTextField txtNgaySinh = new JTextField();

	private final JLabel lblMaNV = new JLabel("Mã nhân viên:");
	private final JLabel lblMatKhau = new JLabel("Mật khẩu:");
	private final JLabel lblHoTen = new JLabel("Họ tên:");
	private final JLabel lblTinhTrang = new JLabel("Tình trạng: ");

	private final JLabel lblNgaySinh = new JLabel("Ngày sinh:");

	private final JButton btnThem = new JButton("Thêm");
	private final JButton btnXoa = new JButton("Xóa");
	private final JButton btnLuu = new JButton("Lưu");
	private final JButton btnHuy = new JButton("Xóa trắng");
	private final JButton btnTimKiem = new JButton("Tìm kiếm");

	private final JTable tbl;
	private DefaultTableModel model;
	private JTextField txtMatKhau;
	private JTextField txtSDT;
	private JTextField txtCCCD;

	public GUI_QuanLy() {
		setTitle("Phần mềm quản lý khách sạn - Nhóm 2");
		setSize(1200, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// Connect to SQL Server
//        try {
//            ConnectDB.getInstance().connect();
//
//            System.out.println("Connect success");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

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
		logoLabel.setIcon(new ImageIcon(GUI_QuanLy.class.getResource("/images/hotel-icon.png")));
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
		lblThngTinPhn.setIcon(new ImageIcon(GUI_QuanLy.class.getResource("/images/info-icon.png")));

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

		JLabel lblQunLPhng = new JLabel("Quản lý nhân viên");
		lblQunLPhng.setIcon(new ImageIcon(GUI_QuanLy.class.getResource("/images/magnify-icon.png")));
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
		lblThngKCc.setIcon(new ImageIcon(GUI_QuanLy.class.getResource("/images/chart-icon.png")));
		lblThngKCc.setForeground(new Color(255, 255, 255));
		lblThngKCc.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngKCc.setFont(new Font("Dialog", Font.BOLD, 20));
		menuPanelItem3.add(lblThngKCc);

		JLabel lblNhm = new JLabel("Welcome, Admin");
		lblNhm.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNhm.setBounds(12, 439, 259, 28);
		menuPanel.add(lblNhm);

		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(271, 0, 925, 569);
		getContentPane().add(contentPanel);

		// Create table
		createTable();

		JPanel panelThree = new JPanel();
		panelThree.setBounds(0, 0, 925, 569);
		panelThree.setBackground(Color.yellow);
		panelThree.add(new JLabel("Panel three"));
		contentPanel.setLayout(null);

		panelTwo.setBounds(0, 0, 925, 569);
		panelTwo.setBackground(UIManager.getColor("Button.background"));
		contentPanel.add(panelTwo);

		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(
				new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Th\u00F4ng tin nh\u00E2n vi\u00EAn",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		inputPanel.setBounds(0, 0, 924, 193);
		panelTwo.add(inputPanel);
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

		// TODO: Box Layout
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		lblMaNV.setFont(new Font("Dialog", Font.PLAIN, 20));

		b1.add(lblMaNV);
		txtMaNV.setFont(new Font("Dialog", Font.PLAIN, 20));
		b1.add(txtMaNV);
		lblHoTen.setFont(new Font("Dialog", Font.PLAIN, 20));

		b2.add(lblHoTen);

		Component horizontalStrut_1 = Box.createHorizontalStrut(60);
		b2.add(horizontalStrut_1);
		txtHoTen.setFont(new Font("Dialog", Font.PLAIN, 20));
		b2.add(txtHoTen);

		b4.add(btnThem);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(btnXoa);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(btnLuu);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(btnHuy);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(btnTimKiem);

		JLabel lblSDT = new JLabel("SDT:");
		lblSDT.setFont(new Font("Dialog", Font.PLAIN, 20));
		b3.add(lblSDT);

		Component horizontalStrut_1_1 = Box.createHorizontalStrut(85);
		b3.add(horizontalStrut_1_1);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Dialog", Font.PLAIN, 20));
		b3.add(txtSDT);
		txtSDT.setColumns(10);
		b3.add(Box.createHorizontalStrut(22));

		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setFont(new Font("Dialog", Font.PLAIN, 20));
		b3.add(lblCCCD);

		Component horizontalStrut_1_4 = Box.createHorizontalStrut(40);
		b3.add(horizontalStrut_1_4);

		txtCCCD = new JTextField();
		txtCCCD.setFont(new Font("Dialog", Font.PLAIN, 20));
		b3.add(txtCCCD);
		txtCCCD.setColumns(10);

		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);

		Component horizontalStrut_1_3 = Box.createHorizontalStrut(25);
		b2.add(horizontalStrut_1_3);
		b2.add(lblNgaySinh);
		lblNgaySinh.setFont(new Font("Dialog", Font.PLAIN, 20));
		b2.add(txtNgaySinh);
		txtNgaySinh.setFont(new Font("Dialog", Font.PLAIN, 20));
		b.add(Box.createVerticalStrut(10));
		b.add(b3);

		b.add(Box.createVerticalStrut(10));
		b.add(b4);
		b.add(Box.createVerticalStrut(20));

		Component horizontalStrut_1_2 = Box.createHorizontalStrut(210);
		b1.add(horizontalStrut_1_2);
		lblMatKhau.setFont(new Font("Dialog", Font.PLAIN, 20));

		b1.add(lblMatKhau);

		Component horizontalStrut_1_2_1 = Box.createHorizontalStrut(10);
		b1.add(horizontalStrut_1_2_1);

		txtMatKhau = new JTextField();
		txtMatKhau.setFont(new Font("Dialog", Font.PLAIN, 20));
		b1.add(txtMatKhau);
		txtMatKhau.setColumns(10);

		// Button font size
		btnThem.setFont(new Font("Arial", Font.BOLD, 15));
		btnXoa.setFont(new Font("Arial", Font.BOLD, 15));
		btnLuu.setFont(new Font("Arial", Font.BOLD, 15));
		btnHuy.setFont(new Font("Arial", Font.BOLD, 15));
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 15));

		inputPanel.add(b);
		panelTwo.setVisible(false);

		tbl = new JTable(model);
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane tblPane = new JScrollPane(tbl);
		tblPane.setBorder(BorderFactory.createTitledBorder("Thông tin các nhân viên"));
		tblPane.setBounds(0, 189, 924, 380);

		// Set size of columns and rows
		tbl.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(80);
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
				txtMaNV.setText(tbl.getValueAt(tbl.getSelectedRow(), 0).toString());
				txtMatKhau.setText(tbl.getValueAt(tbl.getSelectedRow(), 1).toString());
				txtHoTen.setText(tbl.getValueAt(tbl.getSelectedRow(), 2).toString());
				txtNgaySinh.setText(tbl.getValueAt(tbl.getSelectedRow(), 3).toString());
				txtSDT.setText(tbl.getValueAt(tbl.getSelectedRow(), 4).toString());
				txtCCCD.setText(tbl.getValueAt(tbl.getSelectedRow(), 5).toString());
			}
		});

		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		btnTimKiem.addActionListener(this);

		JPanel panelOne = new JPanel();
		panelOne.setBounds(0, 0, 925, 569);
		panelOne.setLayout(null);
		JLabel lblPhnMmQun = new JLabel("Phần mềm quản lý khách sạn");
		lblPhnMmQun.setForeground(new Color(237, 51, 59));
		lblPhnMmQun.setFont(new Font("Dialog", Font.BOLD, 30));
		lblPhnMmQun.setBounds(231, 12, 441, 68);
		panelOne.add(lblPhnMmQun);

		contentPanel.add(panelOne);

		JLabel lblNhm_1 = new JLabel("Nhóm 2:");
		lblNhm_1.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNhm_1.setBounds(375, 78, 112, 45);
		panelOne.add(lblNhm_1);

		JLabel lblNhm_1_1 = new JLabel("Trần Ngọc Phát - 12345678");
		lblNhm_1_1.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNhm_1_1.setBounds(260, 135, 331, 45);
		panelOne.add(lblNhm_1_1);

		JLabel lblNhm_1_1_1 = new JLabel("Trần Nguyên Vũ - 12345678");
		lblNhm_1_1_1.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNhm_1_1_1.setBounds(255, 205, 356, 45);
		panelOne.add(lblNhm_1_1_1);

		JLabel lblNhm_1_1_1_1 = new JLabel("Mai Nhật Hào - 12345678");
		lblNhm_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNhm_1_1_1_1.setBounds(290, 275, 331, 45);
		panelOne.add(lblNhm_1_1_1_1);

		JLabel lblNhm_1_1_1_1_1 = new JLabel("Võ Phước Hậu - 12345678");
		lblNhm_1_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNhm_1_1_1_1_1.setBounds(290, 345, 331, 45);
		panelOne.add(lblNhm_1_1_1_1_1);

		panelOne.setVisible(true);
		lblTinhTrang.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTinhTrang.setFont(new Font("Arial", Font.PLAIN, 15));

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
		String[] tblCols = { "Mã nhân viên", "Mật khẩu", "Họ tên", "Ngày sinh", "Số điện thoại", "CCCD" };
		model = new DefaultTableModel(tblCols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// TODO: Add data
		NhanVien_DAO nvDAO = new NhanVien_DAO();
		List<NhanVien> list = nvDAO.getAllNhanVien();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		for (NhanVien p : list) {
			model.addRow(new Object[] { p.getMaNV(), p.getMatKhau(), p.getTenNV(), dtf.format(p.getNgaySinh()),
					p.getSdt(), p.getCCCD() });
		}

		panelTwo.setLayout(null);
	}

	private void refreshTable() {
		model.setRowCount(0);

		NhanVien_DAO nvDAO = new NhanVien_DAO();
		List<NhanVien> list = nvDAO.getAllNhanVien();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		for (NhanVien p : list) {
			model.addRow(new Object[] { p.getMaNV(), p.getMatKhau(), p.getTenNV(), dtf.format(p.getNgaySinh()),
					p.getSdt(), p.getCCCD() });
		}
	}

	private void clearInputs() {
		txtMaNV.setText("");
		txtHoTen.setText("");
		txtNgaySinh.setText("");
	}

	// TODO: Button handlers
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnThem)) {
			try {
				NhanVien_DAO nvDAO = new NhanVien_DAO();

				NhanVien nv = new NhanVien(txtMaNV.getText(), txtMatKhau.getText(), txtHoTen.getText(),
						LocalDate.parse(txtNgaySinh.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
						txtSDT.getText(), txtCCCD.getText());

				nvDAO.addNhanVien(nv);
				clearInputs();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
				refreshTable();
			} catch (Exception e2) {
				if (e2.getMessage().startsWith("For input string: ")) {
					JOptionPane.showMessageDialog(null, "Lỗi: Number must be double");
				} else {
					JOptionPane.showMessageDialog(null, "Lỗi: " + e2.getMessage());
				}
			}
		} else if (o.equals(btnXoa)) {

		} else if (o.equals(btnLuu)) {
			// TODO: Code here
		} else if (o.equals(btnHuy)) {
			clearInputs();
		} else if (o.equals(btnTimKiem)) {
			String inputValue = JOptionPane.showInputDialog(null, "Nhập mã phòng cần tìm: ");

			if (inputValue != null && !inputValue.equals("")) {
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
		}
	}
}
