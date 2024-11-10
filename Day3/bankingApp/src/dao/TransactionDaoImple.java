package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import exception.InvalidTransactionAmountException;
import exception.TransactionFailureException;
import utility.DBConnection;

public class TransactionDaoImple implements TransactionDAO {

	@Override
	synchronized public void deposit(int account_id, double amount) throws InvalidTransactionAmountException, SQLException, TransactionFailureException {
		if(amount<0) {
			throw new InvalidTransactionAmountException("Deposit amount should be grater than 0");
		}
		try(Connection con = DBConnection.getConnection()){
			CallableStatement st=con.prepareCall("{CALL deposit_procedure(?,?)}"); 
			st.setInt(1, account_id);
			st.setDouble(2, amount);
			st.execute();
		}catch(SQLException e) {
			throw new TransactionFailureException("Deposit failed"+e.getMessage());
		}
		
	}

	@Override
	synchronized public void withdraw(int account_id, double amount) throws InvalidTransactionAmountException, SQLException, TransactionFailureException 
	{
	    if (amount <= 0) 
	    {
	        throw new InvalidTransactionAmountException("Withdrawal amount must be greater than 0");
	    }
	    try (Connection con = DBConnection.getConnection()) 
	    {
	        CallableStatement cs = con.prepareCall("{CALL withdraw(?, ?)}");
	        cs.setInt(1, account_id);
	        cs.setDouble(2, amount);
	        cs.execute();
	    } 
	    catch (SQLException e) 
	    {
	        throw new TransactionFailureException("Withdrawal failed: " + e.getMessage());
	    }
	}


	@Override
	synchronized public void transfer_funds(int from_account_id, int to_account_id, double amount) throws SQLException, TransactionFailureException 
	{
	    try (Connection con = DBConnection.getConnection()) 
	    {
	        CallableStatement cs = con.prepareCall("{CALL transfer_funds(?, ?, ?)}");
	        cs.setInt(1, from_account_id);
	        cs.setInt(2, to_account_id);
	        cs.setDouble(3, amount);
	        cs.execute();
	    } 
	    catch (SQLException e) 
	    {
	        throw new TransactionFailureException("Transfer failed: " + e.getMessage());
	    }
	}

	
}
