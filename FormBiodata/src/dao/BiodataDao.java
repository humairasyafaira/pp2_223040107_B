package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Biodata;

public class BiodataDao {

    private final Connection connection;

    public BiodataDao(Connection connection) {
        this.connection = connection;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void insert(Biodata biodata) {
        String sql = "INSERT INTO biodata (id, nama, telepon, gender, is_wna) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, biodata.getId());
            statement.setString(2, biodata.getNama());
            statement.setString(3, biodata.getTelepon());
            statement.setString(4, biodata.getGender());
            statement.setBoolean(5, biodata.isWna());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void update(Biodata biodata) {
        String sql = "UPDATE biodata SET nama = ?, telepon = ?, gender = ?, is_wna = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, biodata.getNama());
            statement.setString(2, biodata.getTelepon());
            statement.setString(3, biodata.getGender());
            statement.setBoolean(4, biodata.isWna());
            statement.setString(5, biodata.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void delete(String id) {
        String sql = "DELETE FROM biodata WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Biodata> getAll() {
        List<Biodata> biodataList = new ArrayList<>();
        String sql = "SELECT * FROM biodata";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Biodata biodata = new Biodata();
                biodata.setId(resultSet.getString("id"));
                biodata.setNama(resultSet.getString("nama"));
                biodata.setTelepon(resultSet.getString("telepon"));
                biodata.setGender(resultSet.getString("gender"));
                biodata.setWna(resultSet.getBoolean("is_wna"));
                biodataList.add(biodata);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return biodataList;
    }
}
