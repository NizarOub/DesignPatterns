package ma.nizar;

import ma.nizar.model.AccountStatus;
import ma.nizar.model.AccountType;
import ma.nizar.model.BankAccount;
import ma.nizar.model.BankDirector;
import ma.nizar.repository.AccountRepositoryImp;
import ma.nizar.util.JsonSerializer;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        /*
        // 1ere méthode
        BankAccount account1 = new BankAccount(1L,10000,"MAD",AccountType.SAVING_ACCOUNT,AccountStatus.CREATED);
        System.out.println(account1.toString());

        // 2eme méthode
        BankAccount account2 = new BankAccount();
        account2.setAccountId(2L);
        account2.setBalance(20000);
        account2.setCurrency("MAD");
        account2.setType(AccountType.SAVING_ACCOUNT);
        account2.setStatus(AccountStatus.CREATED);
        System.out.println(account2.toString());

        // 3eme méthode
        BankAccount account3 = BankDirector.accountBuilder()
                .accountId(3L)
                .currency("MAD")
                .balance(30000.0)
                .type(AccountType.CURRENT_ACCOUNT)
                .status(AccountStatus.ACTIVATED)
                .build();
        System.out.println(account3.toString());
         */


        JsonSerializer<BankAccount> banckAccountJsonSerializer = new JsonSerializer<>();
        AccountRepositoryImp accountRepositoryImp = new AccountRepositoryImp();
        accountRepositoryImp.populateData();
        List<BankAccount> bankAccounts = accountRepositoryImp.findAll();
        bankAccounts.stream()
                .map(banckAccountJsonSerializer::toJson)
                .forEach(System.out::println);

        System.out.println("=====================================");

        BankAccount account2 = accountRepositoryImp.findByID(2L).orElse(null);
        if (account2 != null){
            System.out.println(banckAccountJsonSerializer.toJson(account2));
        }

        System.out.println("=====================================");


        List<BankAccount> bankAccounts1 = accountRepositoryImp.searchAccounts(new Predicate<BankAccount>() {
            @Override
            public boolean test(BankAccount bankAccount) {
                return bankAccount.getType().equals(AccountType.CURRENT_ACCOUNT);
            }
        });
        bankAccounts1.stream()
                .map(banckAccountJsonSerializer::toJson)
                .forEach(System.out::println);
    }
}