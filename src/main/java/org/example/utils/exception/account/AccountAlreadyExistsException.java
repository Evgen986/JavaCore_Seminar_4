package org.example.utils.exception.account;

/**
 * Исключение генерирующееся при присутствии в БД проверяемого счета.
 */
public class AccountAlreadyExistsException extends AccountException{
    /**
     * Конструктор родительского класса AccountException
     * @param message сообщение для пользователя.
     */
    public AccountAlreadyExistsException(String message) {
        super(message);
    }
}
