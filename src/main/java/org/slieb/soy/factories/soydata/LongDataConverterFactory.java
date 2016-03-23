package org.slieb.soy.factories.soydata;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slieb.soy.context.SoyValueFactoryContext;
import org.slieb.soy.converters.soydata.LongDataConverter;
import org.slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;

@Singleton
public class LongDataConverterFactory implements SoyConverterFactory {

    private final LongDataConverter longDataConverter;

    @Inject
    public LongDataConverterFactory(LongDataConverter longDataConverter) {
        this.longDataConverter = longDataConverter;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Long.class.equals(classObject) || classObject.equals(long.class);
    }

    @Nonnull
    @Override
    public LongDataConverter create(@Nonnull Class<?> classObject, @Nonnull SoyValueFactoryContext context) {
        return this.longDataConverter;
    }


}