package org.example.model.account;

import java.math.BigDecimal;

/**
 * Кредитный счет.
 */
public class CreditAccount extends Account{
    protected CreditAccount(long number, BigDecimal balance, TypeAccount type) {
        super(number, balance, type);
    }


    /**
     * Создание нового кредитного счета.
     * @param balance сумма средств
     * @return новый счет.
     * @throws IllegalArgumentException Сумма средств на счете не может быть отрицательной!
     */
    public static Account createAccount(long number, BigDecimal balance, TypeAccount type)
            throws IllegalArgumentException {
        if (balance.compareTo(new BigDecimal(0)) < 0)
            throw new IllegalArgumentException("Сумма средств на счете не может быть отрицательной!");
        return new CreditAccount(number, balance, type);
    }
}
