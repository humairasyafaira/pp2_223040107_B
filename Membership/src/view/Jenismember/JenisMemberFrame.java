package view.jenismember;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.JenisMemberDao;
import model.JenisMember;

public class JenisMemberFrame extends JFrame {

    private final List<JenisMember> jenisMemberList;
    private final JTextField textFieldNama;
    private final JenisMemberTableModel tableModel;

    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {
        this.jenisMemberList = jenisMemberDao.findAll();

        // Set frame properties
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setSize(400, 500);
        this.setTitle("Jenis Member");

        // Initialize components
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 100, 100, 40);

        // Table setup
        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 150, 358, 200);

        tableModel = new JenisMemberTableModel(jenisMemberList);
        table.setModel(tableModel);

        // Add action listener
        JenisMemberButtonSimpanActionListener actionListener
                = new JenisMemberButtonSimpanActionListener(this, jenisMemberDao);
        button.addActionListener(actionListener);

        // Add components to frame
        this.add(labelInput);
        this.add(textFieldNama);
        this.add(button);
        this.add(scrollableTable);
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public void addJenisMember(JenisMember jenisMember) {
        tableModel.add(jenisMember);
        textFieldNama.setText("");
    }
}
