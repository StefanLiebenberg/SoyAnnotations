package slieb.soy.converters;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import example.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FieldConverterTest {


    private FieldConverter<SoyData> fieldConverter;

    @Mock
    private example.User mockUser;

    @Mock
    private Converter<Object, SoyData> mockTypeConverter;

    @Before
    public void setup() throws Exception {
        Field idField = User.class.getField("isHuman");
        fieldConverter = new FieldConverter<>(idField, mockTypeConverter);
    }

    @Test
    public void testConvert() throws Exception {
        mockUser.isHuman = false;
        SoyData mockSoyData = mock(SoyData.class);
        when(mockTypeConverter.convert(Boolean.TRUE)).thenReturn(null);
        when(mockTypeConverter.convert(Boolean.FALSE)).thenReturn(mockSoyData);
        SoyData result = fieldConverter.convert(mockUser);
        assertEquals(mockSoyData, result);
    }

}
