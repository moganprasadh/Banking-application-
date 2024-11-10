	package controller;
	
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import exception.InvalidAccountTypeException;
import exception.TransactionFailureException;
import model.Account;
	import model.Bank;
import model.CurrentAccount;
import model.SavingsAccount;
	import service.AccountService;
	import service.BankSerivce;
import service.TransactionService;
	
	public class BankController {
		private final AccountService accountService;
		private final BankSerivce bankService;
		private final BufferedReader br;
		private final TransactionService transactionService;
		
		public BankController() {
			this.accountService = new AccountService();
			this.bankService = new BankSerivce();
			this.br = new BufferedReader(new InputStreamReader(System.in));
			this.transactionService = new TransactionService();
		}
		public void start() throws NumberFormatException, IOException, SQLException, InvalidAccountTypeException, InterruptedException, ExecutionException, TransactionFailureException {
			boolean running = true;
			while(running) {
				displayMenu();
				int choice = Integer.parseInt(br.readLine());
				switch(choice) {
				case 1:
					createAccount();
					break;
				case 2:
					updateAccount();
					break;
				case 3:
					deleteAccount();
					break;
				case 4:
					viewAccount();
					break;
				case 5:
					deposit();
					break;
				case 6:
					withdraw();
					break;
				case 7:
					transferFunds();
				case 8:
					//view viewDetailsByBankId();
				case 0:
					running=false;
					transactionService.shutDownExecutorService();
					System.out.println("exciting the app! Gpoodbye");
					break;
					
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}
				
			}
		}
		private void transferFunds() throws SQLException, TransactionFailureException, NumberFormatException, IOException, InterruptedException, ExecutionException {
			System.out.println("Enter From Account No: ");
		    int fromAccountId = Integer.parseInt(br.readLine());

		    System.out.println("Enter To Account No: ");
		    int toAccountId = Integer.parseInt(br.readLine());

		    System.out.println("Enter the amount to be transferred");
		    double amount = Double.parseDouble(br.readLine());
		    
		    Future<?> future = transactionService.transfer_funds(fromAccountId, toAccountId, amount);
		    if (future.get() != null) 
		    {
		        System.out.println("Transfer successful!");
		    } 
		    else 
		    {
		        System.out.println("Transfer failed. Please check account balances or try again.");
		    }
			
		}
		public static void displayMenu() {
			System.out.println("----------Banking Application----------");
			System.out.println("0. Exit");
			System.out.println("1. Create Account");
			System.out.println("2. Update Details");
			System.out.println("3. Delete Account");
			System.out.println("4. View Account");
			System.out.println("5. Deposit");
			System.out.println("6. Withdraw");
			System.out.println("7. Transfer Funds");
			System.out.print("Enter the choice: ");
		}
		
		public void createAccount() throws NumberFormatException, IOException, SQLException, InvalidAccountTypeException {
			System.out.println("Enter Customer Id: ");
			int cusId = Integer.parseInt(br.readLine());
			
			System.out.println("Enter Bank Id: ");
			int bankId = Integer.parseInt(br.readLine());
			Bank bank=bankService.getBankId(bankId);
			
			System.out.println("Enter Account Type(Savings/Current): ");
			String accountType = br.readLine();
			
			System.out.println("Enter Initial balance: ");
			double bal = Double.parseDouble(br.readLine());
			
			if("Savings".equalsIgnoreCase(accountType)) {
				System.out.println("Enter Interest rate: ");
				double interest = Double.parseDouble(br.readLine());
				accountService.createAccount(new SavingsAccount(0,cusId,bank,accountType,bal,interest));
			}
			else if("Current".equalsIgnoreCase(accountType)) {
				System.out.println("Enter OverdraftLimit: ");
				double overDraft = Double.parseDouble(br.readLine());
				accountService.createAccount(new CurrentAccount(0,cusId,bank,accountType,bal,overDraft));
			}
			else{
				System.out.println("Invalid Account Type");
			}
		}
		
		public void updateAccount() throws NumberFormatException, IOException, SQLException, InvalidAccountTypeException {
			System.out.print("Enter the account Id: ");
			int accId = Integer.parseInt(br.readLine());
			System.out.println("\nEnter the new details");
			System.out.print("Enter Customer Id: ");
			int cusId  = Integer.parseInt(br.readLine());
			System.out.print("Enter Account Type: ");
			String accType = br.readLine();
			System.err.print("Enter Initial Balance: ");
			double bal = Double.parseDouble(br.readLine());
			accountService.updateAccount(accId, cusId, accType, bal);
		}
		
		public void deleteAccount() throws NumberFormatException, IOException, SQLException {
			System.out.print("Enter the customer Id: ");
			int cusId  = Integer.parseInt(br.readLine());
			accountService.deleteAccount(cusId);
		}
		
		public void viewAccount() throws NumberFormatException, IOException, SQLException {
			System.out.print("Enter the customer Id: ");
			int cusId = Integer.parseInt(br.readLine());
			accountService.viewAccount(cusId);
		}
		
		private void deposit() throws NumberFormatException, IOException, InterruptedException, ExecutionException {
			System.out.println("Enter the account ID:");
			int Id=Integer.parseInt(br.readLine());
			System.out.println("Enter the amount to be deposited");
			double amount=Double.parseDouble(br.readLine());
			Future<?>  future =transactionService.deposit(Id,amount);
			future.get();
		}
		
		private void withdraw() throws NumberFormatException, IOException, InterruptedException, ExecutionException, SQLException, TransactionFailureException
		{
		    System.out.println("Enter Account No: ");
		    int id = Integer.parseInt(br.readLine());
		    System.out.println("Enter the amount to be withdrawn");
		    double amount = Double.parseDouble(br.readLine());
		    
		    Future<?> future = transactionService.withdraw(id, amount);
		    
		    if (future.get() != null) {
		        System.out.println("Withdrawal successful!");
		    } else {
		        System.out.println("Withdrawal failed. Please check the account balance and try again.");
		    }
		}
		
		
		
		
	}
