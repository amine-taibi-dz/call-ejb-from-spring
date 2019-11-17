package dz.acs.rem.spring;

import javax.ejb.Remote;

@Remote
public interface CalcRemote {
	int addRemote(int op1,int op2);


}
