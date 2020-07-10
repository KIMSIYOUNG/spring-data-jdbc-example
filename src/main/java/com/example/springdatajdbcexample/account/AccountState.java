package com.example.springdatajdbcexample.account;

import java.util.EnumSet;
import java.util.Set;

public enum AccountState {
    ACTIVE,
    LOCKED,
    DELETED;

    AccountState() {
    }

    static Set<AccountState> getNotDeleted() {
        return EnumSet.of(AccountState.ACTIVE, AccountState.LOCKED);
    }
}
