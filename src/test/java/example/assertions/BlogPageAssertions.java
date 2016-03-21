package example.assertions;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyListData;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.data.SoyValue;
import example.models.blog.BlogPost;
import example.models.blog.BlogPostsPage;

import java.util.List;
import java.util.Map;

import static example.assertions.BlogPostAssert.assertBlogPostJsonEquals;
import static example.assertions.BlogPostAssert.assertBlogPostSoyDataEquals;
import static example.assertions.SoyDataAssertions.assertString;
import static org.junit.Assert.*;

public class BlogPageAssertions {

    public static void assertPostsJsonEquals(List<BlogPost> blogPosts,
                                             Object postsObject) {
        if (blogPosts != null) {
            assertTrue(postsObject instanceof List);
            assertEquals(blogPosts.size(), ((List) postsObject).size());
            for (int i = 0; i < blogPosts.size(); i++) {
                BlogPost postModel = blogPosts.get(i);
                Object postObject = ((List) postsObject).get(i);
                assertBlogPostJsonEquals(postModel, postObject);
            }
        } else {
            assertNull(postsObject);
        }
    }

    public static void assertPostsSoyDataEquals(List<BlogPost> blogPosts,
                                                SoyData postsObject) {
        if (blogPosts != null) {
            assertTrue(postsObject instanceof SoyListData);
            assertEquals(blogPosts.size(), ((SoyListData) postsObject).length());
            for (int i = 0; i < blogPosts.size(); i++) {
                BlogPost postModel = blogPosts.get(i);
                SoyData postObject = ((SoyListData) postsObject).get(i);
                assertBlogPostSoyDataEquals(postModel, postObject);
            }
        } else {
            assertNull(postsObject);
        }
    }

    public static void assertBlogPostPageJsonEquals(BlogPostsPage pageModel,
                                                    Object pageObject) {
        if (pageModel != null) {
            assertTrue(pageObject instanceof Map);
            assertEquals(pageModel.getId(), ((Map) pageObject).get("Id"));
            assertPostsJsonEquals(pageModel.getPosts(), ((Map) pageObject).get("Posts"));
        } else {
            assertNull(pageObject);
        }
    }

    public static void assertBlogPostPageSoyDataEquals(BlogPostsPage pageModel,
                                                       SoyValue pageObject) {
        if (pageModel != null) {
            assertTrue(pageObject instanceof SoyMapData);
            assertString(pageModel.getId(), ((SoyMapData) pageObject).get("Id"));
            assertPostsSoyDataEquals(pageModel.getPosts(), ((SoyMapData) pageObject).get("Posts"));
        } else {
            assertNull(pageObject);
        }
    }
}

