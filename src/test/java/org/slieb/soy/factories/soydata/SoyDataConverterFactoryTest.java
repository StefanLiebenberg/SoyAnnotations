package org.slieb.soy.factories.soydata;

import com.google.inject.Injector;
import com.google.template.soy.data.SoyValue;
import example.models.User;
import org.junit.Before;
import org.junit.Test;
import org.slieb.soy.Loader;
import org.slieb.soy.context.SoyDataFactoryContext;
import org.slieb.soy.converters.soydata.*;

import java.util.function.Function;

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
        Function<Object, ? extends SoyValue> converter = factoryContext.create(User.class);
        assertTrue(converter instanceof ClassToSoyMapDataConverter);
    }

    @Test
    public void testCreateBooleanConverter() {
        Function<Object, ? extends SoyValue> converter = factoryContext.create(Boolean.class);
        assertTrue(converter instanceof BooleanDataConverter);
    }

    @Test
    public void testCreateLongConverter() {
        Function<Object, ? extends SoyValue> converter = factoryContext.create(Long.class);
        assertTrue(converter instanceof LongDataConverter);
    }

    @Test
    public void testCreateIntegerConverter() {
        Function<Object, ? extends SoyValue> converter = factoryContext.create(Integer.class);
        assertTrue(converter instanceof IntegerDataConverter);
    }

    @Test
    public void testCreateFloatConverter() {
        Function<Object, ? extends SoyValue> converter = factoryContext.create(Float.class);
        assertTrue(converter instanceof FloatDataConverter);
    }

    @Test
    public void testCreateStringConverter() {
        Function<Object, ? extends SoyValue> converter = factoryContext.create(String.class);
        assertTrue(converter instanceof StringDataConverter);
    }
}
