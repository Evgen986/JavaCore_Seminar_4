package org.example.utils.exception.account;

/**
 * Абстрактный класс исключений связанный с поведением счетов.
 */
public abstract class AccountException extends RuntimeException{
    /**
     * Конструктор родительского класса RuntimeException
     * @param message сообщение для пользователя.
     */
    public AccountException(String message) {
        super(message);
    }
}
