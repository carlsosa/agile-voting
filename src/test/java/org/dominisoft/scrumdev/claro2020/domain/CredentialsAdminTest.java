// + User and password are simple not allow empty state / just one space.
// + If user or password are empty(Message: Incorrect credentials)
// If user or password are wrong (Message: Invalid User)
// Need 2 user admin with valid auth.
package org.dominisoft.scrumdev.claro2020.domain;

import static org.junit.Assert.assertFalse;

import org.dominisoft.scrumdev.claro2020.domain.model.User;
import org.dominisoft.scrumdev.claro2020.domain.validators.UserValidator;
import org.junit.Test;

public class CredentialsAdminTest {

  public static String defaultPassword = "P@SSword";
  public static String defaultUsername = "Anonymous";

  @Test(expected = IllegalArgumentException.class)
  public void user_not_null_values_IllegalArgumentException() {
    new User(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void password_not_allow_only_whitespace() {
    new User("   ", defaultUsername);
  }

  @Test(expected = IllegalArgumentException.class)
  public void username_not_allow_only_whitespace() {
    new User(defaultPassword, "    ");
  }

  @Test
  public void admin_user_not_valid() {
    assertFalse(UserValidator.isAdmin(new User(defaultPassword, defaultUsername)));
  }
}
