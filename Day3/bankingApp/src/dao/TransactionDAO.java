package dao;

import java.sql.SQLException;

import exception.InvalidTransactionAmountException;
import exception.TransactionFailureException;

public interface TransactionDAO {
	void deposit(int account_id,double ammount) throws InvalidTransactionAmountException, SQLException, TransactionFailureException;
	void withdraw(int account_id,double ammount) throws InvalidTransactionAmountException, SQLException, TransactionFailureException;
	void transfer_funds(int from_account_id, int to_account_id, double amount) throws SQLException, TransactionFailureException;
}
