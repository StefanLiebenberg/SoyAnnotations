package org.slieb.soy.factories.soydata;

import com.google.inject.Injector;
import example.models.*;
import org.junit.Before;
import org.junit.Test;
import org.slieb.soy.Loader;
import org.slieb.soy.context.SoyDataFactoryContext;
import org.slieb.soy.exceptions.NeedsDynamicConverterException;

public class SoyDataConverterFactoryDynamicExceptionsTest {

    private final Injector injector = Loader.getFullInjector();

    private SoyDataFactoryContext factoryContext;

    @Before
    public void setUp() throws Exception {
        factoryContext = injector.getInstance(SoyDataFactoryContext.class);
    }

    // todo, better exception class
    @Test(expected = NeedsDynamicConverterException.class)
    public void testBadDynamicFieldException() {
        factoryContext.apply(new BadDynamicFieldExample()).toString();
    }

    @Test
    public void testGoodDynamicFieldException() {
        factoryContext.apply(new GoodDynamicFieldExample()).toString();
    }

    // todo, better exception class
    @Test(expected = NeedsDynamicConverterException.class)
    public void testBadDynamicMethodException() {
        factoryContext.apply(new BadDynamicMethodExample()).toString();
    }

    @Test
    public void testGoodDynamicMethodException() {
        factoryContext.apply(new GoodDynamicMethodExample()).toString();
    }

    // todo, better exception class
    @Test(expected = NeedsDynamicConverterException.class)
    public void testBadDynamicClassException() {
        factoryContext.apply(new BadDynamicClassExample()).toString();
    }

    @Test
    public void testGoodDynamicClassException() {
        factoryContext.apply(new GoodDynamicClassExample()).toString();
    }
}
