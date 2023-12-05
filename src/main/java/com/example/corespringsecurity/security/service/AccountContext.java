package com.example.corespringsecurity.security.service;

import com.example.corespringsecurity.domain.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 회원정보 context
 */
public class AccountContext extends User {

    private final Account account;

    /**
     * Calls the more complex constructor with all boolean arguments set to {@code true}.
     *
     * @param account
     * @param authorities
     */
    public AccountContext(Account account, Collection<? extends GrantedAuthority> authorities) {
        super(account.getUsername(), account.getPassword(), authorities);
        this.account = account;
    }

    /**
     * @return
     */
    public Account getAccount() {
        return account;
    }
}
