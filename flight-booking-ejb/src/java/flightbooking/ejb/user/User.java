/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package flightbooking.ejb.user;

import flightbooking.entity.Users;
import java.util.List;

/**
 *
 * @author vuquo
 */
public interface User {
    List<Users> getAllUser();
    
    Users getUserById(int userId);
    
    void addUser(Users user);
    
    void updateUser(Users user);
    
    void deleteUser(int userId);
}
