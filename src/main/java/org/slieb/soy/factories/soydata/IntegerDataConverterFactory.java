package org.slieb.soy.factories.soydata;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slieb.soy.context.SoyValueFactoryContext;
import org.slieb.soy.converters.soydata.IntegerDataConverter;
import org.slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;

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
    public IntegerDataConverter create(@Nonnull Class<?> classObject,
                                       @Nonnull SoyValueFactoryContext context) {
        return integerDataConverter;
    }
}
