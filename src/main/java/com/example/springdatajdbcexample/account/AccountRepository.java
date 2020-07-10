package com.example.springdatajdbcexample.account;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.example.springdatajdbcexample.support.WithInsert;

public interface AccountRepository extends CrudRepository<Account, Long>, AccountRepositoryCustom, WithInsert<Account> {
    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Account entity);

    @Override
    void deleteAll(Iterable<? extends Account> entities);

    @Override
    void deleteAll();

    @Override
    Iterable<Account> findAll();

    Optional<Account> findByIdAndStateIn(Long id, Set<AccountState> states);

    default Optional<Account> findByIdExcludeDeleted(Long id) {
        return this.findByIdAndStateIn(id, AccountState.getNotDeleted());
    }
}
