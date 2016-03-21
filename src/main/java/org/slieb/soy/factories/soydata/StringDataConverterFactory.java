package org.slieb.soy.factories.soydata;


import com.google.inject.Singleton;
import org.slieb.soy.context.SoyDataFactoryContext;
import org.slieb.soy.converters.soydata.StringDataConverter;
import org.slieb.soy.factories.SoyConverterFactory;

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
        // todo, make separate factory class for enums?
        return String.class.equals(classObject) || classObject.isEnum();
    }

    @Nonnull
    @Override
    public StringDataConverter create(@Nonnull Class<?> classObject,
                                      @Nonnull SoyDataFactoryContext context) {
        return stringDataConverter;
    }


}
