/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HUMAIRA
 */
import java.util.List;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

        void insertUser(User user);  // Tanpa anotasi seperti @Insert
    List<User> getAllUsers();    // Tanpa anotasi seperti @Select
}

