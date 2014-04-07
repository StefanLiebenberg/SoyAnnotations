package slieb.soy.converters;


import ch.lambdaj.function.convert.Converter;
import com.google.common.collect.ImmutableMap;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StrictClassToSoyMapDataConverterTest {

    private class User {
    }

    @Mock
    private Converter<Object, ? extends SoyData> mockNameConverter;


    @Mock
    private Converter<Object, ? extends SoyData> mockEmailConverter;

    @Mock
    private User mockUser;

    @Mock
    private SoyData mockNameData;


    @Mock
    private SoyData mockEmailData;

    private StrictClassToSoyMapDataConverter converter;

    @Before
    public void setUp() throws Exception {
        when(mockNameConverter.convert(mockUser)).thenReturn(mockNameData);
        when(mockEmailConverter.convert(mockUser)).thenReturn(mockEmailData);
        ImmutableMap.Builder<String, Converter<Object, ? extends SoyData>> membersConverters = ImmutableMap.builder();
        membersConverters.put("Name", mockNameConverter);
        membersConverters.put("Email", mockEmailConverter);
        converter = new StrictClassToSoyMapDataConverter(membersConverters.build());
    }

    @Test
    public void testConvert() throws Exception {
        SoyMapData soyMapData = converter.convert(mockUser);
        assertNotNull(soyMapData);
        assertEquals(2, soyMapData.getKeys().size());
        assertEquals(mockNameData, soyMapData.getSingle("Name"));
        assertEquals(mockEmailData, soyMapData.getSingle("Email"));
    }
}
