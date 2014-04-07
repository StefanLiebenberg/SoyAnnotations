package slieb.soy.factories;

import example.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import slieb.soy.converters.ClassConverter;
import slieb.soy.converters.LongConverter;
import slieb.soy.converters.ObjectConverter;
import slieb.soy.factories.soydata.SoyDataConverterFactoryContext;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
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
