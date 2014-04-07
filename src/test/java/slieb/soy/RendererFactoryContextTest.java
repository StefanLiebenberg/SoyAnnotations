package slieb.soy;

import com.google.template.soy.SoyFileSet;
import example.User;
import org.junit.Before;
import org.junit.Test;
import slieb.soy.annotations.Soy;
import slieb.soy.factories.rendering.Renderer;
import slieb.soy.factories.rendering.RendererFactoryContext;

import static org.junit.Assert.assertEquals;

public class RendererFactoryContextTest {

    RendererFactoryContext rendererFactoryContext;

    @Before
    public void setUp() throws Exception {
        rendererFactoryContext =
                new RendererFactoryContext(
                        new SoyFileSet.Builder()
                                .add(getClass().getResource("/templates.soy"))
                                .build().compileToTofu());
    }


    @Test
    public void testBasicSoyTemplateWithFactory() throws Exception {

        @Soy.Template("example.BasicString")
        @Soy
        class BasicString {
        }

        Renderer<Object> renderer = rendererFactoryContext.create(BasicString.class);
        BasicString exampleUser = new BasicString();
        String expected = "basic";
        String result = renderer.render(exampleUser);
        assertEquals(expected, result);
    }

    @Test
    public void testComplexSoyTemplateWithFactory() throws Exception {
        Renderer<Object> renderer = rendererFactoryContext.create(User.class);
        User exampleUser = new User("1");
        exampleUser.setName("John");
        exampleUser.setEmail("john@gmail.com");

        String expected = "User 1, Name John, Email: john@gmail.com";
        String result = renderer.render(exampleUser);
        assertEquals(expected, result);
    }
}
