import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableEditableExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTable Editable Example");
        
        // Header kolom
        String[] columnNames = { "ID", "Name", "Age" };
        
        // Data awal untuk tabel
        Object[][] data = {
            { 1, "John", 25 },
            { 2, "Anna", 30 },
            { 3, "Mike", 35 }
        };
        
        // Membuat DefaultTableModel dengan data dan header
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Hanya kolom 'Name' yang dapat diedit
                return column == 1;
            }
        };
        
        // Membuat JTable dengan model
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Menambahkan JScrollPane ke frame
        frame.add(scrollPane);
        
        // Konfigurasi frame
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
