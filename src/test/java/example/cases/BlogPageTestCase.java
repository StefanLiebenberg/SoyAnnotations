package example.cases;


import com.google.inject.Injector;
import example.models.blog.BlogPostsPage;
import org.junit.Before;
import org.junit.Test;
import org.slieb.soy.Loader;
import org.slieb.soy.context.JsonDataFactoryContext;
import org.slieb.soy.context.SoyDataFactoryContext;

import static example.assertions.BlogPageAssertions.assertBlogPostPageJsonEquals;
import static example.assertions.BlogPageAssertions.assertBlogPostPageSoyDataEquals;
import static example.builders.BlogPostPageBuilder.getPage;

public class BlogPageTestCase {

    private Injector injector = Loader.getFullInjector();

    private BlogPostsPage page;

    @Before
    public void setup() {
        page = getPage(10, 5);

    }

    @Test
    public void testPageSoy() {
        SoyDataFactoryContext context = injector.getInstance(SoyDataFactoryContext.class);
        assertBlogPostPageSoyDataEquals(page, context.getSoyData(page));
    }

    @Test
    public void testPageJson() {
        JsonDataFactoryContext context = injector.getInstance(JsonDataFactoryContext.class);
        // todo, add getJson and getJsonMap to context as methods.
        assertBlogPostPageJsonEquals(page, context.apply(page));
    }

}
