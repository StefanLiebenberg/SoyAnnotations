package slieb.soy.factories.rendering;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import slieb.soy.annotations.CustomRenderer;
import slieb.soy.annotations.Soy;
import slieb.soy.context.RendererFactoryContext;

import javax.annotation.Nullable;

import static java.lang.String.format;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomRendererFactoryTest {

    public static class ParrotRenderer implements Renderer<Object> {

        public ParrotRenderer() {}

        private final String FORMAT = "parrot say %s";

        @Nullable
        @Override
        public String render(@Nullable Object instanceObject) {
            return format(FORMAT, instanceObject);
        }
    }

    @Soy
    @CustomRenderer(ParrotRenderer.class)
    public class Parrot {
        @Override
        public String toString() {
            return "whoo";
        }
    }

    public class Dog {
        @Override
        public String toString() {
            return "woof";
        }
    }

    @Mock
    private RendererFactoryContext mockRendererFactoryContext;

    private CustomRendererFactory customRendererFactory;


    @Before
    public void setUp() {
        customRendererFactory = new CustomRendererFactory();
    }

    @Test
    public void testRenderParrot() {
        assertTrue(customRendererFactory.canCreate(Parrot.class));
        assertFalse(customRendererFactory.canCreate(Dog.class));
        assertEquals("parrot say whoo", customRendererFactory.create(Parrot.class, mockRendererFactoryContext).render(new Parrot()));
    }

}
