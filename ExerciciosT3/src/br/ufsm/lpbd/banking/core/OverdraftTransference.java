package br.ufsm.lpbd.banking.core;

import br.ufsm.lpbd.banking.exception.InsufficientBalanceException;

public class OverdraftTransference {
	public static void transfer(OverdraftAccount source, Account dest, float amount) throws InsufficientBalanceException{
		source.debit(amount);
		dest.credit(amount);
	}
}
