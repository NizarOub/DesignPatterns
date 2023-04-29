package ma.nizar;

import ma.nizar.model.AccountStatus;
import ma.nizar.model.AccountType;
import ma.nizar.model.BankAccount;
import ma.nizar.model.BankDirector;
import ma.nizar.repository.AccountRepositoryImp;
import ma.nizar.util.JsonSerializer;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        JsonSerializer<BankAccount> banckAccountJsonSerializer = new JsonSerializer<>();
        AccountRepositoryImp accountRepositoryImp = AccountRepositoryImp.getInstance();

        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                accountRepositoryImp.populateData();
            }).start();
        }
        System.out.print("Tape a character: ");
        System.in.read();

        // accountRepositoryImp.populateData();
        List<BankAccount> bankAccounts = accountRepositoryImp.findAll();
        bankAccounts.stream()
                .map(banckAccountJsonSerializer::toJson)
                .forEach(System.out::println);

    }
}