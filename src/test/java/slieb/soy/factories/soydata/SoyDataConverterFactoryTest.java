package slieb.soy.factories.soydata;

import ch.lambdaj.function.convert.Converter;
import com.google.inject.Injector;
import example.models.User;
import org.junit.Before;
import org.junit.Test;
import slieb.soy.Loader;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.converters.soydata.*;

import static org.junit.Assert.assertTrue;


public class SoyDataConverterFactoryTest {

    private final Injector injector = Loader.getFullInjector();

    public SoyDataFactoryContext factoryContext;

    @Before
    public void setUp() {
        factoryContext = injector.getInstance(SoyDataFactoryContext.class);
    }

    @Test
    public void testCreateClassConverter() {
        Converter converter = factoryContext.create(User.class);
        assertTrue(converter instanceof ClassToSoyMapDataConverter);
    }

    @Test
    public void testCreateBooleanConverter() {
        Converter converter = factoryContext.create(Boolean.class);
        assertTrue(converter instanceof BooleanDataConverter);
    }

    @Test
    public void testCreateLongConverter() {
        Converter converter = factoryContext.create(Long.class);
        assertTrue(converter instanceof LongDataConverter);
    }

    @Test
    public void testCreateIntegerConverter() {
        Converter converter = factoryContext.create(Integer.class);
        assertTrue(converter instanceof IntegerDataConverter);
    }

    @Test
    public void testCreateFloatConverter() {
        Converter converter = factoryContext.create(Float.class);
        assertTrue(converter instanceof FloatDataConverter);
    }

    @Test
    public void testCreateStringConverter() {
        Converter converter = factoryContext.create(String.class);
        assertTrue(converter instanceof StringDataConverter);
    }
}
