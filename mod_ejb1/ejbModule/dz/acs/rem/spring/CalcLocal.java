package dz.acs.rem.spring;

import javax.ejb.Local;

@Local
public interface CalcLocal {
	int add(int op1,int op2);

}
