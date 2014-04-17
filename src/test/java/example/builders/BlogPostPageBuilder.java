package example.builders;


import example.models.User;
import example.models.blog.BlogPost;
import example.models.blog.BlogPostsPage;
import example.models.blog.Comment;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;

public class BlogPostPageBuilder {

    public static Integer currentId = 0;

    public static String getId(String prefix) {
        return format("%s-%s", prefix, currentId++);
    }

    public static List<Comment> getComments(Integer depthOfComments) {
        return newArrayList(getComment(depthOfComments), getComment(depthOfComments));
    }

    public static Comment getComment(Integer depthOfComments) {
        return new Comment(getId("Comment"), getUser(), null,
                depthOfComments > 0 ? getComments(depthOfComments - 1) : null, null);
    }

    public static User getUser() {
        return new User(getId("User"));
    }

    public static BlogPost getPost(Integer depthOfComments) {
        return new BlogPost(getId("Post"), "Content", getComments(depthOfComments));
    }

    public static List<BlogPost> getPosts(Integer number, Integer depthOfComments) {
        List<BlogPost> posts = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            posts.add(getPost(depthOfComments));
        }
        return posts;
    }

    public static BlogPostsPage getPage(Integer numberOfPosts, Integer depthOfComments) {
        return new BlogPostsPage(getId("BlogPost"), getPosts(numberOfPosts, depthOfComments));
    }

}
