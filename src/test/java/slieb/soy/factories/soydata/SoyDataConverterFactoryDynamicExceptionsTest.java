package slieb.soy.factories.soydata;

import example.models.*;
import org.junit.Before;
import org.junit.Test;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.exceptions.NeedsDynamicConverterException;

import static slieb.soy.configuration.Loader.getFullSoyDataContext;

public class SoyDataConverterFactoryDynamicExceptionsTest {

    private SoyDataFactoryContext factoryContext;

    @Before
    public void setUp() throws Exception {
        factoryContext = getFullSoyDataContext();
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
