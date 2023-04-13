package ui;

import connectDB.ConnectDB;

import javax.swing.JOptionPane;

public class Application {
	public static String currentLoggedInUser = "";

	public static void main(String[] args) {
		try {
			ConnectDB.getInstance().connect();

			System.out.println("Connect success");
		} catch (Exception e) {
			e.printStackTrace();
		}

		FormDangNhap f = new FormDangNhap();
		GUI_NhanVien g = new GUI_NhanVien();
		GUI_QuanLy h = new GUI_QuanLy();
		f.setVisible(true);

		currentLoggedInUser = f.getMaNVDangNhap();

		System.out.println(currentLoggedInUser);

		if (currentLoggedInUser.equals("admin")) {
			h.setVisible(true);

			System.out.println("admin");

		} else if (!currentLoggedInUser.equals("")) {
			g.setVisible(true);
			System.out.println("user");

		} else {
			JOptionPane.showMessageDialog(null, "Chưa đăng nhập");
		}
	}
}
