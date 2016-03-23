package org.slieb.soy.factories.soydata;

import com.google.inject.Inject;
import com.google.template.soy.data.SoyMap;
import org.slieb.soy.context.SoyValueFactoryContext;
import org.slieb.soy.factories.SoyConverterFactory;
import org.slieb.soy.meta.MetaContext;
import org.slieb.soy.meta.MetaInformationToSoyDataConverter;

import javax.annotation.Nonnull;
import java.util.function.Function;

public class MetaInformationSoyDataConverterFactory implements SoyConverterFactory {

    private final MetaContext metaContext;

    @Inject
    public MetaInformationSoyDataConverterFactory(MetaContext metaContext) {
        this.metaContext = metaContext;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return metaContext.hasFactory(classObject);
    }

    @Nonnull
    @Override
    public Function<Object, SoyMap> create(@Nonnull Class<?> classObject,
                                                     @Nonnull SoyValueFactoryContext context) {
        final MetaInformationToSoyDataConverter metaInformationToSoyDataConverter = new MetaInformationToSoyDataConverter(context);
        return metaInformationToSoyDataConverter.apply(metaContext.apply(classObject));
    }
}
