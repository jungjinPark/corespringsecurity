package com.example.corespringsecurity.security.common;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class FormAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
    /**
     * Called by a class when it wishes a new authentication details instance to be
     * created.
     *
     * @param context the request object, which may be used by the authentication details
     *                object
     * @return a fully-configured authentication details instance
     */
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new FormWebAuthenticationDetails(context);
    }
}
