package example.assertions;

import com.google.template.soy.data.SoyData;
import example.models.blog.BlogPost;
import example.models.blog.Comment;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class BlogPostAssert {

    public static void assertCommentsEquals(List<Comment> blogPosts, Object postsObject) {
        CommentAssert.assertCommentsEquals(blogPosts, postsObject);
    }

    public static void assertBlogPostEqualsJson(BlogPost postModel, Object postObject) {
        if (postModel != null) {
            assertTrue(postObject instanceof Map);
            assertEquals(postModel.getId(), ((Map) postObject).get("Id"));
            assertEquals(postModel.getContent(), ((Map) postObject).get("Content"));
            assertCommentsEquals(postModel.getComments(), ((Map) postObject).get("Comments"));
        } else {
            assertNull(postObject);
        }
    }

    public static void assertBlogPostEqualsSoyData(BlogPost postModel, SoyData postObject) {
        throw new RuntimeException();
    }
}
