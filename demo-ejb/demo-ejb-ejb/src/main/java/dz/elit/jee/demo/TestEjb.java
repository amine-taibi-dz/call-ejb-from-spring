/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dz.elit.jee.demo;

import javax.ejb.Stateless;

/**
 *
 * @author martin
 */
@Stateless
public class TestEjb implements CalcLocal,CalcRemote{

    public int add(int op1, int op2) {
        return addCalc(op1, op2);
    }

    @Override
    public int addRemote(int op1, int op2) {
       return addCalc(op1, op2);
    }
    
    private int addCalc(int op1,int op2){
        return op1+op2;
    }

    

}
