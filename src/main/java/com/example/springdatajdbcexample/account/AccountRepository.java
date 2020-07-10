package com.example.springdatajdbcexample.account;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Account entity);

    @Override
    void deleteAll(Iterable<? extends Account> entities);

    @Override
    void deleteAll();

    Optional<Account> findByIdAndStateIn(UUID uuid, Set<AccountState> states);

    default Optional<Account> findByIdExcludeDeleted(UUID uuid) {
        return this.findByIdAndStateIn(uuid, EnumSet.of(AccountState.ACTIVE, AccountState.LOCKED));
    }
}
