package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FormDangNhap extends JDialog implements ActionListener {
	private JTextField txtMaNV;
	private JTextField txtMatKhau;
	private JButton btnDangNhap;
	public String maNVDangNhap;

	public String getMaNVDangNhap() {
		return maNVDangNhap;
	}

	public FormDangNhap() {
		setTitle("Đăng nhập");
		setSize(600, 550);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);

		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(0, 0, 596, 99);
		mainPanel.add(panel);
		panel.setLayout(null);

		JLabel logoLabel = new JLabel("Quản lý khách sạn");
		logoLabel.setBounds(0, 0, 596, 99);
		panel.add(logoLabel);
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setFont(new Font("Dialog", Font.BOLD, 28));
		logoLabel.setIcon(new ImageIcon(GUI_NhanVien.class.getResource("/images/hotel-icon.png")));

		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtMaNV.setBounds(40, 166, 520, 50);
		mainPanel.add(txtMaNV);
		txtMaNV.setColumns(10);

		txtMatKhau = new JTextField();
		txtMatKhau.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(40, 278, 520, 50);
		mainPanel.add(txtMatKhau);

		JLabel lblMaNV = new JLabel("Mã đăng nhập:");
		lblMaNV.setForeground(Color.BLACK);
		lblMaNV.setFont(new Font("Dialog", Font.BOLD, 20));
		lblMaNV.setBounds(40, 137, 160, 25);
		mainPanel.add(lblMaNV);

		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setForeground(Color.BLACK);
		lblMatKhau.setFont(new Font("Dialog", Font.BOLD, 20));
		lblMatKhau.setBounds(40, 248, 160, 25);
		mainPanel.add(lblMatKhau);

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setFont(new Font("Dialog", Font.BOLD, 20));
		btnDangNhap.setBounds(40, 364, 520, 87);
		mainPanel.add(btnDangNhap);

		btnDangNhap.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnDangNhap)) {
			String maNV = txtMaNV.getText().trim();
			String password = txtMatKhau.getText().trim();

			if (maNV.equals("admin") && password.equals("admin")) {
				maNVDangNhap = "admin";

				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Loi");
				maNVDangNhap = "";
				dispose();
			}
		}
	}

}
