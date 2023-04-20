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
        f.setVisible(true);

        currentLoggedInUser = f.getMaNVDangNhap();

        if (currentLoggedInUser.equals("admin")) {
            GUI_QuanLy h = new GUI_QuanLy();
            h.setVisible(true);


        } else if (currentLoggedInUser.matches("NV\\d{3}")) {
            GUI_NhanVien g = new GUI_NhanVien(currentLoggedInUser);
            g.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Chưa đăng nhập");
        }
    }
}
