package org.dominisoft.scrumdev.claro2020.domain.validators;

import java.util.HashMap;
import java.util.Map;

import org.dominisoft.scrumdev.claro2020.domain.model.User;

/**
 * sadfsadfsa.
 * @author scrumdev
 *
 */
public class UserValidator {
  private static Map<String, String> validAdminUsers;

  static {
    validAdminUsers = new HashMap<>();
    validAdminUsers.put("admin", "admin");
    validAdminUsers.put("super", "intro");

  }

  /**
   * sadfsadf.
   */
  public static boolean isAdmin(User user) {
    final String pass = validAdminUsers.get(user.getUsername());

    return pass != null && pass.equals(user.getPassword());
  }
}
