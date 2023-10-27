package org.example.data_base;

import org.example.model.account.Account;
import org.example.model.Client;
import org.example.utils.exception.account.AccountAlreadyExistsException;
import org.example.utils.exception.account.AccountNotFoundException;
import org.example.utils.exception.client.ClientAlreadyExistsException;
import org.example.utils.exception.client.ClientNotFoundException;

import java.util.*;

/**
 * Имитация базы данных.
 */
public class DataBase {
    /**
     * Имитация хранилища записей клиентов и их счетов.
     */
    private final Map<Client, List<Account>> storage = new HashMap<>();

    /**
     * Создание клиента в хранилище.
     * @param client объект клиента.
     * @throws ClientAlreadyExistsException Клиент уже существует!
     */
    public void createClient(Client client) throws ClientAlreadyExistsException{
        if (storage.containsKey(client)) throw new ClientAlreadyExistsException("Клиент уже существует!");
        storage.put(client, new ArrayList<>());
    }

    /**
     * Обновление данных о счете.
     * @param client объект клиента.
     * @param account объект счета.
     * @throws ClientNotFoundException Данные о клиенте не обнаружены!
     */
    public void updateAccount(Client client, Account account) throws ClientNotFoundException{
        if (!storage.containsKey(client))
            throw new ClientNotFoundException("Данные о клиенте не обнаружены!");
        if (account != null){
            int index = indexAccountExists(client, account);
            // Если такого счета у пользователя не было, добавляем новый счет.
            if (index == -1){
                storage.get(client).add(account);
            }
            // Если счет есть, изменяем сумму.
            else{
                storage.get(client).get(index).setBalance(account.getBalance());
            }
        }
    }

    /**
     * Получение сведений о счетах клиента.
     * @param client объект клиента.
     * @return список счетов клиента.
     * @throws ClientNotFoundException Данные о клиенте не обнаружены!
     */
    public List <Account> getClientData (Client client) throws ClientNotFoundException{
        if (!storage.containsKey(client))
            throw new ClientNotFoundException("Данные о клиенте не обнаружены!");
        return storage.get(client);
    }

    /**
     * Проверка счета на существование в БД.
     * @param client объект клиента.
     * @param account объект счета.
     * @throws AccountNotFoundException Счет уже существует!
     */
    public void checkAccountInDataBase(Client client, Account account) throws AccountNotFoundException{
        List <Account> clientData = getClientData(client);
        if (clientData != null) {
            for (Account accountClient : clientData) {
                if (accountClient.equals(account)) {
                    account = accountClient;
                    throw new AccountAlreadyExistsException("Счет уже существует!");
                }
            }
        }
    }

    /**
     * Служебный метод получения индекса в листе хранения счетов клиента.
     * @param client
     * @param account
     * @return
     */
    private int indexAccountExists(Client client, Account account){
        List <Account> clientData = getClientData(client);
        if (clientData != null) {
            for (int i = 0; i < clientData.size(); i++) {
                if (clientData.get(i).equals(account) ) return i;
            }
        }
        return -1;
    }
}
