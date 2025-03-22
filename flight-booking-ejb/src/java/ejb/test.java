/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejb;

import javax.ejb.Stateless;

/**
 *
 * @author vuquo
 */
@Stateless
public class test implements testinterface{
    @Override
    public double cToF(double c) {
        return c*9/5 + 32;
    };
    
    @Override
    public double fToC(double f) {
        return (f-32) * 5/9;
    };
}
