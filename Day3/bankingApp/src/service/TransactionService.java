package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import dao.TransactionDAO;
import dao.TransactionDaoImple;
import exception.InvalidTransactionAmountException;
import exception.TransactionFailureException;
import utility.TransactionHistoryUtil;

public class TransactionService 
{
	private final TransactionDAO transactionDAO ;
	private final ExecutorService executorService;
	
	public TransactionService()
	{
		this.transactionDAO = new TransactionDaoImple();
		this.executorService = Executors.newFixedThreadPool(5);
	}
	
	public Future<?> deposit(int account_id,double amount)
	{
		return executorService.submit(() -> 
		{
			try 
			{
				transactionDAO.deposit(account_id, amount);
				TransactionHistoryUtil.saveTransaction("Deposit", account_id, amount);
			} 
			catch (InvalidTransactionAmountException | SQLException | TransactionFailureException | IOException e) 
			{
				
			}
			
		});
	}

	public  void shutDownExecutorService() 
	{
	    executorService.shutdown();
	}

	public Future<?> withdraw(int account_id, double amount) throws SQLException, TransactionFailureException 
	{
	    return executorService.submit(() -> 
	    {
	        try 
	        {
	            // Validate the amount
	            if (amount <= 0) 
	            {
	                throw new InvalidTransactionAmountException("Withdrawal amount must be positive.");
	            }
	            
	            // Perform the withdrawal operation
	            transactionDAO.withdraw(account_id, amount);
	            
	            // Log the transaction in history
	            TransactionHistoryUtil.saveTransaction("Withdrawal", account_id, amount);
	            
	            return true; // Indicate success
	        } 
	        catch (InvalidTransactionAmountException | IOException e) 
	        {
	            // Handle the exception (you might want to log it)
	            System.err.println("Error processing withdrawal: " + e.getMessage());
	            return false; // Indicate failure
	        }
	    });
	}

	public Future<?> transfer_funds(int from_account_id, int to_account_id, double amount) throws SQLException, TransactionFailureException 
	{
	    return executorService.submit(() -> 
	    {
	        try 
	        {
	            if (amount <= 0) 
	            {
	                throw new InvalidTransactionAmountException("Transfer amount must be positive.");
	            }
	            
	            transactionDAO.transfer_funds(from_account_id, to_account_id, amount);
	            
	            // Log both sides of the transfer transaction
	            TransactionHistoryUtil.saveTransaction("Transfer Out", from_account_id, amount);
	            TransactionHistoryUtil.saveTransaction("Transfer In", to_account_id, amount);
	            
	            return true; // Indicate success
	        } 
	        catch (InvalidTransactionAmountException | IOException e) 
	        {
	            System.err.println("Error processing transfer: " + e.getMessage());
	            return false; // Indicate failure
	        }
	    });
	}


}