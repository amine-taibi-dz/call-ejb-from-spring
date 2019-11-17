/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dz.elit.jee.demo;

import javax.ejb.Remote;

/**
 *
 * @author ataibi
 */
@Remote
public interface CalcRemote {
    	int addRemote(int op1,int op2);

    
}
