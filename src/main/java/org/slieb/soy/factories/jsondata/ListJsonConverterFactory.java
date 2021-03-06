package org.slieb.soy.factories.jsondata;

import com.google.inject.Singleton;
import org.slieb.soy.context.JsonDataFactoryContext;
import org.slieb.soy.converters.json.DynamicConverter;
import org.slieb.soy.converters.json.JsonListConverter;
import org.slieb.soy.factories.JsonConverterFactory;

import javax.annotation.Nonnull;
import java.util.function.Function;

@Singleton
public class ListJsonConverterFactory implements JsonConverterFactory {

    @Nonnull
    @Override
    public Function<Object, ?> create(@Nonnull Class<?> classObject,
                                      @Nonnull JsonDataFactoryContext context) {
        return new JsonListConverter(new DynamicConverter(context));
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Iterable.class.isAssignableFrom(classObject);
    }
}
