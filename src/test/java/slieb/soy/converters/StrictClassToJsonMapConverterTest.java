package slieb.soy.converters;

import ch.lambdaj.function.convert.Converter;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import slieb.soy.converters.json.StrictClassToJsonMapConverter;

import java.util.Map;

import static com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StrictClassToJsonMapConverterTest {

    @Mock
    private Converter<Object, Object> mockNameConverter, mockEmailConverter;

    @Mock
    private Object mockUser, mockNameResult, mockEmailResult;

    private StrictClassToJsonMapConverter converter;


    @Before
    public void setUp() throws Exception {
        when(mockNameConverter.convert(mockUser)).thenReturn(mockNameResult);
        when(mockEmailConverter.convert(mockUser)).thenReturn(mockEmailResult);
        converter = new StrictClassToJsonMapConverter(
                new ImmutableMap.Builder<String, Converter<Object, ?>>()
                        .put("Name", mockNameConverter)
                        .put("Email", mockEmailConverter)
                        .build());
    }

    @Test
    public void testConvert() throws Exception {
        Map<String, Object> result = converter.convert(mockUser);
        assertEquals(newHashSet("Name", "Email"), result.keySet());
        assertEquals(mockNameResult, result.get("Name"));
        assertEquals(mockEmailResult, result.get("Email"));
    }
}
