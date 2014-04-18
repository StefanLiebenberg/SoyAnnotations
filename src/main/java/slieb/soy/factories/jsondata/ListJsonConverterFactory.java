package slieb.soy.factories.jsondata;

import ch.lambdaj.function.convert.Converter;
import com.google.inject.Singleton;
import slieb.soy.context.JsonDataFactoryContext;
import slieb.soy.converters.json.DynamicConverter;
import slieb.soy.converters.json.JsonListConverter;
import slieb.soy.factories.JsonConverterFactory;

import javax.annotation.Nonnull;

@Singleton
public class ListJsonConverterFactory implements JsonConverterFactory {

    @Nonnull
    @Override
    public Converter<Object, ?> create(@Nonnull Class<?> classObject, @Nonnull JsonDataFactoryContext context) {
        return new JsonListConverter(new DynamicConverter(context));
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Iterable.class.isAssignableFrom(classObject);
    }
}