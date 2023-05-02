package ui.panels;

import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.Phong_DAO;
import entity.Phong;

public class PanelCheckin extends JPanel {
	private JTable tbl;
	private DefaultTableModel model;

	public PanelCheckin() {
		setBounds(0, 0, 725, 669);
		setLayout(null);

		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 725, 50);
		add(titlePanel);
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel mainTitle = new JLabel("Check in");
		mainTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		titlePanel.add(mainTitle);
		mainTitle.setHorizontalAlignment(SwingConstants.TRAILING);

		createTable();
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

		JScrollPane tblPane_1 = new JScrollPane(tbl);
		tblPane_1.setBounds(0, 54, 725, 615);
		tblPane_1.setBorder(BorderFactory.createTitledBorder("Thông tin các phòng"));
		add(tblPane_1);

		Phong_DAO hotels = new Phong_DAO();
		List<Phong> list = hotels.getAllPhong();

		for (Phong p : list) {
			String tinhTrang = p.getTinhTrang() ? "Đã thuê" : "Còn trống";

			if (tinhTrang.equals("Đã thuê")) {
				model.addRow(new Object[] { p.getMaPhong(), p.getMaLoai(), p.getGiaPhong(), tinhTrang, p.getGhiChu() });
			}
		}

	}
}
