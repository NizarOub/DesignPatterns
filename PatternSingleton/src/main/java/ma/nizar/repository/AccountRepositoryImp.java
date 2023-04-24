package ma.nizar.repository;

import ma.nizar.model.AccountStatus;
import ma.nizar.model.AccountType;
import ma.nizar.model.BankAccount;
import ma.nizar.model.BankDirector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class AccountRepositoryImp implements AccountRepository {

    private static final AccountRepositoryImp accountRepositoryImp;
    static {
        System.out.println("Instantiation du Singleton");
        accountRepositoryImp = new AccountRepositoryImp();
    }

    private AccountRepositoryImp() {}

    private Map<Long,BankAccount> bankAccountMap = new HashMap<>();
    private long accountsCount = 0;

    @Override
    public synchronized BankAccount save(BankAccount bankAccount) {
        Long accountId;
        //synchronized (this){
            accountId = ++ accountsCount; // Critical zone
            bankAccount.setAccountId(accountId);
            bankAccountMap.put(accountId,bankAccount);
        //}
        return bankAccount;
    }

    @Override
    public List<BankAccount> findAll() {
        return bankAccountMap.values()
                .stream()
                .toList();
    }

    @Override
    public Optional<BankAccount> findByID(Long id) {
        BankAccount account = bankAccountMap.get(id);
        if (account == null){
            return Optional.empty();
        }
        else
            return Optional.of(account);
    }

    @Override
    public List<BankAccount> searchAccounts(Predicate<BankAccount> predicate) {
        return bankAccountMap.values()
                .stream()
                .filter(predicate)
                .toList();
    }

    @Override
    public BankAccount update(BankAccount bankAccount) {
        bankAccountMap.put(bankAccount.getAccountId(),bankAccount);
        return bankAccount;
    }

    @Override
    public void deleteById(Long id) {
        bankAccountMap.remove(id);
    }

    public void populateData(){
        for (int i = 0; i < 10; i++) {
            BankAccount account = BankDirector.accountBuilder()
                    .balance(10000 + Math.random()*90000)
                    .type(Math.random()>0.5 ? AccountType.SAVING_ACCOUNT:AccountType.CURRENT_ACCOUNT)
                    .status(Math.random() > 0.5 ? AccountStatus.CREATED:AccountStatus.ACTIVATED)
                    .currency(Math.random() > 0.5 ? "MAD":"USD")
                    .build();
            save(account);
        }
        System.out.println("===========================================");
        System.out.println(Thread.currentThread().getName());
        System.out.println("Account Count = " + accountsCount);
        System.out.println("Size = " + bankAccountMap.values().size());
        System.out.println("===========================================");
    }

    public static AccountRepositoryImp getInstance(){
        /*
        if (accountRepositoryImp == null){
            System.out.println("Instantiation du Singleton");
            accountRepositoryImp = new AccountRepositoryImp();
            accountRepositoryImp.populateData();
        }
         */
        return accountRepositoryImp;
    }
}
