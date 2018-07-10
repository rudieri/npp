
package br.ufsm.lpbd.banking.aspect;



import java.text.DecimalFormat;

import br.ufsm.lpbd.banking.core.Account;

public aspect Ex2 {

	pointcut chamadasDebit(float amount, Account a): call (public void Account+.debit(float) throws *) && args(amount) && target(a);
	
	before(float amount, Account a) : chamadasDebit(amount, a){
		String nome = thisJoinPoint.getSignature().getName();
		DecimalFormat dc = new DecimalFormat("#.00");
		String strAmount = dc.format(amount);
		System.out.println("Método: " + nome + ", Conta: " + a.getAccountNumber() + ", Valor: " + strAmount  + ")");
	}
	after(float amount, Account a) : chamadasDebit(amount, a){
		DecimalFormat dc = new DecimalFormat("#.00");
		String strAmount = dc.format(a.getBalance());
		System.out.println("Saldo da conta: " + strAmount);
	}
	
	pointcut chamadasCredit(float amount, Account a): call (public void Account+.credit(float)) && args(amount) && target(a);
	before(float amount, Account a) : chamadasCredit(amount, a){
		String nome = thisJoinPoint.getSignature().getName();
		DecimalFormat dc = new DecimalFormat("#.00");
		String strAmount = dc.format(amount);
		System.out.println("Método: " + nome + ", Conta: " + a.getAccountNumber() + ", Valor: " + strAmount  + ")");
	}
	after(float amount, Account a) : chamadasCredit(amount, a){
		DecimalFormat dc = new DecimalFormat("#.00");
		String strAmount = dc.format(amount);
		System.out.println("Saldo da conta: " + strAmount);
	}
}
