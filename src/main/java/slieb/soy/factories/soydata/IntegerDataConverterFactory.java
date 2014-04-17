package slieb.soy.factories.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.restricted.IntegerData;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.converters.soydata.IntegerDataConverter;
import slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Singleton
public class IntegerDataConverterFactory implements SoyConverterFactory {

    private final IntegerDataConverter integerDataConverter;

    @Inject
    public IntegerDataConverterFactory(IntegerDataConverter integerDataConverter) {
        this.integerDataConverter = integerDataConverter;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Integer.class.equals(classObject) || classObject.equals(int.class);
    }


    @Nonnull
    @Override
    public IntegerDataConverter create(@Nonnull Class<?> classObject, @Nonnull SoyDataFactoryContext context) {
        return integerDataConverter;
    }
}
