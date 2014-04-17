package example.cases;


import com.google.template.soy.data.SoyData;
import example.models.blog.BlogPostsPage;
import org.junit.Before;
import org.junit.Test;
import slieb.soy.Context;

import static example.assertions.BlogPageAssertions.assertBlogPostPageJsonEquals;
import static example.assertions.BlogPageAssertions.assertBlogPostPageSoyDataEquals;
import static example.builders.BlogPostPageBuilder.getPage;
import static slieb.soy.Loader.getContext;

public class BlogPageTestCase {

    private Context context;

    @Before
    public void setup() {
        context = getContext();
    }

    @Test
    public void testPageSoy() {
        BlogPostsPage page = getPage(10, 5);
        SoyData object = context.getSoyData(page);
        assertBlogPostPageSoyDataEquals(page, object);
    }

    @Test
    public void testPageSoyWith3_3() {
        BlogPostsPage page = getPage(3, 3);
        SoyData object = context.getSoyData(page);
        assertBlogPostPageSoyDataEquals(page, object);
    }

    @Test
    public void testPageJson() {
        BlogPostsPage page = getPage(3, 3);
        Object object = context.getJsonData(page);
        assertBlogPostPageJsonEquals(page, object);
    }

}
