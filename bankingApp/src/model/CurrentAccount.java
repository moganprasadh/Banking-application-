package model;

public class CurrentAccount extends Account
{
	private double overdraftLimit;
	
	//Constructors
	public CurrentAccount(int accountId, int customerId, Bank bank, String accountType, double balance,  double overdraftLimit) 
	{
		super(accountId, customerId, bank, accountType, balance);
		this.overdraftLimit = overdraftLimit;
	}

	//Getters and Setters
	public double getOverdraftLimit() 
	{
		return overdraftLimit;
	}

	public void setOverdraftLimit(double overdraftLimit) 
	{
		this.overdraftLimit = overdraftLimit;
	}
	
	@Override
	public String getAccountDetails() {
		return "Current Account with overdraft limit " + this.overdraftLimit;
	}
	@Override
	public String toString() {
		return "Current Account Details : Account Id"+this.getAccountDetails()+"Customer Id:"+this.getCustomerId()+"Bank:"+this.getBank()+"AccountType:"+this.getAccountType()+"balance:"+this.getBalance()+"overdraftLimit:"+this.getOverdraftLimit();                                                                      
	}
	
}
