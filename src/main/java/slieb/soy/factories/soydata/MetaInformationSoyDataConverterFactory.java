package slieb.soy.factories.soydata;

import com.google.inject.Inject;
import com.google.template.soy.data.SoyMapData;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.factories.SoyConverterFactory;
import slieb.soy.meta.MetaContext;
import slieb.soy.meta.MetaInformationToSoyDataConverter;

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
    public Function<Object, ? extends SoyMapData> create(@Nonnull Class<?> classObject,
                                                         @Nonnull SoyDataFactoryContext context) {
        final MetaInformationToSoyDataConverter metaInformationToSoyDataConverter = new MetaInformationToSoyDataConverter(context);
        return metaInformationToSoyDataConverter.apply(metaContext.apply(classObject));
    }
}
