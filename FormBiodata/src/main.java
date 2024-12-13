
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

import dao.BiodataDao;
import view.BiodataFrame;

public class main {

    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nama_database", "user", "password");
            BiodataDao biodataDao = new BiodataDao(connection);

            SwingUtilities.invokeLater(() -> {
                BiodataFrame frame = new BiodataFrame(biodataDao);
                frame.setVisible(true);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
