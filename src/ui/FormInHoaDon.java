package ui;

import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.LoaiPhong_DAO;
import dao.Phong_DAO;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.Phong;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

public class FormInHoaDon extends JDialog implements ActionListener {
    private String idPhong;
    private String maNV;
    private JTextField txtMaKH;
    private JTextField txtHoTenKH;
    private JTextField txtNgaySinh;
    private JTextField txtSDT;
    private JTextField txtCCCD;
    private JButton btnInHoaDon = new JButton("In hóa đơn");

    public FormInHoaDon(String idPhong, String maNV) {
        this.setTitle("In hóa đơn cho phòng " + idPhong);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 360);
        this.setLocationRelativeTo(null);

        this.idPhong = idPhong;
        this.maNV = maNV;
        getContentPane().setLayout(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 596, 330);
        getContentPane().add(mainPanel);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        Box b = Box.createVerticalBox();
        Box b1 = Box.createHorizontalBox();
        Box b2 = Box.createHorizontalBox();
        Box b3 = Box.createHorizontalBox();
        Box b4 = Box.createHorizontalBox();
        Box b5 = Box.createHorizontalBox();
        Box b6 = Box.createHorizontalBox();

        JLabel lblMaKH = new JLabel("Mã khách hàng: ");
        lblMaKH.setFont(new Font("Dialog", Font.BOLD, 20));
        JLabel lblHoTenKH = new JLabel("Họ tên khách hàng: ");
        lblHoTenKH.setFont(new Font("Dialog", Font.BOLD, 20));
        JLabel lblNgaySinh = new JLabel("Ngày sinh: ");
        lblNgaySinh.setFont(new Font("Dialog", Font.BOLD, 20));
        JLabel lblSDT = new JLabel("Số điện thoại: ");
        lblSDT.setFont(new Font("Dialog", Font.BOLD, 20));
        JLabel lblCCCD = new JLabel("Số CCCD: ");
        lblCCCD.setFont(new Font("Dialog", Font.BOLD, 20));

        b1.add(lblMaKH);

        b2.add(lblHoTenKH);

        b3.add(lblNgaySinh);

        b4.add(lblSDT);

        b5.add(lblCCCD);
        btnInHoaDon.setFont(new Font("Dialog", Font.BOLD, 20));

        b6.add(btnInHoaDon);

        b.add(Box.createVerticalStrut(10));
        b.add(b1);
        Component horizontalStrut = Box.createHorizontalStrut(35);
        b1.add(horizontalStrut);

        txtMaKH = new JTextField();
        txtMaKH.setFont(new Font("Dialog", Font.PLAIN, 20));
        b1.add(txtMaKH);
        txtMaKH.setColumns(10);
        b.add(Box.createVerticalStrut(10));
        b.add(b2);

        txtHoTenKH = new JTextField();
        txtHoTenKH.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtHoTenKH.setColumns(10);
        b2.add(txtHoTenKH);
        b.add(Box.createVerticalStrut(10));
        b.add(b3);

        Component horizontalStrut_1 = Box.createHorizontalStrut(88);
        b3.add(horizontalStrut_1);

        txtNgaySinh = new JTextField();
        txtNgaySinh.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtNgaySinh.setColumns(10);
        b3.add(txtNgaySinh);
        b.add(Box.createVerticalStrut(10));
        b.add(b4);

        Component horizontalStrut_1_1 = Box.createHorizontalStrut(58);
        b4.add(horizontalStrut_1_1);

        txtSDT = new JTextField();
        txtSDT.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtSDT.setColumns(10);
        b4.add(txtSDT);
        b.add(Box.createVerticalStrut(10));
        b.add(b5);

        Component horizontalStrut_1_2 = Box.createHorizontalStrut(106);
        b5.add(horizontalStrut_1_2);

        txtCCCD = new JTextField();
        txtCCCD.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtCCCD.setColumns(10);
        b5.add(txtCCCD);
        b.add(Box.createVerticalStrut(10));

        b.add(b6);
        b.add(Box.createVerticalStrut(10));

        mainPanel.add(b);

        btnInHoaDon.addActionListener(this);
    }

    public void writeFileHoaDon() {
        try {
            HoaDon_DAO hoaDonDAO = new HoaDon_DAO();
            int countHoaDon = hoaDonDAO.countHoaDon();
            String maHD = "";

            if (countHoaDon == 0) {
                maHD = "HD001";
            } else {
                maHD = "HD" + String.format("%03d", countHoaDon + 1);
            }

            File file = new File("./src/txt/HoaDon" + maHD + ".txt");
            FileWriter fw = new FileWriter(file);

            if (file.exists()) {
                Phong_DAO phongDAO = new Phong_DAO();
                LoaiPhong_DAO loaiPhongDAO = new LoaiPhong_DAO();
                KhachHang_DAO khachHangDAO = new KhachHang_DAO();

                String maKH = txtMaKH.getText();
                String hoTenKH = txtHoTenKH.getText();
                String ngaySinh = txtNgaySinh.getText();
                String sdt = txtSDT.getText();
                String cccd = txtCCCD.getText();
                Phong p = phongDAO.getPhongByID(idPhong);
                String loaiPhong = loaiPhongDAO.getLoaiPhongByID(p.getMaLoai()).getTenLoai();
                String chatLuong = loaiPhongDAO.getLoaiPhongByID(p.getMaLoai()).getChatLuong();

                KhachHang k = new KhachHang(maKH, hoTenKH, LocalDate.parse(ngaySinh, DateTimeFormatter.ofPattern("dd/MM/yyyy")), sdt, cccd);

                HoaDon hd = new HoaDon(maHD, idPhong, maNV, maKH, p.getGiaPhong(), LocalDate.now());

                khachHangDAO.addKhachHang(k);
                hoaDonDAO.addNewHoaDon(hd);


                fw.write("Hóa đơn thuê phòng: ");
                fw.write("\n");

                fw.write("Mã hóa đơn: " + hd.getMaHD());
                fw.write("\n");

                fw.write("----------------------------------------");
                fw.write("\n");

                fw.write("Mã khách hàng: " + hd.getMaKH());
                fw.write("\n");

                fw.write("Họ tên khách hàng: " + k.getTenKH());
                fw.write("\n");

                fw.write("Ngày sinh: " + k.getNgaySinh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                fw.write("\n");

                fw.write("Số điện thoại: " + k.getSDT());
                fw.write("\n");

                fw.write("Số CCCD: " + k.getCCCD());
                fw.write("\n");

                fw.write("----------------------------------------");
                fw.write("\n");

                fw.write("Mã phòng: " + hd.getMaPhong());
                fw.write("\n");

                fw.write("Loại phòng: " + loaiPhong);
                fw.write("\n");

                fw.write("Chất lượng: " + chatLuong);
                fw.write("\n");

                fw.write("Giá phòng: " + hd.getTongTien());
                fw.write("\n");

                fw.write("----------------------------------------");
                fw.write("\n");

                fw.write("Ngày thuê: " + hd.getNgayTaoHD().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                JOptionPane.showMessageDialog(null, "In hóa đơn thành công");
                fw.close();
                dispose();
            } else {
                file.createNewFile();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == btnInHoaDon) {
            writeFileHoaDon();
        }
    }
}