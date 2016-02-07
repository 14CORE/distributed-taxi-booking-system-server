package com.robertnorthard.dtbs.server.layer.service;

import com.robertnorthard.dtbs.server.common.exceptions.AccountAlreadyExistsException;
import com.robertnorthard.dtbs.server.common.exceptions.AccountAuthenticationFailed;
import com.robertnorthard.dtbs.server.common.exceptions.AccountInvalidException;
import com.robertnorthard.dtbs.server.common.exceptions.EntityNotFoundException;
import com.robertnorthard.dtbs.server.layer.model.Account;

/**
 * An interface for defining and enforcing operations needed for 
 * the Account Service class.  It provides the scope of possible 
 * database requests made through the AccountDAO.
 * @author robertnorthard
 */
public interface AccountFacade {
    
    /**
     * Register a new account. 
     * Two user's cannot have the same username.
     * 
     * @param acct account to create.
     * @throws AccountAlreadyExistsException if account already exists.
     * @throws AccountInvalidException if email invalid.
     */
    public void registerAccount(Account acct) 
            throws AccountAlreadyExistsException, AccountInvalidException;
    
    /**
     * Authenticate a user.
     * @param username username of account
     * @param password password of account
     * @return account object if authentication successful else null.
     * @throws AccountAuthenticationFailed if authentication fails.
     */
    public Account authenticate(String username, String password) 
            throws AccountAuthenticationFailed;
    
    /**
     * Authenticate user from base64 encoded message.
     * @param base64Credentials base64 encoded credentials
     * @return account object if authentication successful else null.
     * @throws AccountAuthenticationFailed if authentication fails.
     */
    public Account authenticate(final String base64Credentials) 
            throws AccountAuthenticationFailed;
    
     /**
     * Return account with corresponding username.
     * @param username username
     * @return an account with the corresponding username. 
     * If the account does not exist
     * return null.
     */
    public Account findAccount(final String username);   
    
    /**
     * Reset account password.
     * @param username username of account to reset.
     * @throws AccountInvalidException account not found
     */
    public void resetPassword(final String username)
            throws AccountInvalidException;
    
    /**
     * 
     * @param code temporary authentication code.
     * @param username username to authenticate with.
     * @param newPassword new password for user.
     * @throws AccountAuthenticationFailed if username and code do not match a valid, active password reset event.
     * @throws EntityNotFoundException account not found but password resets exist. Indicates data integrity issues.
     */
    public void resetPassword(final String code, final String username, final String newPassword) 
            throws AccountAuthenticationFailed, EntityNotFoundException;
}
