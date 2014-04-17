package slieb.soy.factories.jsondata;

import ch.lambdaj.function.convert.Converter;
import com.google.inject.Singleton;
import slieb.soy.context.JsonDataFactoryContext;
import slieb.soy.converters.json.DynamicConverter;
import slieb.soy.converters.json.JsonMapConverter;
import slieb.soy.factories.JsonConverterFactory;

import javax.annotation.Nonnull;
import java.util.Map;


@Singleton
public class MapJsonConverterFactory implements JsonConverterFactory {

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Map.class.isAssignableFrom(classObject);
    }

    @Nonnull
    @Override
    public Converter<Object, ?> create(@Nonnull Class<?> classObject, @Nonnull JsonDataFactoryContext context) {
        return new JsonMapConverter(new DynamicConverter(context));
    }
}
