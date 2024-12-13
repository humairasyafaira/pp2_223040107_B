/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MembershipORM.src.main;

import MembershipORM.src.dao.JenisMemberDao;
import MembershipORM.src.dao.MemberDao;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import MembershipORM.src.view.main.MainFrame;
import java.io.File;

public class Main {
    
    public static void main(String[] args) throws IOException {
          
    // Debug untuk memastikan file ditemukan
    File file = new File("src/main/resources/mybatis-config.xml");
    System.out.println("File exists: " + file.exists());
    System.out.println("Absolute path: " + file.getAbsolutePath());

    // Proses MyBatis
    String resource = "mybatis-config.xml"; 
    InputStream inputStream = Resources.getResourceAsStream(resource); // <-- Periksa apakah tetap error
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    // Inisialisasi DAO
    JenisMemberDao jenisMemberDao = new JenisMemberDao(sqlSessionFactory);
    MemberDao memberDao = new MemberDao(sqlSessionFactory);

    // Tampilan GUI
    MainFrame f = new MainFrame(jenisMemberDao, memberDao);
    javax.swing.SwingUtilities.invokeLater(() -> {
        f.setVisible(true);
    });
  }
}

