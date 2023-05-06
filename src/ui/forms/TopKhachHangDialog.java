package ui.forms;

import javax.swing.*;

public class TopKhachHangDialog extends JDialog {
    public TopKhachHangDialog(JFrame parent) {
        super(parent, "Top khách hàng", true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        add(new JLabel("Top khách hàng"));

    }
}
