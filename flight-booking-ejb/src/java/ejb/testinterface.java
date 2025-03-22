/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ejb;

import javax.ejb.Remote;

/**
 *
 * @author vuquo
 */
@Remote
public interface testinterface {
    public double cToF(double c);
    public double fToC(double f);
}
