package slieb.soy.factories.jsondata;

import ch.lambdaj.function.convert.Converter;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import slieb.soy.context.JsonDataFactoryContext;
import slieb.soy.factories.JsonConverterFactory;
import slieb.soy.meta.MetaContext;

import javax.annotation.Nonnull;

@Singleton
public class MetaInformationToJsonDataConverterFactory implements JsonConverterFactory {

    private MetaContext metaContext;

    @Inject
    public MetaInformationToJsonDataConverterFactory(MetaContext metaContext) {
        this.metaContext = metaContext;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return metaContext.hasFactory(classObject);
    }

    @Nonnull
    @Override
    public Converter<Object, ?> create(@Nonnull Class<?> classObject, @Nonnull JsonDataFactoryContext context) {
        return new MetaInformationToJsonDataConverterConverter(context).convert(metaContext.convert(classObject));
    }
}
