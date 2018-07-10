
package br.ufsm.lpbd.banking.aspect;

import br.ufsm.lpbd.banking.core.Account;
import br.ufsm.lpbd.banking.core.OverdraftAccount;
import br.ufsm.lpbd.banking.core.OverdraftTransference;

public aspect Ex5 {

	pointcut chamadasOverdraftTransference(OverdraftAccount src, Account dest, float amount): execution (* OverdraftTransference.transfer(OverdraftAccount, Account, float) throws *) && args(src, dest, amount) ;
	before(OverdraftAccount src, Account dest, float amount) : chamadasOverdraftTransference(src, dest, amount){
		float tax = amount * 0.01f;
		dest.registerTax(tax);
	}
	
	
	
}
