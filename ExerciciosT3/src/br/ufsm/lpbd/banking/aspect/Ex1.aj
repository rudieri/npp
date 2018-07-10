
package br.ufsm.lpbd.banking.aspect;

import java.text.DecimalFormat;

import br.ufsm.lpbd.banking.core.Account;

public aspect Ex1 {

	pointcut chamadasDebitEx1(float amount, Account a): call (public void Account+.debit(float) throws *) && args(amount) && target(a);
	before(float amount, Account a) : chamadasDebitEx1(amount, a){
		if(amount > 10000) {
			DecimalFormat dc = new DecimalFormat("#.00");
			String strAmount = dc.format(amount);
			System.out.print("Saque(Conta: " + a.getAccountNumber() + ", Valor: " + strAmount + ")\n");
		}
	}
}
