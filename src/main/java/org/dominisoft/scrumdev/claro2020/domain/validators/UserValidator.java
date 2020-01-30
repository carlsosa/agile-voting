package org.dominisoft.scrumdev.claro2020.domain.validators;

import org.dominisoft.scrumdev.claro2020.domain.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserValidator {
    private static Map<String, String> validAdminUsers;

    static {
        validAdminUsers = new HashMap<>();
        validAdminUsers.put("admin","admin");
        validAdminUsers.put("super","intro");

    }

    public static boolean isAdmin(User user){
       String pass =validAdminUsers.get(user.getUsername());

       return pass != null && pass.equals(user.getPassword());
    }
}
