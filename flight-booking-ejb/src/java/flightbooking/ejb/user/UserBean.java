/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightbooking.ejb.user;

import flightbooking.entity.Users;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author vuquo
 */
public class UserBean implements User{
    
    @PersistenceContext(unitName = "flightBookingPU")
    private EntityManager em;
    
    @Override
    public List<Users> getAllUser() {
        TypedQuery<Users> query = em.createNamedQuery("Users.findAll", Users.class);
        return query.getResultList();
    }

    @Override
    public Users getUserById(int userId) {
        return em.find(Users.class, userId);
    }

    @Override
    public void addUser(Users user) {
        em.persist(user);
    }

    @Override
    public void updateUser(Users user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(int userId) {
        Users user = em.find(Users.class, userId);
        if(user != null){
            em.remove(user);
        }
    }
    
}
