package com.robertnorthard.dtbs.server.layer.service.business.rules.account;

import com.robertnorthard.dtbs.server.layer.service.business.rules.CompositeSpecification;
import com.robertnorthard.dtbs.server.layer.service.business.rules.validator.ValidatorResult;
import com.robertnorthard.dtbs.server.layer.service.entities.Account;

/**
 * Password constraint specification.
 * 
 * @author robertnorthard
 */
public class PasswordConstrantSpecification extends CompositeSpecification<Account> {

    public PasswordConstrantSpecification(ValidatorResult result){
        super(result);
    }
    
    @Override
    public boolean isSatisfiedBy(Account candidate) {
        if (candidate.getPassword().length() < 6){
            this.getErrorResult().addError("Password must be more than 6 characters.");
            return false;
        }else{
            return true;
        }
    }    
}
