package slieb.soy.factories.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.inject.Inject;
import com.google.template.soy.data.SoyMapData;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.factories.SoyConverterFactory;
import slieb.soy.meta.MetaContext;
import slieb.soy.meta.MetaInformationToSoyDataConverter;

import javax.annotation.Nonnull;

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
    public Converter<Object, ? extends SoyMapData> create(@Nonnull Class<?> classObject, @Nonnull SoyDataFactoryContext context) {
        return new MetaInformationToSoyDataConverter(context).convert(metaContext.convert(classObject));
    }
}
