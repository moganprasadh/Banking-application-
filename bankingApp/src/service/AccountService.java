package service;

import dao.accountDAO;
import exception.InvalidAccountTypeException;
import model.Account;

import java.sql.SQLException;

import dao.AccountDAOImplem;

public class AccountService {
	private final accountDAO accountDAO;
	public AccountService() {
		this.accountDAO = new AccountDAOImplem();
		
	}
	public void createAccount(Account account) throws SQLException, InvalidAccountTypeException {
		accountDAO.createAccount(account);
	}
	public void updateAccount(int accId, int cusId, String accType, double bal) throws SQLException, InvalidAccountTypeException {
		accountDAO.updateAccount(accId,cusId,accType,bal);
	}
	public void deleteAccount(int cusId) throws SQLException {
		accountDAO.deleteAccount(cusId);
		
	}
	public void viewAccount(int cusId) throws SQLException {
		accountDAO.viewAccount(cusId);
		
	}


}
