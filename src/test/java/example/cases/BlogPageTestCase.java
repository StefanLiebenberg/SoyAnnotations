package example.cases;


import example.assertions.BlogPageAssertions;
import example.models.blog.BlogPost;
import example.models.blog.BlogPostsPage;
import example.models.blog.Comment;
import org.junit.Test;
import slieb.soy.factories.json.JsonConverterFactoryContext;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;

public class BlogPageTestCase {

    private Integer currentId = 0;

    private String getId(String prefix) {
        return format("%s-%s", prefix, currentId++);
    }

    private List<Comment> getComments(Integer depthOfComments) {
        return newArrayList(getComment(depthOfComments), getComment(depthOfComments));
    }

    private Comment getComment(Integer depthOfComments) {
        return new Comment(getId("Comment"), null, null,
                depthOfComments > 0 ? getComments(depthOfComments - 1) : null, null);
    }

    private BlogPost getPost(Integer depthOfComments) {
        return new BlogPost(getId("Post"), null, getComments(depthOfComments));
    }

    private List<BlogPost> getPosts(Integer number, Integer depthOfComments) {
        List<BlogPost> posts = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            posts.add(getPost(depthOfComments));
        }
        return posts;
    }

    private BlogPostsPage getPage(Integer numberOfPosts, Integer depthOfComments) {
        return new BlogPostsPage(getId("BlogPost"), getPosts(numberOfPosts, depthOfComments));
    }


    @Test
    public void testPage() {
        BlogPostsPage page = getPage(10, 10);
        JsonConverterFactoryContext jsonContext = new JsonConverterFactoryContext();
        Object object = jsonContext.convert(page);
        BlogPageAssertions.assertBlogPostPageJsonEquals(page, object);
    }
}
