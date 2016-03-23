package org.slieb.soy.factories.soydata;

import com.google.inject.Inject;
import org.slieb.soy.context.SoyValueFactoryContext;
import org.slieb.soy.converters.soydata.LazyMapDataConverter;
import org.slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;

public class LazyClassToSoyMapDataConverterFactory implements SoyConverterFactory {

    private final MetaInformationSoyDataConverterFactory metaClassToSoyMapDataConverterFactory;

    @Inject
    public LazyClassToSoyMapDataConverterFactory(MetaInformationSoyDataConverterFactory classToSoyMapDataConverterFactory) {
        this.metaClassToSoyMapDataConverterFactory = classToSoyMapDataConverterFactory;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return metaClassToSoyMapDataConverterFactory.canCreate(classObject);
    }

    @Nonnull
    @Override
    public LazyMapDataConverter create(@Nonnull Class<?> classObject,
                                       @Nonnull SoyValueFactoryContext context) {
        return new LazyMapDataConverter(metaClassToSoyMapDataConverterFactory.create(classObject, context));
    }
}
