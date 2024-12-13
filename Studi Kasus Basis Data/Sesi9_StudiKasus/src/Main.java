/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HUMAIRA
 */
import model.RecordDAO;
import view.MainView;
import controller.MainController;

public class Main {
    public static void main(String[] args) {
        MainView view = new MainView();
        RecordDAO dao = new RecordDAO();
        new MainController(view, dao);
        view.setVisible(true);
    }
}
