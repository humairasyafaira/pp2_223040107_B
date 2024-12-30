/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author HUMAIRA
 */
import model.*;
import view.UserPdf;
import view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserController {
    private UserView view;
    private UserMapper mapper;
    private UserPdf pdf;

    public UserController(UserView view, UserMapper mapper, UserPdf pdf) {
        this.view = view;
        this.mapper = mapper;
        this.pdf = pdf;
        
        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addExportListener(new ExportListener());
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String email = view.getEmailInput();

            if (!name.isEmpty() && !email.isEmpty()) {
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                mapper.insertUser(user);
                JOptionPane.showMessageDialog(view, "User added successfully!");
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            }
        }
    }

    class RefreshListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() {
                List<User> users = mapper.getAllUsers();
                view.setStatus("Refreshing data...");
                view.setProgress(0);
                int size = users.size();

                int progressIncrement = size > 0 ? 100 / size : 0;
                int progress = 0;

                for (int i = 0; i < size; i++) {
                    User user = users.get(i);
                    try {
                        Thread.sleep(10); // Simulasi waktu pemrosesan per data
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    progress += progressIncrement;

                    // Publish data ke GUI
                    publish(user.getName() + " (" + user.getEmail() + ")");

                    // Pastikan progres tidak lebih dari 100
                    setProgress(Math.min(progress, 100));
                }

                // Paksa progres mencapai 100% pada akhir
                setProgress(100);
                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                for (String user : chunks) {
                    view.addToUserList(user); // Tambahkan data ke daftar tampilan
                }
                view.setProgress(getProgress());
            }

            @Override
            protected void done() {
                view.setStatus("Refresh complete!");
                view.setProgress(100); // Pastikan GUI menampilkan 100% di akhir
            }
        }.execute();
    }
}


    class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() {
                    view.setStatus("Exporting to PDF...");
                    List<User> users = mapper.getAllUsers();
                    pdf.exportPdf(users);
                    return null;
                }

                @Override
                protected void done() {
                    view.setStatus("Export complete!");
                }
            }.execute();
        }
    }
}

