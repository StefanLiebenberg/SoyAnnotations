package slieb.soy.factories.soydata;

import com.google.inject.Injector;
import example.models.*;
import org.junit.Before;
import org.junit.Test;
import slieb.soy.Loader;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.exceptions.NeedsDynamicConverterException;


public class SoyDataConverterFactoryDynamicExceptionsTest {

    private final Injector injector =Loader.getFullInjector();

    private SoyDataFactoryContext factoryContext;

    @Before
    public void setUp() throws Exception {
        factoryContext = injector.getInstance(SoyDataFactoryContext.class);
    }

    // todo, better exception class
    @Test(expected = NeedsDynamicConverterException.class)
    public void testBadDynamicFieldException() {
        factoryContext.convert(new BadDynamicFieldExample()).toString();
    }

    @Test
    public void testGoodDynamicFieldException() {
        factoryContext.convert(new GoodDynamicFieldExample()).toString();
    }

    // todo, better exception class
    @Test(expected = NeedsDynamicConverterException.class)
    public void testBadDynamicMethodException() {
        factoryContext.convert(new BadDynamicMethodExample()).toString();
    }

    @Test
    public void testGoodDynamicMethodException() {
        factoryContext.convert(new GoodDynamicMethodExample()).toString();
    }


    // todo, better exception class
    @Test(expected = NeedsDynamicConverterException.class)
    public void testBadDynamicClassException() {
        factoryContext.convert(new BadDynamicClassExample()).toString();
    }

    @Test
    public void testGoodDynamicClassException() {
        factoryContext.convert(new GoodDynamicClassExample()).toString();
    }
}
