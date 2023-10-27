package org.example.utils.exception.client;

/**
 * Исключение генерируемое в случае присутствия клиента в БД.
 */
public class ClientAlreadyExistsException extends ClientException{
    /**
     * Конструктор родительского класса ClientException
     * @param message сообщение для пользователя.
     */
    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}
