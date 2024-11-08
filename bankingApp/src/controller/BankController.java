	package controller;
	
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.sql.SQLException;
	
	import exception.InvalidAccountTypeException;
	import model.Account;
	import model.Bank;
import model.CurrentAccount;
import model.SavingsAccount;
	import service.AccountService;
	import service.BankSerivce;
	
	public class BankController {
		private final AccountService accountService;
		private final BankSerivce bankService;
		private final BufferedReader br;
		
		public BankController() {
			this.accountService = new AccountService();
			this.bankService = new BankSerivce();
			this.br = new BufferedReader(new InputStreamReader(System.in));
		}
		public void start() throws NumberFormatException, IOException, SQLException, InvalidAccountTypeException {
			boolean running = true;
			while(running) {
				displayMenu();
				int choice = Integer.parseInt(br.readLine());
				switch(choice) {
				case 1:
					createAccount();
					break;
				case 2:
					updateAccount();break;
				case 3:
					deleteAccount();break;
				case 4:
					viewAccount();break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}
				
			}
		}
		public static void displayMenu() {
			System.out.println("----------Banking Application----------");
			System.out.println("0. Exit");
			System.out.println("1. Create Account");
			System.out.println("2. Update Details");
			System.out.println("3. Delete Account");
			System.out.println("4. View Account");
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
		
		
		
	}
