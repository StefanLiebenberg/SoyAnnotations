package example.assertions;


import example.models.User;

import static org.junit.Assert.assertNull;


public class UserAssert {
    public static void assertUserJsonEquals(User userModel, Object userObject) {
        if (userModel != null) {
            throw new RuntimeException();
        } else {
            assertNull(userObject);
        }
    }
}
