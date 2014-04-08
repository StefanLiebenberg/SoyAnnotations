package example.assertions;

import example.models.blog.Comment;

import java.util.List;
import java.util.Map;

import static example.assertions.UserAssert.assertUserJsonEquals;
import static org.junit.Assert.*;


public class CommentAssert {

    public static void assertCommentEquals(Comment commentModel, Object commentObject) {
        if (commentModel != null) {
            assertTrue(commentObject instanceof Map);
            assertEquals(commentModel.getId(), ((Map) commentObject).get("Id"));
            assertUserJsonEquals(commentModel.getUser(), ((Map) commentObject).get("User"));
            assertCommentsEquals(commentModel.getComments(), ((Map) commentObject).get("Comments"));
        } else {
            assertNull(commentObject);
        }
    }

    public static void assertCommentsEquals(List<Comment> commentsModel, Object commentsObject) {
        if (commentsModel != null) {
            assertTrue(commentsObject instanceof List);
            assertEquals(commentsModel.size(), ((List) commentsObject).size());
            for (int i = 0; i < commentsModel.size(); i++) {
                Comment commentModel = commentsModel.get(i);
                Object commentObject = ((List) commentsObject).get(i);
                assertCommentEquals(commentModel, commentObject);
            }
        } else {
            assertNull(commentsObject);
        }
    }
}
