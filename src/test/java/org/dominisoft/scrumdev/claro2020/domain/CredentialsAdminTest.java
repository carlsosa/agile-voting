// User and password are simple not allow empty state / just one space.
// If user or password are empyu(Message: Incorrect credentials)
// If user or password are wrong (Message: Invalid User)
// Need 2 user admin with valid auth.
package org.dominisoft.scrumdev.claro2020.domain;

import org.dominisoft.scrumdev.claro2020.domain.model.User;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

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
}