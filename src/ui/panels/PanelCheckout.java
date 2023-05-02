package ui.panels;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import dao.Phong_DAO;
import entity.Phong;
import ui.forms.FormInHoaDon;

public class PanelCheckout extends JPanel {
	private JTable tbl;
	private DefaultTableModel model;
	private JButton btnCheckout = new JButton("Checkout");
	private String currentMaNV = "";

	public PanelCheckout(String currentMaNV) {
		this.currentMaNV = currentMaNV;
		setBounds(0, 0, 725, 569);
		setVisible(false);
		setLayout(null);

		JLabel lblCheckout_1 = new JLabel("Check-out");
		lblCheckout_1.setBounds(305, 5, 100, 28);
		lblCheckout_1.setFont(new Font("Dialog", Font.BOLD, 20));
		add(lblCheckout_1);

		btnCheckout.setFont(new Font("DIalog", Font.BOLD, 20));

		createTable();

		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl.getSelectedRow();

				if (row != -1) {
					Phong_DAO hotels = new Phong_DAO();

					if (row != -1) {
						FormInHoaDon form = new FormInHoaDon(model.getValueAt(row, 0).toString(), currentMaNV);
						form.setVisible(true);

						if (!form.isActive()) {
							// Set lại trạng thái phòng
							try {
								hotels.editPhongByID(model.getValueAt(row, 0).toString(),
										new Phong(model.getValueAt(row, 0).toString(),
												model.getValueAt(row, 1).toString(), false,
												Double.parseDouble(model.getValueAt(row, 2).toString()),
												model.getValueAt(row, 4).toString()));

								refreshTable();
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Chọn một phòng để in hóa đơn");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Phải chọn một dòng");
				}
			}
		});

	}

	private void createTable() {
		String[] tblCols = { "Mã phòng", "Mã loại phòng", "Giá phòng", "Tình trạng", "Ghi chú" };
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
		tblPane_1.setBounds(0, 36, 725, 533);
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

	private void refreshTable() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
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
