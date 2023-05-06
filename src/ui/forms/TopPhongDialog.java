package ui.forms;

import dao.Phong_DAO;
import entity.Phong;

import javax.swing.*;
import java.util.List;

public class TopPhongDialog extends JDialog {
    public TopPhongDialog(JFrame parent) {
        super(parent, "Top phòng được đặt nhiều nhất", true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Top phòng được đặt nhiều nhất");
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label);

        Phong_DAO phong_dao = new Phong_DAO();
        List<Phong> ls = phong_dao.getTopPhong();

        // TODO: Hiển thị thông tin phòng
        for (Phong phong : ls) {
            // Add phòng vào panel
            JPanel panel = new JPanel();
//            panel.add(new JLabel("Mã phòng " + phong.getMaPhong() + ", số lần đặt " + phong.getSoLanDat()));
            add(panel);
        }
    }
}
