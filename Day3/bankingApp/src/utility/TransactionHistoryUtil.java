package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TransactionHistoryUtil {
    static final String File_Path="transaction_history.txt";
    static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    //to save all the Transaction in a file
    
    public static void saveTransaction(String transactiontype,int accountId,double amount) throws IOException {
    	String timestamp=dateFormat.format(new Date());
    	String record=String.format("%s | %s, Account ID: %d|Amount|Amount:%.2f",timestamp,transactiontype,accountId,amount);
    	try(BufferedWriter writer = new BufferedWriter(new FileWriter(File_Path,true))){
    		writer.write(record);
    		writer.newLine();
    	}catch(IOException e) {
    		System.out.println("Error Writing to transaction history file");
    	}
	}
    
    //Method to retrive and display transaction history
    
    public static List<String> retrieveTransaction() throws FileNotFoundException, IOException{
    	List<String> history = new ArrayList<String>();
    	try(BufferedReader reader = new BufferedReader(new FileReader(File_Path))){
    		String line;
    		while((line=reader.readLine()) !=null) {
    			history.add(line);
    		}
    	}catch(IOException e) {
    		System.err.println("Error reading transaction");
    	}
    	return history;
    }
}