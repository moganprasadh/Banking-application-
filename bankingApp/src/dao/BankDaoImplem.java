package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.InvalidAccountTypeException;
import model.Bank;
import utility.DBConnection;

public  class BankDaoImplem implements BankDao {
	
	@Override
	public Bank getBankById(int Id) throws InvalidAccountTypeException, SQLException {
		String sql="select * from Bank where bank_id = ?";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, Id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			String bankName = rs.getString("bank_name");
			String bankBranch = rs.getString("bank_branch");
			return new Bank(Id, bankName, bankBranch);
		    }
		 }return null;
		}
		
	}
	
