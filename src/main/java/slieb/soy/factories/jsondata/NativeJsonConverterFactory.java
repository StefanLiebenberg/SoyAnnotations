package slieb.soy.factories.jsondata;


import ch.lambdaj.function.convert.Converter;
import com.google.common.primitives.Primitives;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import slieb.soy.context.JsonDataFactoryContext;
import slieb.soy.converters.json.NativeJsonConverter;
import slieb.soy.factories.JsonConverterFactory;

import javax.annotation.Nonnull;

@Singleton
public class NativeJsonConverterFactory implements JsonConverterFactory {

    private final NativeJsonConverter nativeJsonConverter;

    @Inject
    public NativeJsonConverterFactory(NativeJsonConverter nativeJsonConverter) {
        this.nativeJsonConverter = nativeJsonConverter;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return classObject.isPrimitive() || Primitives.isWrapperType(classObject) ||
                classObject.equals(String.class) || classObject.equals(Object.class);
    }

    @Nonnull
    @Override
    public Converter<Object, ?> create(@Nonnull Class<?> classObject, @Nonnull JsonDataFactoryContext context) {
        return nativeJsonConverter;
    }
}
