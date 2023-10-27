package org.example;

import org.example.data_base.DataBase;
import org.example.model.*;
import org.example.model.account.Account;
import org.example.model.account.CreditAccount;
import org.example.model.account.DebitAccount;
import org.example.model.account.TypeAccount;
import org.example.service.TransactionalService;

import java.math.BigDecimal;

/**
 * Тестирование программы:
 * План тестирования:
 * 1.	Попытка добавить в БД уже существующего клиента;
 * 2.	Попытка добавить уже существующий счет в БД;
 * 3.	Попытка зачислить на счет отрицательную сумму;
 * 4.	Попытка списать сумму, превышающую остаток на счете;
 * 5.	Попытка перевести деньги другому клиенту больше чем есть на счете у отправляющего клиента.
 */
public class Main {
    public static void main(String[] args) {
        // Создание объектов необходимых для работы программы.
        DataBase dataBase = new DataBase();
        TransactionalService service = new TransactionalService(dataBase);

        // Создание объектов клиентов и счетов.
        Client client1 = new Client("Вася Пупкин");
        Account creditAccount1 = CreditAccount.createAccount(555, new BigDecimal(1000), TypeAccount.CREDIT);
        Account debitAccount1 = DebitAccount.createAccount(555, new BigDecimal(2000), TypeAccount.DEBIT);

        Client client2 = new Client("Петя Иванов");
        Account creditAccount2 = CreditAccount.createAccount(358, new BigDecimal(5000), TypeAccount.CREDIT);
        Account debitAccount2 = DebitAccount.createAccount(254, new BigDecimal(7000), TypeAccount.DEBIT);


        // Тестирование программы
        // Добавление новых клиентов в БД
        service.createNewClient(client1);
        service.createNewClient(client2);
        // Попытка добавить в БД уже существующего клиента
        System.out.print("1. ");
        service.createNewClient(client1);

        // Добавление счетов клиентам
        service.createAccount(client1, creditAccount1);
        service.createAccount(client1, debitAccount1);
        service.createAccount(client2, creditAccount2);
        service.createAccount(client2, debitAccount2);
        // Попытка добавить существующий счет в БД.
        System.out.print("2. ");
        service.createAccount(client1, creditAccount1);

        System.out.println("\n============== ДАННЫЕ О СЧЕТАХ КЛИЕНТОВ ==============");
        service.printClientData(client1);
        System.out.println();
        service.printClientData(client2);

        // Зачисление денежных средств на счет клиента №1
        service.credit(client1, creditAccount1, new BigDecimal(2000));
        System.out.println("\n============== ДАННЫЕ О СЧЕТАХ КЛИЕНТОВ ==============");
        service.printClientData(client1);
        // Попытка зачисления некорректной суммы
        System.out.print("3. ");
        service.credit(client1, creditAccount1, new BigDecimal(-2000));

        // Списание денежных средств со счета #1
        service.debit(client1, creditAccount1, new BigDecimal(500));
        System.out.println("\n============== ДАННЫЕ О СЧЕТАХ КЛИЕНТОВ ==============");
        service.printClientData(client1);
        // Попытка списания средств больше, чем находится на счете
        System.out.print("4. ");
        service.debit(client1, creditAccount1, new BigDecimal(999999));

        System.out.println("\n============== ДАННЫЕ О СЧЕТАХ КЛИЕНТОВ ==============");
        service.printClientData(client1);
        service.printClientData(client2);

        // Перевод денежных средств между клиентами
        System.out.println("\n============== ПЕРЕВОД СРЕДСТВ МЕЖДУ КЛИЕНТАМИ ==============");
        service.transaction(client1, client2, creditAccount1, debitAccount2, new BigDecimal(1000));
        System.out.println("============== ДАННЫЕ О СЧЕТАХ КЛИЕНТОВ ==============");
        service.printClientData(client1);
        service.printClientData(client2);

        System.out.print("\n5. ");
        // Попытка перевода при недостаточном балансе
        service.transaction(client1, client2, creditAccount1, debitAccount2, new BigDecimal(9999999));
        System.out.println("\n============== ДАННЫЕ О СЧЕТАХ КЛИЕНТОВ ==============");
        service.printClientData(client1);
        service.printClientData(client2);

    }
}