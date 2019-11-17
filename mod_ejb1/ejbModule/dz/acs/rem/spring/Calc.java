package dz.acs.rem.spring;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class Calc
 */
@Stateless(mappedName = "calc")
public class Calc implements CalcRemote, CalcLocal {

	/**
	 * Default constructor. 
	 */
	public Calc() {
	}

	@Override
	public int add(int op1, int op2) {
		return opAdd(op1,op2);
	}

	@Override
	public int addRemote(int op1, int op2) {
		return opAdd(op1,op2);	}

	/**
	 * opAdd 
	 * @param op1
	 * @param op2
	 * @return res
	 */
	private int opAdd(int op1, int op2) {
		return op1+op2;
	}

}
