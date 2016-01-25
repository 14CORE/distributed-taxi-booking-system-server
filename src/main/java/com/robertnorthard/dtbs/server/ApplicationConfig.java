package com.robertnorthard.dtbs.server;

import com.robertnorthard.dtbs.server.layer.controllers.AccountController;
import com.robertnorthard.dtbs.server.layer.controllers.LoginController;
import com.robertnorthard.dtbs.server.layer.security.AuthenticationFilter;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 * Application configuration.
 * @author robertnorthard
 **/
@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig(){
        
        // RESTful Controllers
        register(AccountController.class);
        register(LoginController.class);
        
        // Filters
        register(RolesAllowedDynamicFeature.class);
        register(AuthenticationFilter.class);
    }

}
 