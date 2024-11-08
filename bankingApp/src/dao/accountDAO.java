package dao;

import java.sql.SQLException;
import exception.InvalidAccountTypeException;
import model.Account;

public interface accountDAO {
	void createAccount(Account account) throws InvalidAccountTypeException, SQLException;

	void updateAccount(int accId, int cusId, String accType, double bal) throws SQLException, InvalidAccountTypeException;

	void deleteAccount(int cusId) throws SQLException;

	void viewAccount(int cusId) throws SQLException;
}
