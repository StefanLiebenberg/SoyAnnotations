package example.assertions;


import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import example.models.blog.Votes;

import java.util.Map;

import static example.assertions.SoyDataAssertions.*;
import static org.junit.Assert.*;

public class VotesAssert {

    public static void assertVotesJsonEquals(Votes votes, Object votesObject) {
        if (votes != null) {
            assertTrue(votesObject instanceof Map);
            assertEquals(votes.getId(), ((Map) votesObject).get("Id"));
            assertEquals(votes.getDownvotes(), ((Map) votesObject).get("Downvotes"));
            assertEquals(votes.getUpvotes(), ((Map) votesObject).get("Upvotes"));
        } else {
            assertNull(votesObject);
        }
    }

    public static void assertVotesSoyDataEquals(Votes votes, SoyData votesData) {
        if (votes != null) {
            assertTrue(votesData instanceof SoyMapData);
            assertString(votes.getId(), ((SoyMapData) votesData).get("Id"));
            assertInteger(votes.getDownvotes(), ((SoyMapData) votesData).get("Downvotes"));
            assertInteger(votes.getUpvotes(), ((SoyMapData) votesData).get("Upvotes"));
        } else {
            assertNullData(votesData);
        }
    }
}
