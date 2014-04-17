package example.assertions;


import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import example.models.User;

import java.util.Map;

import static example.assertions.SoyDataAssertions.*;
import static org.junit.Assert.*;


public final class UserAssert {

    public static void assertUserJsonEquals(User userModel, Object userObject) {
        if (userModel != null) {
            assertTrue(userObject instanceof Map);
            assertEquals(userModel.id, ((Map) userObject).get("Id"));
            assertEquals(userModel.getEmail(), ((Map) userObject).get("Email"));
            assertEquals(userModel.getName(), ((Map) userObject).get("Name"));
            assertEquals(userModel.isHuman, ((Map) userObject).get("IsHuman"));
        } else {
            assertNull(userObject);
        }
    }

    public static void assertUserSoyDataEquals(User userModel, SoyData userObject) {
        if (userModel != null) {
            assertTrue(userObject instanceof SoyMapData);
            assertString(userModel.id, ((SoyMapData) userObject).get("Id"));
            assertString(userModel.getEmail(), ((SoyMapData) userObject).get("Email"));
            assertString(userModel.getName(), ((SoyMapData) userObject).get("Name"));
            assertBoolean(userModel.isHuman, ((SoyMapData) userObject).get("IsHuman"));
        } else {
            assertNullData(userObject);
        }
    }
}
