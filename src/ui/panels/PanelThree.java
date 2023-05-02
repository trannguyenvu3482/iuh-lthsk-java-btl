package ui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.HoaDon_DAO;
import entity.HoaDon;
import ui.forms.HoaDonDialog;

public class PanelThree extends JPanel implements ActionListener {
	private String soLuongHD;
	private double tongTien;
	private JTable tbl;
	private DefaultTableModel model;
	private JTextField txtTuNgay;
	private JTextField txtDenNgay;
	private JTextField txtPhong;
	private JTextField txtNhanVien;
	private JLabel lblSoLuongHoaDon = new JLabel("");

	private JLabel lblTongTien = new JLabel("");

	private JButton btnXoaBoLoc = new JButton("Xóa mọi bộ lọc");

	private TableRowSorter<DefaultTableModel> rowSorter;

	private RowFilter<DefaultTableModel, Integer> dateFilter = null;

	private RowFilter<DefaultTableModel, Integer> roomFilter = null;

	private RowFilter<DefaultTableModel, Integer> staffFilter = null;

	public PanelThree() {
		// TODO Auto-generated constructor stub
		setBounds(0, 0, 925, 569);
		setBackground(UIManager.getColor("Button.background"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		createTable();

		Box b = Box.createVerticalBox();
		add(b);

		Component verticalStrut = Box.createVerticalStrut(20);
		b.add(verticalStrut);

		Box b1 = Box.createHorizontalBox();
		b.add(b1);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		b1.add(horizontalStrut_2);

		JLabel lblTuNgay = new JLabel("Từ ngày:");
		lblTuNgay.setFont(new Font("Dialog", Font.BOLD, 20));
		b1.add(lblTuNgay);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		b1.add(horizontalStrut);

		txtTuNgay = new JTextField();
		txtTuNgay.setFont(new Font("Dialog", Font.PLAIN, 20));
		b1.add(txtTuNgay);
		txtTuNgay.setColumns(10);

		Component horizontalStrut_7 = Box.createHorizontalStrut(24);
		b1.add(horizontalStrut_7);

		JLabel lblDenNgay = new JLabel("Đến ngày: ");
		lblDenNgay.setFont(new Font("Dialog", Font.BOLD, 20));
		b1.add(lblDenNgay);

		txtDenNgay = new JTextField();
		txtDenNgay.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtDenNgay.setColumns(10);
		b1.add(txtDenNgay);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		b1.add(horizontalStrut_4);

		Component verticalStrut_1_1 = Box.createVerticalStrut(20);
		b.add(verticalStrut_1_1);

		Box b2 = Box.createHorizontalBox();
		b.add(b2);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		b2.add(horizontalStrut_3);

		JLabel lblPhong = new JLabel("Phòng:");
		lblPhong.setFont(new Font("Dialog", Font.BOLD, 20));
		b2.add(lblPhong);

		Component horizontalStrut_1 = Box.createHorizontalStrut(36);
		b2.add(horizontalStrut_1);

		txtPhong = new JTextField();
		txtPhong.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtPhong.setColumns(10);
		b2.add(txtPhong);

		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		b2.add(horizontalStrut_6);

		JLabel lblNhanVien = new JLabel("Nhân viên: ");
		lblNhanVien.setFont(new Font("Dialog", Font.BOLD, 20));
		b2.add(lblNhanVien);

		txtNhanVien = new JTextField();
		txtNhanVien.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtNhanVien.setColumns(10);
		b2.add(txtNhanVien);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		b2.add(horizontalStrut_5);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		b.add(verticalStrut_1);

		Box boxBtn = Box.createHorizontalBox();
		b.add(boxBtn);

		btnXoaBoLoc.setFont(new Font("Dialog", Font.BOLD, 15));
		boxBtn.add(btnXoaBoLoc);

		Component verticalStrut_1_2 = Box.createVerticalStrut(20);
		b.add(verticalStrut_1_2);

		Box b3 = Box.createHorizontalBox();
		b.add(b3);

		lblSoLuongHoaDon.setFont(new Font("Dialog", Font.BOLD, 20));
		b3.add(lblSoLuongHoaDon);

		Component horizontalStrut_8 = Box.createHorizontalStrut(50);
		b3.add(horizontalStrut_8);

		lblTongTien.setFont(new Font("Dialog", Font.BOLD, 20));
		b3.add(lblTongTien);

		Component verticalStrut_1_1_1 = Box.createVerticalStrut(20);
		b.add(verticalStrut_1_1_1);

		// Button Listener
		btnXoaBoLoc.addActionListener(this);

		// Textfield Listener
		txtTuNgay.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						String fromDateString = txtTuNgay.getText();
						String toDateString = txtDenNgay.getText();
						if (fromDateString.matches("\\d{2}/\\d{2}/\\d{4}")) {
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							Date fromDate = sdf.parse(fromDateString);
							Date toDate = toDateString.matches("\\d{2}/\\d{2}/\\d{4}") ? sdf.parse(toDateString) : null;

							dateFilter = new RowFilter<DefaultTableModel, Integer>() {
								public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
									try {
										String dateString = (String) entry
												.getValue(entry.getModel().getColumnCount() - 1);

										Date date = sdf.parse(dateString);

										if (toDate == null) {
											return date.compareTo(fromDate) >= 0;
										} else {
											return date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0;
										}
									} catch (Exception e3) {
										return false;
									}
								}
							};

							rowSorter.setRowFilter(dateFilter);
						} else if (!fromDateString.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Sai định dạng ngày, vui lòng nhập lại");
						} else {
							dateFilter = null;

							if (roomFilter != null) {
								rowSorter.setRowFilter(roomFilter);
							} else if (staffFilter != null) {
								rowSorter.setRowFilter(staffFilter);
							} else {
								rowSorter.setRowFilter(null);
							}
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});

		txtDenNgay.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						String fromDateString = txtTuNgay.getText();
						String toDateString = txtDenNgay.getText();
						if (toDateString.matches("\\d{2}/\\d{2}/\\d{4}")) {
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							Date fromDate = fromDateString.matches("\\d{2}/\\d{2}/\\d{4}") ? sdf.parse(fromDateString)
									: null;
							Date toDate = sdf.parse(toDateString);

							dateFilter = new RowFilter<DefaultTableModel, Integer>() {
								public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
									try {
										String dateString = (String) entry
												.getValue(entry.getModel().getColumnCount() - 1);

										Date date = sdf.parse(dateString);

										if (fromDate == null) {
											return date.compareTo(toDate) <= 0;
										} else {
											return date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0;
										}
									} catch (Exception e3) {
										return false;
									}
								}
							};

							rowSorter.setRowFilter(dateFilter);
						} else if (!toDateString.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Sai định dạng ngày, vui lòng nhập lại");
						} else {
							dateFilter = null;

							if (roomFilter != null) {
								if (staffFilter != null) {
									rowSorter.setRowFilter(RowFilter.andFilter(Arrays.asList(roomFilter, staffFilter)));
								} else {
									rowSorter.setRowFilter(roomFilter);
								}
							} else {
								rowSorter.setRowFilter(null);
							}
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});

		txtPhong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String phong = txtPhong.getText();
					if (!phong.isEmpty()) {
						roomFilter = new RowFilter<DefaultTableModel, Integer>() {
							public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
								try {
									String phongString = (String) entry.getValue(1);
									return phongString.contains(phong);
								} catch (Exception e3) {
									return false;
								}
							}
						};

						if (dateFilter != null) {
							rowSorter.setRowFilter(RowFilter.andFilter(Arrays.asList(dateFilter, roomFilter)));
						} else {
							rowSorter.setRowFilter(roomFilter);
						}
					} else {
						roomFilter = null;

						if (dateFilter != null) {
							if (staffFilter != null) {
								rowSorter.setRowFilter(RowFilter.andFilter(Arrays.asList(dateFilter, staffFilter)));
							} else {
								rowSorter.setRowFilter(dateFilter);
							}
						} else {
							rowSorter.setRowFilter(null);
						}
					}
				}
			}
		});

		txtNhanVien.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String nhanVien = txtNhanVien.getText();
					if (!nhanVien.isEmpty()) {
						staffFilter = new RowFilter<DefaultTableModel, Integer>() {
							public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
								try {
									String nhanVienString = (String) entry.getValue(2);
									return nhanVienString.contains(nhanVien);
								} catch (Exception e3) {
									return false;
								}
							}
						};

						if (dateFilter != null) {
							if (roomFilter != null) {
								rowSorter.setRowFilter(
										RowFilter.andFilter(Arrays.asList(dateFilter, roomFilter, staffFilter)));
							} else {
								rowSorter.setRowFilter(RowFilter.andFilter(Arrays.asList(dateFilter, staffFilter)));
							}
						} else {
							rowSorter.setRowFilter(staffFilter);
						}
					} else {
						staffFilter = null;
						if (dateFilter != null) {
							if (roomFilter != null) {
								rowSorter.setRowFilter(RowFilter.andFilter(Arrays.asList(dateFilter, roomFilter)));
							} else {
								rowSorter.setRowFilter(dateFilter);
							}
						} else {
							rowSorter.setRowFilter(null);
						}
					}
				}
			}
		});

		// Load data to table
		loadDataToTable();

		// Table listener
		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && tbl.getSelectedRow() != -1) {
					HoaDon_DAO hdDAO = new HoaDon_DAO();
					int row = tbl.getSelectedRow();

					HoaDon hd = hdDAO.getHoaDonByID((String) tbl.getValueAt(row, 0));
					new HoaDonDialog(hd).setVisible(true);
				}
			}
		});
	}

	private void createTable() {
		String[] tblCols = { "Mã hóa đơn", "Mã phòng", "Mã nhân viên", "Mã khách hàng", "Tổng tiền", "Ngày tạo" };

		model = new DefaultTableModel(tblCols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tbl = new JTable(model);

		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tbl.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 20));
		tbl.setFont(new Font("Dialog", Font.PLAIN, 20));

		rowSorter = new TableRowSorter<>(model);
		tbl.setRowSorter(rowSorter);

		tbl.getColumnModel().getColumn(0).setPreferredWidth(70);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(90);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(110);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbl.getColumnModel().getColumn(4).setPreferredWidth(80);
		tbl.getColumnModel().getColumn(5).setPreferredWidth(80);

		Box boxTop = Box.createVerticalBox();
		add(boxTop);

		Box hBox = Box.createHorizontalBox();
		boxTop.add(hBox);

		JLabel lblTitle = new JLabel("Thống kê doanh thu");
		lblTitle.setForeground(Color.RED);
		hBox.add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 25));

		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut);
		tbl.setRowHeight(30);

		JScrollPane scrollPane = new JScrollPane(tbl);

		add(scrollPane);

		Object[] testRow = { "HD001", "P001", "NV001", "K001", 200000, "14/07/2022" };

		model.addRow(testRow);

	}

	private void loadDataToTable() {
		try {
			model.setRowCount(0);
			HoaDon_DAO hdDAO = new HoaDon_DAO();
			List<HoaDon> list = hdDAO.getAllHoaDon();

			lblSoLuongHoaDon.setText("Số lượng hóa đơn: " + list.size());
			for (HoaDon h : list) {
				tongTien += h.getTongTien();
			}

			Locale lc = new Locale("vi", "VN");
			NumberFormat nf = NumberFormat.getCurrencyInstance(lc);

			lblTongTien.setText("Tổng tiền: " + nf.format(tongTien));

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			for (HoaDon h : list) {
				Object[] data = { h.getMaHD(), h.getMaPhong(), h.getMaNV(), h.getMaKH(), nf.format(h.getTongTien()),
						dtf.format(h.getNgayTaoHD().plusDays(2)) };

				model.addRow(data);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnXoaBoLoc)) {
			txtTuNgay.setText("");
			txtDenNgay.setText("");
			txtPhong.setText("");
			txtNhanVien.setText("");
			rowSorter.setRowFilter(null);
		}
	}
}
