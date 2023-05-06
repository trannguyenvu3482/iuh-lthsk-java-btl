package ui.panels;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.LoaiPhong_DAO;
import dao.Phong_DAO;
import dao.TinhTrang_DAO;
import entity.Phong;
import ui.forms.FormKhachHang;

public class PanelCheckin extends JPanel implements ActionListener {
	private JTable tbl;
	private DefaultTableModel model;

	private String currentMaNV;
	private JComboBox<String> cboFilterLoaiPhong = new JComboBox<String>();
	private JComboBox<String> cboFilterChatLuong = new JComboBox<String>();
	private JComboBox<String> cboFilterGia = new JComboBox<String>();
	private TableRowSorter<DefaultTableModel> rowSorter;

	public PanelCheckin(String currentMaNV) {
		this.currentMaNV = currentMaNV;
		setBounds(0, 0, 725, 669);
		setLayout(null);

		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 725, 90);
		add(titlePanel);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

		Box verticalBox = Box.createVerticalBox();
		titlePanel.add(verticalBox);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		JLabel mainTitle = new JLabel("Check in");
		horizontalBox.add(mainTitle);
		mainTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		mainTitle.setHorizontalAlignment(SwingConstants.TRAILING);

		Component verticalStrut_1 = Box.createVerticalStrut(10);
		verticalBox.add(verticalStrut_1);

		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setBorder(
				new TitledBorder(null, "Bộ lọc tìm kiếm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox.add(horizontalBox_1);

		JLabel lblNewLabel = new JLabel("Loại phòng: ");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		horizontalBox_1.add(lblNewLabel);

		cboFilterLoaiPhong.setFont(new Font("Dialog", Font.PLAIN, 15));

		cboFilterLoaiPhong.addItem("---");
		cboFilterLoaiPhong.addItem("Phòng đơn");
		cboFilterLoaiPhong.addItem("Phòng đôi");

		horizontalBox_1.add(cboFilterLoaiPhong);

		Component horizontalStrut = Box.createHorizontalStrut(10);
		horizontalBox_1.add(horizontalStrut);

		JLabel lblNewLabel_1 = new JLabel("Chất lượng: ");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		horizontalBox_1.add(lblNewLabel_1);

		cboFilterChatLuong.setFont(new Font("Dialog", Font.PLAIN, 15));
		horizontalBox_1.add(cboFilterChatLuong);

		cboFilterChatLuong.addItem("---");
		cboFilterChatLuong.addItem("Thường");
		cboFilterChatLuong.addItem("Cao cấp");

		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		horizontalBox_1.add(horizontalStrut_1);

		JLabel lblNewLabel_3 = new JLabel("Giá: ");
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 16));
		horizontalBox_1.add(lblNewLabel_3);

		cboFilterGia.setFont(new Font("Dialog", Font.PLAIN, 15));
		horizontalBox_1.add(cboFilterGia);

		cboFilterGia.addItem("---");
		cboFilterGia.addItem("Tăng");
		cboFilterGia.addItem("Giảm");

		createTable();

		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl.getSelectedRow();

				if (row != -1) {
					Phong_DAO hotels = new Phong_DAO();

					FormKhachHang form = new FormKhachHang(model.getValueAt(row, 0).toString(), currentMaNV);
					form.setVisible(true);

					if (!form.isActive()) {
						// Set lại trạng thái phòng
						try {
							refreshTable();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Phải chọn một dòng");
				}
			}
		});

		// Filter handler
		cboFilterLoaiPhong.addActionListener(this);
		cboFilterChatLuong.addActionListener(this);
		cboFilterGia.addActionListener(this);
	}

	public void refreshTable() {
		Phong_DAO hotels = new Phong_DAO();
		TinhTrang_DAO ttDao = new TinhTrang_DAO();
		List<Phong> listPhong = hotels.getAllPhong();

		// TODO: FIX THIS
		model.setRowCount(0);
		for (Phong p : listPhong) {
			if (ttDao.getTinhTrangByID(p.getMaPhong()).getMaKH().equals("KH000")) {
				model.addRow(new Object[] { p.getMaPhong(), p.getMaLoai(), p.getGiaPhong(), p.getGhiChu() });
			}
		}
	}

	private void createTable() {
		String[] tblCols = { "Mã phòng", "Mã loại phòng", "Giá phòng", "Ghi chú" };
		model = new DefaultTableModel(tblCols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tbl = new JTable(model);

		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		rowSorter = new TableRowSorter<>(model);
		tbl.setRowSorter(rowSorter);

		// Set size of columns and rows
		tbl.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(120);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(120);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(80);
		tbl.setRowHeight(30);

		// Set font size of table
		tbl.setFont(new Font("Arial", Font.PLAIN, 20));

		JScrollPane tblPane_1 = new JScrollPane(tbl);
		tblPane_1.setBounds(0, 94, 725, 575);
		tblPane_1.setBorder(BorderFactory.createTitledBorder("Thông tin các phòng"));
		add(tblPane_1);

		refreshTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(cboFilterLoaiPhong) || o.equals(cboFilterChatLuong)) {
			// If both are selected, filter by both, else filter by one, or none
			// The value of the other combobox will be "---"
			// Filter by add a RowFilter to rowSorter, using the value of the combobox
			// as the filter
			// If the value is "---", remove the filter
			// Else, add the filter
			// If both are "---", remove all filters
			LoaiPhong_DAO loaiPhongDAO = new LoaiPhong_DAO();

			if (cboFilterLoaiPhong.getSelectedIndex() == 0 && cboFilterChatLuong.getSelectedIndex() == 0) {
				rowSorter.setRowFilter(null);
			} else if (cboFilterLoaiPhong.getSelectedIndex() != 0 && cboFilterChatLuong.getSelectedIndex() == 0) {
				String maLoai1 = loaiPhongDAO.getMaFromLoai( cboFilterLoaiPhong.getSelectedItem().toString(), "Thường");
				String maLoai2 = loaiPhongDAO.getMaFromLoai( cboFilterLoaiPhong.getSelectedItem().toString(), "Cao cấp");

				rowSorter.setRowFilter(RowFilter.orFilter(Arrays.asList(
						RowFilter.regexFilter(maLoai1, 1),
						RowFilter.regexFilter(maLoai2, 1))));
			} else if (cboFilterLoaiPhong.getSelectedIndex() == 0 && cboFilterChatLuong.getSelectedIndex() != 0) {
				String maLoai1 = loaiPhongDAO.getMaFromLoai("Phòng đơn" , cboFilterChatLuong.getSelectedItem().toString());
				String maLoai2 = loaiPhongDAO.getMaFromLoai("Phòng đôi", cboFilterChatLuong.getSelectedItem().toString());
				rowSorter.setRowFilter(RowFilter.orFilter(Arrays.asList(
						RowFilter.regexFilter(maLoai1, 1),
						RowFilter.regexFilter(maLoai2, 1))));
			} else {
				String maLoai = loaiPhongDAO.getMaFromLoai(cboFilterLoaiPhong.getSelectedItem().toString(),
						cboFilterChatLuong.getSelectedItem().toString());

				rowSorter.setRowFilter(RowFilter.regexFilter(maLoai, 1));
			}
		} else if (o.equals(cboFilterGia)) {
			List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);

			if (cboFilterGia.getSelectedIndex() == 1) {
				sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
			} else if (cboFilterGia.getSelectedIndex() == 2) {
				sortKeys.add(new RowSorter.SortKey( 2, SortOrder.DESCENDING));
			} else {
				rowSorter.setSortKeys(null);
			}

			rowSorter.setSortKeys(sortKeys);
		}
	}
}
