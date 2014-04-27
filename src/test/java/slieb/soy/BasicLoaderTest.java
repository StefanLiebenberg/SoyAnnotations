package slieb.soy;

import com.google.inject.ConfigurationException;
import com.google.inject.Injector;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.data.restricted.IntegerData;
import com.google.template.soy.data.restricted.StringData;
import org.junit.Before;
import org.junit.Test;
import slieb.soy.context.RendererFactoryContext;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.model.LongData;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static slieb.soy.Loader.getBasicInjector;

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
        SoyData data = context.convert(integer);
        assertTrue(data instanceof IntegerData);
        assertEquals(102, data.integerValue());
        SoyData data2 = context.convert(102);
        assertTrue(data2 instanceof IntegerData);
        assertEquals(data2, data);
    }

    @Test
    public void testBasicInjectorCanConvertLong() {
        SoyDataFactoryContext context = injector.getInstance(SoyDataFactoryContext.class);
        Long integer = new Long(102);
        SoyData data = context.convert(integer);
        assertTrue(data instanceof LongData);
    }


    @Test
    public void testBasicInjectorCanConvertSoyData() {
        SoyDataFactoryContext context = injector.getInstance(SoyDataFactoryContext.class);
        SoyData data = context.convert(1);
        assertTrue(data instanceof IntegerData);
        assertSame(data, context.convert(data));
    }

    @Test
    public void testBasicInjectorCanConvertString() {
        SoyDataFactoryContext context = injector.getInstance(SoyDataFactoryContext.class);
        SoyData data = context.convert("string");
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
        SoyData data = context.convert(map);
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
