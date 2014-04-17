package slieb.soy.factories.soydata;

import com.google.inject.Inject;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.converters.soydata.LazyMapDataConverter;
import slieb.soy.factories.SoyConverterFactory;

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
    public LazyMapDataConverter create(@Nonnull Class<?> classObject, @Nonnull SoyDataFactoryContext context) {
        return new LazyMapDataConverter(metaClassToSoyMapDataConverterFactory.create(classObject, context));
    }
}
