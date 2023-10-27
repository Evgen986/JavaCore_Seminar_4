package org.example.model.account;

import java.math.BigDecimal;

/**
 * Дебетовый счет.
 */
public class DebitAccount extends Account{
    protected DebitAccount(long number, BigDecimal balance, TypeAccount type) {
        super(number, balance, type);
    }

    /**
     * Создание нового дебетового счета.
     * @param balance сумма средств
     * @return новый счет.
     * @throws IllegalArgumentException Сумма средств на счете не может быть отрицательной!
     */
    public static Account createAccount(long number, BigDecimal balance, TypeAccount type)
            throws IllegalArgumentException {
        if (balance.compareTo(new BigDecimal(0)) < 0)
            throw new IllegalArgumentException("Сумма средств на счете не может быть отрицательной!");
        return new DebitAccount(number, balance, type);
    }
}
