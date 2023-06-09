package ui.forms;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import dao.*;
import entity.HoaDon;
import entity.KhachHang;
import entity.Phong;
import entity.TinhTrang;

public class FormKhachHang extends JDialog implements ActionListener {
    private String idPhong;

    private String currentMaKH;
    private String maNV;
    private JTextField txtMaKH;
    private JTextField txtHoTenKH;
    private JTextField txtNgaySinh;
    private JTextField txtSDT;
    private JTextField txtCCCD;
    private JButton btnInHoaDon = new JButton("Check-in");
    private JButton btnKiemTraKhachHang = new JButton("Kiểm tra");
    private JButton btnInHoaDon_2 = new JButton("Check-in");
    private JTextField txtCCCD_1;
    private JTextField txtMaKH_2;
    private JTextField txtHoTen_2;
    private JTextField txtSDT_2;

    public FormKhachHang(String idPhong, String maNV) {
        this.setTitle("Checkin cho phòng " + idPhong);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 360);
        this.setLocationRelativeTo(null);

        this.idPhong = idPhong;
        this.maNV = maNV;
        getContentPane().setLayout(null);

        JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane_1.setBounds(0, 0, 596, 327);
        getContentPane().add(tabbedPane_1);

        JPanel mainPanel = new JPanel();
        JPanel mainPanel2 = new JPanel();

        tabbedPane_1.addTab("Khách hàng mới", mainPanel);
        tabbedPane_1.addTab("Khách hàng cũ", mainPanel2);
        mainPanel2.setLayout(null);

        Box b_1 = Box.createVerticalBox();
        b_1.setBounds(0, 0, 591, 298);
        mainPanel2.add(b_1);

        Component verticalStrut = Box.createVerticalStrut(10);
        b_1.add(verticalStrut);

        Box b1_1 = Box.createHorizontalBox();
        b_1.add(b1_1);

        JLabel lblCCCD_2 = new JLabel("Nhập vào mã số CCCD của khách hàng cũ:");
        lblCCCD_2.setFont(new Font("Dialog", Font.BOLD, 20));
        b1_1.add(lblCCCD_2);

        Component verticalStrut_1 = Box.createVerticalStrut(10);
        b_1.add(verticalStrut_1);

        Box b4_1 = Box.createHorizontalBox();
        b_1.add(b4_1);

        JLabel lblCCCD_1 = new JLabel("Mã CCCD:");
        lblCCCD_1.setFont(new Font("Dialog", Font.BOLD, 20));
        b4_1.add(lblCCCD_1);

        Component horizontalStrut_1_1_1 = Box.createHorizontalStrut(114);
        b4_1.add(horizontalStrut_1_1_1);

        txtCCCD_1 = new JTextField();
        txtCCCD_1.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtCCCD_1.setColumns(10);
        b4_1.add(txtCCCD_1);

        Component verticalStrut_4 = Box.createVerticalStrut(30);
        b_1.add(verticalStrut_4);

        Box b5_1 = Box.createHorizontalBox();
        b_1.add(b5_1);

        JLabel lblThongTinKH = new JLabel("Mã khách hàng:");
        lblThongTinKH.setFont(new Font("Dialog", Font.BOLD, 20));
        b5_1.add(lblThongTinKH);

        Component horizontalStrut_1_2_1 = Box.createHorizontalStrut(54);
        b5_1.add(horizontalStrut_1_2_1);

        txtMaKH_2 = new JTextField();
        txtMaKH_2.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtMaKH_2.setEditable(false);
        b5_1.add(txtMaKH_2);
        txtMaKH_2.setColumns(10);

        Component verticalStrut_4_1 = Box.createVerticalStrut(10);
        b_1.add(verticalStrut_4_1);

        Box b5_1_1 = Box.createHorizontalBox();
        b_1.add(b5_1_1);

        JLabel lblHTnKhch = new JLabel("Họ tên khách hàng:");
        lblHTnKhch.setFont(new Font("Dialog", Font.BOLD, 20));
        b5_1_1.add(lblHTnKhch);

        Component horizontalStrut_1_2_1_1 = Box.createHorizontalStrut(20);
        b5_1_1.add(horizontalStrut_1_2_1_1);

        txtHoTen_2 = new JTextField();
        txtHoTen_2.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtHoTen_2.setEditable(false);
        txtHoTen_2.setColumns(10);
        b5_1_1.add(txtHoTen_2);

