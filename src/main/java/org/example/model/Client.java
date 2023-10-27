package org.example.model;

import org.example.model.account.Account;

import java.util.List;

/**
 * Модель клиента.
 */
public class Client {
    /**
     * ФИО клиента.
     */
    private String name;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
