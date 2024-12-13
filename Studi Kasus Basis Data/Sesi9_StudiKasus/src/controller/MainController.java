/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author HUMAIRA
 */


import model.Record;
import model.RecordDAO;
import view.MainView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MainController {
    private MainView view;
    private RecordDAO dao;

    public MainController(MainView view, RecordDAO dao) {
        this.view = view;
        this.dao = dao;

        loadTableData();

        view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Name:");
                String email = JOptionPane.showInputDialog("Enter Email:");
                String address = JOptionPane.showInputDialog("Enter Address:");
                String phone = JOptionPane.showInputDialog("Enter Phone:");

                try {
                    dao.insertRecord(new Record(0, name, email, address, phone));
                    loadTableData();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        view.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getTable().getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(view, "Please select a record to update.");
                    return;
                }

                int id = (int) view.getTable().getValueAt(selectedRow, 0);
                String name = JOptionPane.showInputDialog("Enter Name:");
                String email = JOptionPane.showInputDialog("Enter Email:");
                String address = JOptionPane.showInputDialog("Enter Address:");
                String phone = JOptionPane.showInputDialog("Enter Phone:");

                try {
                    dao.updateRecord(new Record(id, name, email, address, phone));
                    loadTableData();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        view.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getTable().getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(view, "Please select a record to delete.");
                    return;
                }

                int id = (int) view.getTable().getValueAt(selectedRow, 0);
                try {
                    dao.deleteRecord(id);
                    loadTableData();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void loadTableData() {
        try {
            List<Record> records = dao.getAllRecords();
            DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Email", "Address", "Phone"}, 0);
            for (Record record : records) {
                model.addRow(new Object[]{record.getId(), record.getName(), record.getEmail(), record.getAddress(), record.getPhone()});
            }
            view.getTable().setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

