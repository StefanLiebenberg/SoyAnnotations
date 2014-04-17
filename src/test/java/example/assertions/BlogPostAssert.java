package example.assertions;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import example.models.blog.BlogPost;

import java.util.Map;

import static example.assertions.CommentAssert.assertCommentsEqualsJson;
import static example.assertions.CommentAssert.assertCommentsSoyDataEquals;
import static example.assertions.SoyDataAssertions.assertNullData;
import static example.assertions.SoyDataAssertions.assertString;
import static org.junit.Assert.*;


public class BlogPostAssert {

    public static void assertBlogPostJsonEquals(BlogPost postModel, Object postObject) {
        if (postModel != null) {
            assertTrue(postObject instanceof Map);
            assertEquals(postModel.getId(), ((Map) postObject).get("Id"));
            assertEquals(postModel.getContent(), ((Map) postObject).get("Content"));
            assertCommentsEqualsJson(postModel.getComments(), ((Map) postObject).get("Comments"));
        } else {
            assertNull(postObject);
        }
    }

    public static void assertBlogPostSoyDataEquals(BlogPost postModel, SoyData postObject) {
        if (postModel != null) {
            assertTrue(postObject instanceof SoyMapData);
            assertString(postModel.getId(), ((SoyMapData) postObject).get("Id"));
            assertString(postModel.getContent(), ((SoyMapData) postObject).get("Content"));
            assertCommentsSoyDataEquals(postModel.getComments(), ((SoyMapData) postObject).get("Comments"));
        } else {
            assertNullData(postObject);
        }
    }
}
