package slieb.soy.converters;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import example.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MethodConverterTest {

    @Mock
    private Converter<Object, SoyData> mockTypeConverter;

    @Mock
    private User mockUser;

    private MethodConverter<SoyData> methodConverter;

    @Before
    public void setup() throws Exception {
        Method getName = User.class.getMethod("getName");
        methodConverter = new MethodConverter<>(getName, mockTypeConverter);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConvertWithBadInput() throws Exception {
        methodConverter.convert("A");
    }

    @Test
    public void testConvertCallsTheMethod() throws Exception {
        String userName = "userName";
        SoyData mockSoyData = Mockito.mock(SoyData.class);
        when(mockUser.getName()).thenReturn(userName);
        when(mockTypeConverter.convert(userName)).thenReturn(mockSoyData);
        SoyData result = methodConverter.convert(mockUser);
        assertEquals(mockSoyData, result);
    }
}
