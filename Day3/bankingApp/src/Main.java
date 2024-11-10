import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import controller.BankController;
import exception.BankingException;
import exception.InvalidAccountTypeException;
import exception.TransactionFailureException;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException, SQLException, BankingException, InvalidAccountTypeException, InterruptedException, ExecutionException, TransactionFailureException {
        BankController bc = new BankController();
        bc.start();
    }
}
