package slieb.soy.converters;

import com.google.template.soy.data.SoyMapData;
import example.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import slieb.soy.converters.soydata.SoyDataMapConverter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClassConverterTest {

    @Mock
    private User mockUser;

    @Mock
    private SoyMapData mockMapData;

    @Mock
    private SoyDataMapConverter mockSoyDataMapConverter;

    private ClassConverter classConverter;

    @Before
    public void setup() {
        when(mockSoyDataMapConverter.convert(mockUser)).thenReturn(mockMapData);
        classConverter = new ClassConverter<>(User.class, mockSoyDataMapConverter);
    }

    @Test
    public void testConvert() throws Exception {
        assertEquals(mockMapData, classConverter.convert(mockUser));
    }

    @Test
    public void testConvertNullValue() throws Exception {
        assertNull(classConverter.convert(null));
    }

}
