package pl.coderslab.charity.app;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class SecurityUtils {

    public static String username(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static String uuidToken(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