        Component verticalStrut_4_1_1 = Box.createVerticalStrut(10);
        b_1.add(verticalStrut_4_1_1);

        Box b5_1_2 = Box.createHorizontalBox();
        b_1.add(b5_1_2);

        JLabel lblStKhchHng = new JLabel("SĐT khách hàng:");
        lblStKhchHng.setFont(new Font("Dialog", Font.BOLD, 20));
        b5_1_2.add(lblStKhchHng);

        Component horizontalStrut_1_2_1_2 = Box.createHorizontalStrut(50);
        b5_1_2.add(horizontalStrut_1_2_1_2);

        txtSDT_2 = new JTextField();
        txtSDT_2.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtSDT_2.setEditable(false);
        txtSDT_2.setColumns(10);
        b5_1_2.add(txtSDT_2);

        Component verticalStrut_4_1_1_1 = Box.createVerticalStrut(10);
        b_1.add(verticalStrut_4_1_1_1);

        Box b6_1 = Box.createHorizontalBox();
        b_1.add(b6_1);

        btnKiemTraKhachHang.setFont(new Font("Dialog", Font.BOLD, 20));
        b6_1.add(btnKiemTraKhachHang);

        Component horizontalStrut_1_2_1_3 = Box.createHorizontalStrut(20);
        b6_1.add(horizontalStrut_1_2_1_3);

        btnInHoaDon_2.setFont(new Font("Dialog", Font.BOLD, 20));
        b6_1.add(btnInHoaDon_2);

        Component verticalStrut_6 = Box.createVerticalStrut(10);
        b_1.add(verticalStrut_6);
        mainPanel.setBounds(0, 0, 596, 330);
        getContentPane().add(tabbedPane_1);

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
        btnKiemTraKhachHang.addActionListener(this);
        btnInHoaDon_2.addActionListener(this);
    }

    @SuppressWarnings("resource")
    public void handleCheckin(KhachHang k, String maPhong, Boolean isNewKhachHang) {
        try {
            int days = Integer.parseInt(JOptionPane.showInputDialog(null, "Nhập số ngày thuê: "));


            if (Boolean.TRUE.equals(isNewKhachHang)) {
                KhachHang_DAO khDAO = new KhachHang_DAO();
                khDAO.addKhachHang(k);
            }

            TinhTrang_DAO ttDAO = new TinhTrang_DAO();
            ttDAO.editTinhTrang(maPhong, new TinhTrang(maPhong, k.getMaKH(), LocalDate.now(), LocalDate.now().plusDays(days)));
            JOptionPane.showMessageDialog(null, "Checkin thành công!");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
        }
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        Object o = e.getSource();
        if (o == btnInHoaDon) {
            Phong_DAO phongDAO = new Phong_DAO();
            KhachHang_DAO khDAO = new KhachHang_DAO();

            String maKH = txtMaKH.getText();
            String hoTenKH = txtHoTenKH.getText();
            String ngaySinh = txtNgaySinh.getText();
            String sdt = txtSDT.getText();
            String cccd = txtCCCD.getText();


            try {
                for (KhachHang kh : khDAO.getAllKhachHang()) {
                    if (kh.getCCCD().equals(cccd)) {
                        JOptionPane.showMessageDialog(null, "Trùng mã CCCD với một khách hàng khác!");
                        return;
                    }
                }

                handleCheckin(
                        new KhachHang(maKH, hoTenKH,
                                LocalDate.parse(ngaySinh, DateTimeFormatter.ofPattern("dd/MM/yyyy")), sdt, cccd),
                        idPhong, true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else if (o == btnKiemTraKhachHang) {
            KhachHang_DAO khachHangDAO = new KhachHang_DAO();
            String CCCD = txtCCCD_1.getText();
            try {
                KhachHang k = khachHangDAO.getKhachHangByCCCD(CCCD);

                if (k != null) {
                    txtMaKH_2.setText(k.getMaKH());
                    txtHoTen_2.setText(k.getTenKH());
                    txtSDT_2.setText(k.getSDT());

                    currentMaKH = k.getMaKH();
                } else {
                    txtMaKH_2.setText("");
                    txtHoTen_2.setText("");
                    txtSDT_2.setText("");
                    JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng");
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
        } else if (o == btnInHoaDon_2) {
            try {
                if (currentMaKH == null) {
                    throw new Exception("Chưa kiểm tra khách hàng");
                } else {
                    handleCheckin(new KhachHang_DAO().getKhachHangByID(currentMaKH),
                            idPhong, false);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
}