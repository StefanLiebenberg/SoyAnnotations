package slieb.soy.factories.soydata;

import example.models.User;
import org.junit.Before;
import org.junit.Test;
import slieb.soy.converters.ClassConverter;
import slieb.soy.converters.soydata.LongConverter;
import slieb.soy.converters.ObjectConverter;

import static org.junit.Assert.assertTrue;

public class SoyDataConverterFactoryTest {

    public SoyDataConverterFactoryContext factoryContext;

    @Before
    public void setUp() {
        factoryContext = new SoyDataConverterFactoryContext();
    }

    @Test
    public void testCreateClassConverter() {
        assertTrue(factoryContext.create(User.class) instanceof ClassConverter);
    }

    @Test
    public void testCreateBooleanConverter() {
        assertTrue(factoryContext.create(Boolean.class) instanceof ObjectConverter);
    }

    @Test
    public void testCreateLongConverter() {
        assertTrue(factoryContext.create(Long.class) instanceof LongConverter);
    }

    @Test
    public void testCreateNumberConverter() {
        assertTrue(factoryContext.create(Integer.class) instanceof ObjectConverter);
    }

    @Test
    public void testCreateStringConverter() {
        assertTrue(factoryContext.create(String.class) instanceof ObjectConverter);
    }
}
