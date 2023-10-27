package org.example.utils.exception.account;

/**
 * Исключение генерируется при отсутствии аккаунта в БД.
 */
public class AccountNotFoundException extends AccountException{
    /**
     * Конструктор родительского класса AccountException
     * @param message сообщение для пользователя.
     */
    public AccountNotFoundException(String message) {
        super(message);
    }
}
