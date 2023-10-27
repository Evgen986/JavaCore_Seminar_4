package org.example.model.account;

import org.example.utils.exception.InsufficientFundsException;

import java.math.BigDecimal;

/**
 * Абстрактная модель счета клиента.
 */
public abstract class Account {
    /**
     * Номер счета.
     */
    private final long number;
    /**
     * Баланс денежных средств на счете.
     */
    private BigDecimal balance;
    /**
     * Тип счета (дебетовый или кредитный)
     */
    private final TypeAccount type;

    /**
     * Закрытый конструктор класса.
     * @param balance баланс средств на счете.
     */
    protected Account(long number, BigDecimal balance, TypeAccount type) {
        this.number = number;
        this.balance = balance;
        this.type = type;
    }


    /**
     * Уменьшение средств на счету
     * @param amount сумма для уменьшения.
     * @throws InsufficientFundsException Превышение остатка средств на счету!
     */
    public void decreaseBalance(BigDecimal amount) throws InsufficientFundsException{
        if (balance.subtract(amount).compareTo(new BigDecimal(0)) < 0) {
            StringBuilder builder = new StringBuilder();
            builder.append("Сумма списания ")
                    .append(amount)
                    .append(" руб. превышает остаток средств на счету! Остаток средств на счету равен: ")
                    .append(balance)
                    .append(" руб.");
            throw new InsufficientFundsException(builder.toString());
        }
        balance = balance.subtract(amount);
    }

    /**
     * Увеличение остатка средств на счету.
     * @param amount сумма для увелечения.
     */
    public void increaseBalance(BigDecimal amount){
        balance = balance.add(amount);
    }

    public long getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
