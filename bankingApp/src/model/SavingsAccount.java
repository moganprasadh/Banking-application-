package model;

public class SavingsAccount extends Account
{
	private double interestRate;
	
	//Constructors
	public SavingsAccount(int accountId, int customerId, Bank bank, String accountType, double balance, double interestRate) 
	{
		super(accountId, customerId, bank, accountType, balance);
		this.interestRate = interestRate;
	}
	
	//Getters and Setters
	public double getInterestRate() 
	{
		return interestRate;
	}

	public void setInterestRate(double interestRate) 
	{
		this.interestRate = interestRate;
	}
	@Override
	public String getAccountDetails() {
		return "Savings Account with interest rate " + this.interestRate;
	}
	@Override
	public String toString() {
		return "Savings Account Details : Account Id"+this.getAccountDetails()+"Customer Id:"+this.getCustomerId()+"Bank:"+this.getBank()+"AccountType:"+this.getAccountType()+"balance:"+this.getBalance()+"interest rate:"+this.getInterestRate();                                                                      
	}
}
