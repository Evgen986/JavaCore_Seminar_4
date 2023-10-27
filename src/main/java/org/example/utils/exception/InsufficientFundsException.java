package org.example.utils.exception;

/**
 * Исключение генерирующееся при недостаточном количестве средств на счету.
 */
public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(String message) {
        super(message);
    }
}
