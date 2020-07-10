package com.example.springdatajdbcexample.account;

import java.util.Objects;

import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
public class AccountRepositoryImpl implements AccountRepositoryCustom{
    private final JdbcAggregateOperations jdbcAggregateOperations;

   public void deleteById(Long id) {
       Account account = this.jdbcAggregateOperations.findById(id, Account.class);
       if(Objects.isNull(account)) {
           throw new TransientDataAccessResourceException("존재하지 않는 아이디 입니다. id : " + id);
       }

       this.delete(account);
   }

    public void delete(Account account) {
        account.delete();
        this.jdbcAggregateOperations.update(account);
    }

    public void deleteAll(Iterable<? extends Account> entities) {
       entities.forEach(this::delete);
    }

    public void deleteAll() {
        Iterable<Account> accounts = this.jdbcAggregateOperations.findAll(Account.class);
        deleteAll(accounts);
    }
}
