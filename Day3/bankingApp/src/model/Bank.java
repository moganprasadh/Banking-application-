package model;

public class Bank 
{
	private int bank_id;
	private String bankName;
	private String bankBranch;
	
	//Constructors
	public Bank(int bank_id, String bankName, String bankBranch) 
	{
		this.bank_id = bank_id;
		this.bankName = bankName;
		this.bankBranch = bankBranch;
	}
	
	//Getters and Setters
	public int getBankid() 
	{
		return bank_id;
	}

	public void setBankid(int bank_id) 
	{
		this.bank_id = bank_id;
	}
	
	public String getBankName() 
	{
		return bankName;
	}
	
	public void setBankName(String bankName) 
	{
		this.bankName = bankName;
	}
	
	public String getBankBranch() 
	{
		return bankBranch;
	}
	
	public void setBankBranch(String bankBranch) 
	{
		this.bankBranch = bankBranch;
	}
	
}
