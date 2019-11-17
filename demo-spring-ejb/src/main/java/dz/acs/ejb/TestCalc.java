package dz.acs.ejb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.elit.jee.demo.CalcRemote;

/**
 * TestCalc
 * @author ataibi
 *
 */
@Service("test_calc")
public class TestCalc implements CalcRemote{
	@Autowired
	@Qualifier("calcRemoteGlassFish")
	public CalcRemote calcRemote;
	
	@Override
	public int addRemote(int op1, int op2) {
		return calcRemote.addRemote(op1, op2);
	}
}
