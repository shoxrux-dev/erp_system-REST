package com.system.erp_system.util;

import com.system.erp_system.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityUtils {
    public static String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof UsernamePasswordAuthenticationToken token) {
            if(token.getPrincipal() instanceof UserDetails userDetails){
                return userDetails.getUsername();
            }
        }
        return null;
    }

    public static User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof UsernamePasswordAuthenticationToken token) {
            if(token.getPrincipal() instanceof User user){
                return user;
            }
        }
        return null;
    }

}
