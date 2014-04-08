package slieb.soy.factories.soydata;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import slieb.soy.converters.ClassConverter;
import slieb.soy.converters.soydata.StrictClassToSoyMapDataConverter;
import slieb.soy.exceptions.NeedsDynamicConverterException;
import slieb.soy.factories.internal.AbstractClassConverterFactory;
import slieb.soy.factories.internal.ConverterFactoryContext;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static slieb.soy.converters.soydata.NullSafeConverter.wrapConverterWithNullSafe;


public class SoyDataClassConverterFactory extends AbstractClassConverterFactory<SoyData> {

    public SoyDataClassConverterFactory(FactoryHelper factoryHelper, ConverterFactoryContext<SoyData> factoryContext) {
        super(factoryHelper, factoryContext);
    }

    @Override
    protected Converter<Object, ? extends SoyData> getFieldConverter(Field field) {
        try {
            return wrapConverterWithNullSafe(super.getFieldConverter(field));
        } catch (StackOverflowError error) {
            throw new NeedsDynamicConverterException();
        }
    }

    @Override
    protected Converter<Object, ? extends SoyData> getMethodConverter(Method method) {
        try {
            return wrapConverterWithNullSafe(super.getMethodConverter(method));
        } catch (StackOverflowError error) {
            throw new NeedsDynamicConverterException();
        }
    }

    @Nonnull
    @Override
    public Converter<Object, ? extends SoyMapData> create(@Nonnull Class<?> classObject) {
        try {
            return new ClassConverter<>(classObject, new StrictClassToSoyMapDataConverter(getMembersConverterMap(classObject)));
        } catch (StackOverflowError error) {
            throw new NeedsDynamicConverterException();
        }
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return factoryHelper.isFactoryClass(classObject);
    }
}
