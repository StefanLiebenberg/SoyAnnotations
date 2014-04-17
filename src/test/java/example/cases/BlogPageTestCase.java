package example.cases;


import com.google.template.soy.data.SoyData;
import example.models.blog.BlogPostsPage;
import org.junit.Test;

import static example.assertions.BlogPageAssertions.assertBlogPostPageJsonEquals;
import static example.assertions.BlogPageAssertions.assertBlogPostPageSoyDataEquals;
import static example.builders.BlogPostPageBuilder.getPage;
import static slieb.soy.configuration.Loader.getFullJsonDataContext;
import static slieb.soy.configuration.Loader.getFullSoyDataContext;

public class BlogPageTestCase {

    @Test
    public void testPageSoy() {
        BlogPostsPage page = getPage(10, 5);
        SoyData object = getFullSoyDataContext().convert(page);
        assertBlogPostPageSoyDataEquals(page, object);
    }

    @Test
    public void testPageSoyWith3_3() {
        BlogPostsPage page = getPage(3, 3);
        SoyData object = getFullSoyDataContext().convert(page);
        assertBlogPostPageSoyDataEquals(page, object);
    }

    @Test
    public void testPageJson() {
        BlogPostsPage page = getPage(3, 3);
        Object object = getFullJsonDataContext().convert(page);
        assertBlogPostPageJsonEquals(page, object);
    }

}
