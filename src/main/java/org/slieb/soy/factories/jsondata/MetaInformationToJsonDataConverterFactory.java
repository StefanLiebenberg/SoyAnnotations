package org.slieb.soy.factories.jsondata;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slieb.soy.context.JsonDataFactoryContext;
import org.slieb.soy.factories.JsonConverterFactory;
import org.slieb.soy.meta.MetaContext;

import javax.annotation.Nonnull;
import java.util.function.Function;

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
    public Function<Object, ?> create(@Nonnull Class<?> classObject,
                                      @Nonnull JsonDataFactoryContext context) {
        return new MetaInformationToJsonDataConverterConverter(context).apply(metaContext.apply(classObject));
    }
}
