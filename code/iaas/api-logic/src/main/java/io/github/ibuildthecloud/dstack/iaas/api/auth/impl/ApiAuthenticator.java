package io.github.ibuildthecloud.dstack.iaas.api.auth.impl;

import io.github.ibuildthecloud.dstack.api.auth.Policy;
import io.github.ibuildthecloud.dstack.archaius.util.ArchaiusUtil;
import io.github.ibuildthecloud.dstack.core.model.Account;
import io.github.ibuildthecloud.dstack.iaas.api.auth.AccountLookup;
import io.github.ibuildthecloud.dstack.iaas.api.auth.AuthorizationProvider;
import io.github.ibuildthecloud.dstack.iaas.api.auth.dao.AuthDao;
import io.github.ibuildthecloud.gdapi.context.ApiContext;
import io.github.ibuildthecloud.gdapi.exception.ClientVisibleException;
import io.github.ibuildthecloud.gdapi.factory.SchemaFactory;
import io.github.ibuildthecloud.gdapi.request.ApiRequest;
import io.github.ibuildthecloud.gdapi.request.handler.AbstractApiRequestHandler;
import io.github.ibuildthecloud.gdapi.util.ResponseCodes;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.config.DynamicBooleanProperty;

public class ApiAuthenticator extends AbstractApiRequestHandler {

    private static final DynamicBooleanProperty SECURITY = ArchaiusUtil.getBoolean("api.security.enabled");
    private static final Logger log = LoggerFactory.getLogger(ApiAuthenticator.class);

    AuthDao authDao;
    AccountLookup accountLookup;
    boolean failOnNotFound = true;
    List<AuthorizationProvider> authorizationProviders;

    @Override
    public void handle(ApiRequest request) throws IOException {
        if ( ApiContext.getContext().getPolicy() != null ) {
            return;
        }

        Account account = getAccount(request);
        if ( account == null ) {
            if ( failOnNotFound ) {
                throw new ClientVisibleException(ResponseCodes.UNAUTHORIZED);
            } else {
                return;
            }
        }

        Policy policy = getPolicy(account, request);
        if ( policy == null ) {
            log.error("Failed to find policy for [{}]", account.getId());
            throw new ClientVisibleException(ResponseCodes.UNAUTHORIZED);
        }

        SchemaFactory schemaFactory = getSchemaFactory(account, request);
        if ( schemaFactory == null ) {
            log.error("Failed to find a schema for account type [{}]", account.getKind());
            if ( SECURITY.get() ) {
                throw new ClientVisibleException(ResponseCodes.UNAUTHORIZED);
            }
        }

        saveInContext(request, policy, schemaFactory);
    }

    protected void throwUnauthorizated() {
        throw new ClientVisibleException(ResponseCodes.UNAUTHORIZED);
    }

    protected void saveInContext(ApiRequest request, Policy policy, SchemaFactory schemaFactory) {
        if ( schemaFactory != null ) {
            request.setSchemaFactory(schemaFactory);
        }

        ApiContext.getContext().setPolicy(policy);
    }

    protected Policy getPolicy(Account account, ApiRequest request) {
        Policy policy = null;

        for ( AuthorizationProvider auth : authorizationProviders ) {
            policy = auth.getPolicy(account, request);
            if ( policy != null ) {
                break;
            }
        }

        return policy;
    }

    protected SchemaFactory getSchemaFactory(Account account, ApiRequest request) {
        SchemaFactory factory = null;

        for ( AuthorizationProvider auth : authorizationProviders ) {
            factory = auth.getSchemaFactory(account, request);
            if ( factory != null ) {
                break;
            }
        }

        return factory;
    }

    protected Account getAccount(ApiRequest request) {
        Account account = accountLookup.getAccount(request);

        if ( account != null ) {
            return account;
        }

        if ( SECURITY.get() ) {
            if ( failOnNotFound ) {
                accountLookup.challenge(request);
            }
        } else {
            account = authDao.getAdminAccount();
        }

        return account;
    }

    public AuthDao getAuthDao() {
        return authDao;
    }

    @Inject
    public void setAuthDao(AuthDao authDao) {
        this.authDao = authDao;
    }

    public AccountLookup getAccountLookup() {
        return accountLookup;
    }

    @Inject
    public void setAccountLookup(AccountLookup accountLookup) {
        this.accountLookup = accountLookup;
    }

    public boolean isFailOnNotFound() {
        return failOnNotFound;
    }

    public void setFailOnNotFound(boolean failOnNotFound) {
        this.failOnNotFound = failOnNotFound;
    }

    public List<AuthorizationProvider> getAuthorizationProviders() {
        return authorizationProviders;
    }

    @Inject
    public void setAuthorizationProviders(List<AuthorizationProvider> authorizationProviders) {
        this.authorizationProviders = authorizationProviders;
    }

}
