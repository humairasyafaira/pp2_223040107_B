package view.jenismember;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.JenisMember;

public class JenisMemberTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Nama"};
    private final List<JenisMember> data;

    public JenisMemberTableModel(List<JenisMember> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        JenisMember jenisMember = data.get(row);
        return switch (col) {
            case 0 ->
                jenisMember.getNama();
            default ->
                null;
        };
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(JenisMember value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}
