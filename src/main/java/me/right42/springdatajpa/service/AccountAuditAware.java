package me.right42.springdatajpa.service;

import me.right42.springdatajpa.domain.Account;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountAuditAware implements AuditorAware<Account> {

    @Override
    public Optional<Account> getCurrentAuditor() {
        System.out.println("looking for current user");
        return Optional.empty();
    }
}
