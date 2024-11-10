package model;

import java.util.Date;

public abstract class WithdrawalMethod extends Transaction
{
	private String withdrawalMethod;

	public WithdrawalMethod(int transactionId, int accountId, String transactionType, double amount, Date transactionDate, String withdrawalMethod) 
	{
		super(transactionId, accountId, transactionType, amount, transactionDate);
		this.withdrawalMethod = withdrawalMethod;
	}

	public String getWithdrawalMethod() {
		return withdrawalMethod;
	}

	public void setWithdrawalMethod(String withdrawalMethod) {
		this.withdrawalMethod = withdrawalMethod;
	}
	
	
}
