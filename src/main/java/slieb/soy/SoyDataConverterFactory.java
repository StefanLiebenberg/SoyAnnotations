package slieb.soy;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import slieb.soy.annotations.SoyAnnotated;
import slieb.soy.annotations.SoyField;
import slieb.soy.annotations.SoyMethod;
import slieb.soy.internal.AbstractConverterFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class SoyDataConverterFactory extends AbstractConverterFactory<SoyData> {

    private static final Converter<Object,
            SoyData> SOY_DATA_FALLBACK_CONVERTER =
            new Converter<Object, SoyData>() {
                @Override
                public SoyData convert(Object o) {
                    return SoyData.createFromExistingData(o);
                }
            };

    private static final SoyMapDataConverter SOY_MAP_DATA_CONVERTER =
            new SoyMapDataConverter();

    @Override
    protected Converter<Object, SoyData>
    getFallbackConverter(Class classObject) {
        return SOY_DATA_FALLBACK_CONVERTER;
    }

    @Override
    protected Converter<Map<String, SoyData>, SoyData>
    getMapConverter(Class classObject) {
        return SOY_MAP_DATA_CONVERTER;
    }

    @Override
    protected boolean isFieldConvertible(Field field) {
        return field.isAnnotationPresent(SoyField.class);
    }


    @Override
    protected String getFieldKey(Field field) {
        return field.getAnnotation(SoyField.class).value();
    }

    @Override
    protected boolean isMethodConvertible(Method method) {
        return method.isAnnotationPresent(SoyMethod.class);
    }

    @Override
    protected String getMethodKey(Method method) {
        return method.getAnnotation(SoyMethod.class).value();
    }

    @Override
    protected boolean isClassConvertible(Class classObject) {
        return classObject.isAnnotationPresent(SoyAnnotated.class);
    }

}
