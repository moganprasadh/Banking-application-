package service;

import java.sql.SQLException;

import dao.BankDao;
import dao.BankDaoImplem;
import exception.InvalidAccountTypeException;
import model.Bank;

public class BankSerivce {
		private final BankDao bankdao;
		
		public BankSerivce() {
			this.bankdao = new BankDaoImplem();
		}
		public Bank getBankId(int id) throws InvalidAccountTypeException, SQLException{
			return this.bankdao.getBankById(id);
		}
}
