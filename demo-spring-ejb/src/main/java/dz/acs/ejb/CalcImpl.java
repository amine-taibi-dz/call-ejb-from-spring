package dz.acs.ejb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.acs.rem.spring.CalcRemote;

@Service("calcTest")
public class CalcImpl implements CalcRemote {

	@Autowired
	@Qualifier("calcRemoteJboss")
	public CalcRemote calcRemote;
	
	@Override
	public int addRemote(int op1, int op2) {
		return calcRemote.addRemote(op1, op2);
	}

}
