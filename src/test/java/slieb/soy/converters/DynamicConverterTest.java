package slieb.soy.converters;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import slieb.soy.factories.internal.ConverterFactoryContext;
import slieb.soy.factories.internal.Factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DynamicConverterTest {

    public class User {
    }

    @Mock
    private User mockUser;

    @Mock
    private Converter<Object, ? extends SoyData> mockUserConverter;

    @Mock
    private SoyMapData mockSoyMapData;

    @Mock
    private ConverterFactoryContext<SoyData> mockConverterFactoryContext;

    @Mock
    private Factory<Converter<Object, ? extends SoyData>> mockConverterFactory;

    private DynamicConverter converter;

    @Before
    public void setUp() throws Exception {
        when(mockConverterFactoryContext.convert(mockUser)).thenReturn(mockSoyMapData);
        converter = new DynamicConverter<>(mockConverterFactoryContext);
    }

    @Test
    public void testConvert() throws Exception {
        assertEquals(mockSoyMapData, converter.convert(mockUser));
        assertNull(converter.convert(null));
    }
}
