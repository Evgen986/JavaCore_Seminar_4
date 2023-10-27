package org.example.utils.exception.client;

/**
 * Абстрактный класс исключений связанным с поведением клиента.
 */
public abstract class ClientException extends RuntimeException{
    /**
     * Конструктор родительского класса RuntimeException
     * @param message сообщение для пользователя.
     */
    public ClientException(String message) {
        super(message);
    }
}
