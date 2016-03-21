package org.slieb.soy.factories.jsondata;

import com.google.inject.Singleton;
import org.slieb.soy.context.JsonDataFactoryContext;
import org.slieb.soy.converters.json.DynamicConverter;
import org.slieb.soy.converters.json.JsonMapConverter;
import org.slieb.soy.factories.JsonConverterFactory;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.function.Function;

@Singleton
public class MapJsonConverterFactory implements JsonConverterFactory {

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Map.class.isAssignableFrom(classObject);
    }

    @Nonnull
    @Override
    public Function<Object, ?> create(@Nonnull Class<?> classObject,
                                      @Nonnull JsonDataFactoryContext context) {
        return new JsonMapConverter(new DynamicConverter(context));
    }
}
