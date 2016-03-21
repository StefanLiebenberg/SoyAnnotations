package org.slieb.soy;

import com.google.inject.ConfigurationException;
import com.google.inject.Injector;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.data.SoyValue;
import com.google.template.soy.data.restricted.IntegerData;
import com.google.template.soy.data.restricted.StringData;
import org.junit.Before;
import org.junit.Test;
import org.slieb.soy.context.RendererFactoryContext;
import org.slieb.soy.context.SoyDataFactoryContext;
import org.slieb.soy.model.LongData;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.slieb.soy.Loader.getBasicInjector;

public class BasicLoaderTest {

    private Injector injector;

    @Before
    public void setup() {
        injector = getBasicInjector();
    }

    @Test
    public void testBasicInjectorMakesSingletonContext() throws Exception {
        assertSame("Singleton instance expected",
                   injector.getInstance(SoyDataFactoryContext.class),
                   injector.getInstance(SoyDataFactoryContext.class));
    }

    @Test(expected = ConfigurationException.class)
    public void testBasicInjectorCannotDeliverRenderer() {
        injector.getInstance(RendererFactoryContext.class);
    }

    @Test
    public void testBasicInjectorCanConvertInteger() {
        SoyDataFactoryContext context = injector.getInstance(SoyDataFactoryContext.class);
        Integer integer = new Integer(102);
        SoyValue data = context.apply(integer);
        assertTrue(data instanceof IntegerData);
        assertEquals(102, data.integerValue());
        SoyValue data2 = context.apply(102);
        assertTrue(data2 instanceof IntegerData);
        assertEquals(data2, data);
    }

    @Test
    public void testBasicInjectorCanConvertLong() {
        SoyDataFactoryContext context = injector.getInstance(SoyDataFactoryContext.class);
        Long integer = new Long(102);
        SoyValue data = context.apply(integer);
        assertTrue(data instanceof LongData);
    }

    @Test
    public void testBasicInjectorCanConvertSoyData() {
        SoyDataFactoryContext context = injector.getInstance(SoyDataFactoryContext.class);
        SoyValue data = context.apply(1);
        assertTrue(data instanceof IntegerData);
        assertSame(data, context.apply(data));
    }

    @Test
    public void testBasicInjectorCanConvertString() {
        SoyDataFactoryContext context = injector.getInstance(SoyDataFactoryContext.class);
        SoyValue data = context.apply("string");
        assertTrue(data instanceof StringData);
        assertEquals("string", data.stringValue());
    }

    @Test
    public void testBasicInjectorCanConvertMap() {
        SoyDataFactoryContext context = injector.getInstance(SoyDataFactoryContext.class);

        Map<String, Object> map = new HashMap<>();
        map.put("strEntry", "string");
        map.put("intEntry", 10);
        map.put("longEntry", 10L);
        SoyValue data = context.apply(map);
        assertTrue(data instanceof SoyMapData);
        SoyData strEntryData = ((SoyMapData) data).get("strEntry");
        assertTrue(strEntryData instanceof StringData);
        assertEquals("string", strEntryData.stringValue());
        SoyData intEntryData = ((SoyMapData) data).get("intEntry");
        assertTrue(intEntryData instanceof IntegerData);
        assertEquals(10, intEntryData.integerValue());
        SoyData longEntryData = ((SoyMapData) data).get("longEntry");
        assertTrue(longEntryData instanceof LongData);
    }
}
