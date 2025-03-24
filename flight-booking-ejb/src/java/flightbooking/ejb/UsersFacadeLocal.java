/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package flightbooking.ejb;

import flightbooking.entity.Users;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vuquo
 */
@Local
public interface UsersFacadeLocal {

    void create(Users users);

    void edit(Users users);

    void remove(Users users);

    Users find(Object id);        

    List<Users> findAll();

    List<Users> findRange(int[] range);

    int count();
    
    Users findByUsername(String username);
}
