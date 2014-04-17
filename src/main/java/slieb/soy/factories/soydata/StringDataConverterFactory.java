package slieb.soy.factories.soydata;


import com.google.inject.Singleton;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.converters.soydata.StringDataConverter;
import slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;
import javax.inject.Inject;

@Singleton
public class StringDataConverterFactory implements SoyConverterFactory {

    private final StringDataConverter stringDataConverter;

    @Inject
    public StringDataConverterFactory(StringDataConverter stringDataConverter) {
        this.stringDataConverter = stringDataConverter;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return String.class.equals(classObject);
    }

    @Nonnull
    @Override
    public StringDataConverter create(@Nonnull Class<?> classObject,
                                      @Nonnull SoyDataFactoryContext context) {
        return stringDataConverter;
    }


}
