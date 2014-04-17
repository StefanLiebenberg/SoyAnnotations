package slieb.soy.factories.soydata;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.converters.soydata.LongDataConverter;
import slieb.soy.factories.SoyConverterFactory;

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
    public LongDataConverter create(@Nonnull Class<?> classObject, @Nonnull SoyDataFactoryContext context) {
        return this.longDataConverter;
    }


}