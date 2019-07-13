package ro.utcluj.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextUsernameGet {

    public String getUserNameFromContext(){
        UserDetails userDetails = (UserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return userDetails.getUsername();    }
}
