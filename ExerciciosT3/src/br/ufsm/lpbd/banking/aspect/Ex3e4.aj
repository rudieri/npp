
package br.ufsm.lpbd.banking.aspect;

import java.util.List;

import br.ufsm.lpbd.banking.core.Account;
import br.ufsm.lpbd.banking.core.OverdraftAccount;
import br.ufsm.lpbd.banking.core.OverdraftTransference;
import br.ufsm.lpbd.banking.exception.InsufficientBalanceException;

public aspect Ex3e4 {

	pointcut chamadasDebitEx3e4(float amount, Account a): execution (public void Account+.debit(float) throws InsufficientBalanceException) && args(amount) 
	&& target(a) && !target(OverdraftAccount);
	void around(float amount, Account a) throws InsufficientBalanceException : chamadasDebitEx3e4(amount, a){
		// 150 - 70 = 80, dif = -20
		// 150 - 400 = -250, 
		// 10000 - 10500 = -500, 600 (10500 - 10000 + 100) 
		if(a.getBalance() - amount < 100) {
			List<Account> listaEmprestimos = a.getCustomer().getOverdraftAccounts();
			float dif = amount + 100 - a.getBalance();
			for (Account accEmp: listaEmprestimos) {
				if(accEmp.getBalance() > dif) {
					OverdraftTransference.transfer((OverdraftAccount)accEmp, a, dif);
					proceed(amount, a);
					return;
				}
			}
			throw new InsufficientBalanceException("Limite de segurança atingido.");
		}
		proceed(amount, a);
	}
}
