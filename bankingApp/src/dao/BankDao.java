package dao;

import java.sql.SQLException;

import exception.InvalidAccountTypeException;
import model.Bank;

public interface BankDao {
	Bank getBankById(int bankId) throws InvalidAccountTypeException, SQLException;
}
