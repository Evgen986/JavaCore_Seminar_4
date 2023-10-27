package org.example.utils.exception.client;

/**
 * Исключение генерируемое при отсутствии клиента в БД.
 */
public class ClientNotFoundException extends ClientException{
    /**
     * Конструктор родительского класса ClientException
     * @param message сообщение для пользователя.
     */
    public ClientNotFoundException(String message) {
        super(message);
    }
}
