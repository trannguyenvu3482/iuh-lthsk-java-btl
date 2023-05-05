package ui.panels;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.Phong_DAO;
import dao.TinhTrang_DAO;
import entity.Phong;
import ui.forms.FormKhachHang;

public class PanelCheckin extends JPanel {
	private JTable tbl;
	private DefaultTableModel model;

	private String currentMaNV;

	public PanelCheckin(String currentMaNV) {
		this.currentMaNV = currentMaNV;
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

		// Set size of columns and rows
		tbl.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(120);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(120);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(80);
		tbl.setRowHeight(30);

		// Set font size of table
		tbl.setFont(new Font("Arial", Font.PLAIN, 20));

		JScrollPane tblPane_1 = new JScrollPane(tbl);
		tblPane_1.setBounds(0, 54, 725, 615);
		tblPane_1.setBorder(BorderFactory.createTitledBorder("Thông tin các phòng"));
		add(tblPane_1);

		refreshTable();
	}
}
