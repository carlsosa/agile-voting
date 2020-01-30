// User and password are simple not allow empty state / just one space.
// If user or password are empyu(Message: Incorrect credentials)
// If user or password are wrong (Message: Invalid User)
// Need 2 user admin with valid auth.
package org.dominisoft.scrumdev.claro2020.domain;

import org.dominisoft.scrumdev.claro2020.domain.model.User;
import org.junit.Test;

public class CredentialsAdminTest {

    @Test(expected = IllegalArgumentException.class)
    public void testUserIllegalArgumentException() {
        new User(null, null);
    }
    @Test
    public void testPswdNotWhiteSpace(){
        User user = new User("g")
    }
}