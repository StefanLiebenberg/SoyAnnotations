package slieb.soy;

import com.google.template.soy.SoyFileSet;
import com.google.template.soy.tofu.SoyTofu;
import example.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import slieb.soy.annotations.SoyAnnotated;
import slieb.soy.annotations.SoyTemplate;

public class RendererFactoryTest {

    RendererFactory rendererFactory;

    SoyDataConverterFactory soyDataConverterFactory;

    @Before
    public void setUp() throws Exception {
        soyDataConverterFactory = new SoyDataConverterFactory();
        SoyFileSet.Builder soyFileSetBuilder = new SoyFileSet.Builder();
        soyFileSetBuilder.add(getClass().getResource("/templates.soy"));
        SoyFileSet soyFileSet = soyFileSetBuilder.build();
        SoyTofu tofu = soyFileSet.compileToTofu();
        rendererFactory = new RendererFactory(soyDataConverterFactory, tofu);
    }

    @After
    public void tearDown() throws Exception {
        rendererFactory = null;
    }


    @Test
    public void testBasicSoyTemplateWithFactory() throws Exception {

        @SoyTemplate("example.BasicString")
        @SoyAnnotated
        class BasicString {
        }

        Renderer renderer = rendererFactory.create(BasicString.class);
        BasicString exampleUser = new BasicString();
        String expected = "basic";
        String result = renderer.render(exampleUser);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testComplexSoyTemplateWithFactory() throws Exception {
        Renderer renderer = rendererFactory.create(User.class);
        User exampleUser = new User("1");
        exampleUser.setName("John");
        exampleUser.setEmail("john@gmail.com");

        String expected = "User 1, Name John, Email: john@gmail.com";
        String result = renderer.render(exampleUser);
        Assert.assertEquals(expected, result);
    }
}
