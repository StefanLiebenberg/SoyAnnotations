package example.assertions;


import example.models.blog.BlogPost;
import example.models.blog.BlogPostsPage;

import java.util.List;
import java.util.Map;

import static example.assertions.BlogPostAssert.assertBlogPostEqualsJson;
import static junit.framework.Assert.*;

public class BlogPageAssertions {

    public static void assertPostsEquals(List<BlogPost> blogPosts, Object postsObject) {
        if (blogPosts != null) {
            assertTrue(postsObject instanceof List);
            assertEquals(blogPosts.size(), ((List) postsObject).size());
            for (int i = 0; i < blogPosts.size(); i++) {
                BlogPost postModel = blogPosts.get(i);
                Object postObject = ((List) postsObject).get(i);
                assertBlogPostEqualsJson(postModel, postObject);
            }
        } else {
            assertNull(postsObject);
        }
    }

    public static void assertBlogPostPageJsonEquals(BlogPostsPage pageModel, Object pageObject) {
        if (pageModel != null) {
            assertTrue(pageObject instanceof Map);
            assertEquals(pageModel.getId(), ((Map) pageObject).get("Id"));
            assertPostsEquals(pageModel.getPosts(), ((Map) pageObject).get("Posts"));
        } else {
            assertNull(pageObject);
        }
    }
}
