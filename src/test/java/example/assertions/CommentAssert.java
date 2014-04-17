package example.assertions;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyListData;
import com.google.template.soy.data.SoyMapData;
import example.models.blog.Comment;

import java.util.List;
import java.util.Map;

import static example.assertions.SoyDataAssertions.*;
import static example.assertions.UserAssert.assertUserJsonEquals;
import static example.assertions.UserAssert.assertUserSoyDataEquals;
import static example.assertions.VotesAssert.assertVotesJsonEquals;
import static example.assertions.VotesAssert.assertVotesSoyDataEquals;
import static org.junit.Assert.*;


public class CommentAssert {

    public static void assertCommentEqualsJson(Comment commentModel, Object commentObject) {
        if (commentModel != null) {
            assertTrue(commentObject instanceof Map);
            assertEquals(commentModel.getId(), ((Map) commentObject).get("Id"));
            assertUserJsonEquals(commentModel.getUser(), ((Map) commentObject).get("User"));
            assertCommentsEqualsJson(commentModel.getComments(), ((Map) commentObject).get("Comments"));
            assertVotesJsonEquals(commentModel.getVotes(), ((Map) commentObject).get("Votes"));
        } else {
            assertNull(commentObject);
        }
    }

    public static void assertCommentsEqualsJson(List<Comment> commentsModel, Object commentsObject) {
        if (commentsModel != null) {
            assertTrue(commentsObject instanceof List);
            assertEquals(commentsModel.size(), ((List) commentsObject).size());
            for (int i = 0; i < commentsModel.size(); i++) {
                Comment commentModel = commentsModel.get(i);
                Object commentObject = ((List) commentsObject).get(i);
                assertCommentEqualsJson(commentModel, commentObject);
            }
        } else {
            assertNull(commentsObject);
        }
    }

    public static void assertCommentsSoyDataEquals(List<Comment> comments, SoyData commentsObject) {
        if (comments != null) {
            assertTrue(commentsObject instanceof SoyListData);
            assertEquals(comments.size(), ((SoyListData) commentsObject).length());
            for (int i = 0; i < comments.size(); i++) {
                assertCommentSoyDataEquals(comments.get(i), ((SoyListData) commentsObject).get(i));
            }
        } else {
            assertNullData(commentsObject);
        }
    }

    public static void assertCommentSoyDataEquals(Comment commentModel, SoyData commentObject) {
        if (commentModel != null) {
            assertTrue(commentObject instanceof SoyMapData);
            assertString(commentModel.getId(), ((SoyMapData) commentObject).get("Id"));
            assertLong(commentModel.getTimestamp(), ((SoyMapData) commentObject).get("Timestamp"));
            assertUserSoyDataEquals(commentModel.getUser(), ((SoyMapData) commentObject).get("User"));
            assertCommentsSoyDataEquals(commentModel.getComments(), ((SoyMapData) commentObject).get("Comments"));
            assertVotesSoyDataEquals(commentModel.getVotes(), ((SoyMapData) commentObject).get("Votes"));
        } else {
            assertNullData(commentObject);
        }
    }
}
