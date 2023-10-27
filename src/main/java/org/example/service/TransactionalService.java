package org.example.service;

import org.example.data_base.DataBase;
import org.example.model.*;
import org.example.model.account.Account;
import org.example.utils.exception.account.AccountAlreadyExistsException;
import org.example.utils.exception.account.AccountNotFoundException;
import org.example.utils.exception.client.ClientAlreadyExistsException;
import org.example.utils.exception.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Класс осуществляющий транзакции и общение с БД.
 */
public class TransactionalService {

    /**
     * Поле базы данных (не стал вводить дополнительно репозиторий, понимаю, что так не делают.)
     */
    private final DataBase dataBase;

    /**
     * Конструктор класса.
     * @param dataBase объект БД.
     */
    public TransactionalService (DataBase dataBase) {
        this.dataBase = dataBase;
    }

    /**
     * Создание нового клиента.
     * @param client объект клиента.
     */
    public void createNewClient(Client client){
        try {
            dataBase.createClient(client);
        }catch (ClientAlreadyExistsException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Создание нового счета в БД.
     * @param client объект клиента
     * @param account объект счета.
     */
    public void createAccount(Client client, Account account){
        try {
            dataBase.checkAccountInDataBase(client, account);
            dataBase.updateAccount(client, account);
        }catch (IllegalArgumentException | AccountAlreadyExistsException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Внесение средств на счет.
     * @param client объект клиента.
     * @param account объект счета.
     * @param amount сумма для внесения.
     */
    public void credit(Client client, Account account, BigDecimal amount){
        try {
            checkAmount(amount);
            account.increaseBalance(amount);
            dataBase.updateAccount(client, account);
        }catch (IllegalArgumentException | AccountNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Списание средств со счета.
     * @param client объект клиента.
     * @param account объект счета.
     * @param amount сумма для списания.
     */
    public void debit(Client client, Account account, BigDecimal amount){
        try {
            checkAmount(amount);
            account.decreaseBalance(amount);
            dataBase.updateAccount(client, account);
        }catch (InsufficientFundsException | AccountNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Перевод денежных средств между счетами.
     * @param clientCredit клиент отправляющий деньги.
     * @param clientDebit клиент получающий деньги.
     * @param accountCredit счет списания.
     * @param accountDebit счет зачисления.
     * @param amount сумма перевода.
     */
    public void transaction(Client clientCredit,
                            Client clientDebit,
                            Account accountCredit,
                            Account accountDebit,
                            BigDecimal amount){
        checkAmount(amount);
        try {
            if (accountCredit.getBalance().subtract(amount).compareTo(new BigDecimal(0)) < 0)
                throw new InsufficientFundsException("На счету недостаточно средств для проведения операции!");
            credit(clientCredit, accountCredit, amount);
            debit(clientDebit, accountDebit, amount);
        }catch (InsufficientFundsException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Печать данных о счетах клиента
     * @param client объект клиента.
     */
    public void printClientData(Client client){
        List<Account> data = dataBase.getClientData(client);
        System.out.printf("Данные о клиенте %s:\n", client.getName());
        for (Account account : data){
            System.out.printf("Счет № %d, баланс: %s руб.\n", account.getNumber(), account.getBalance());
        }
    }

    /**
     * Служебный метод, проверки сумма транзакции на корректность.
     * Отрицательная сумма не допустима.
     * @param amount сумма транзакции.
     * @throws IllegalArgumentException Введена отрицательная сумма!
     */
    private void checkAmount(BigDecimal amount) throws IllegalArgumentException{
        if (amount.compareTo(new BigDecimal(0)) < 0)
            throw new IllegalArgumentException("Введена отрицательная сумма!");
    }

}
