package ma.nizar;

import ma.nizar.model.AccountStatus;
import ma.nizar.model.AccountType;
import ma.nizar.model.BankAccount;
import ma.nizar.model.Customer;

public class Main2 {
    public static void main(String[] args) throws CloneNotSupportedException{
        BankAccount account1 = new BankAccount();
        account1.setAccountId(1L);
        account1.setCurrency("MAD");
        account1.setType(AccountType.SAVING_ACCOUNT);
        account1.setStatus(AccountStatus.ACTIVATED);
        account1.setCustomer(new Customer(1L,"Nizar"));


        BankAccount account2 = new BankAccount();
        account2.setAccountId(account1.getAccountId());
        account2.setCurrency(account1.getCurrency());
        account2.setType(account1.getType());
        account2.setStatus(account1.getStatus());

        BankAccount account3 = account1.clone();

        BankAccount account4 = account1;

        System.out.println(account1);
        System.out.println(account3);

        account1.getCustomer().setName("Walid");

        System.out.println(account1);
        System.out.println(account3);



    }
}
