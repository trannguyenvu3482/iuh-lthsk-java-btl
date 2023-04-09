package ui;

import javax.swing.JOptionPane;

public class Application {
	public static void main(String[] args) {
		FormDangNhap f = new FormDangNhap();
		GUI_NhanVien g = new GUI_NhanVien();
		f.setVisible(true);

		String loggedInUser = f.getMaNVDangNhap();

		System.out.println(loggedInUser);

		if (loggedInUser.equals("admin")) {
			g.setVisible(true);

		} else if (!loggedInUser.equals("")) {
			g.setVisible(true);

		} else {
			JOptionPane.showMessageDialog(null, "Chưa đăng nhập");
		}
	}
}
