package view.member;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Member;

public class MemberTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Nama", "Jenis Member"};
    private final List<Member> data;

    public MemberTableModel(List<Member> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Member member = data.get(row);
        return switch (col) {
            case 0 ->
                member.getNama(); // Pastikan metode getNama() ada di kelas Member
            case 1 ->
                member.getJenisMember().getNama(); // Pastikan metode getNama() ada di kelas JenisMember
            default ->
                null;
        };
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(Member member) {
        data.add(member);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}
