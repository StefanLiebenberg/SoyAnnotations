package org.slieb.soy.factories.jsondata;

import com.google.common.primitives.Primitives;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slieb.soy.context.JsonDataFactoryContext;
import org.slieb.soy.converters.json.NativeJsonConverter;
import org.slieb.soy.factories.JsonConverterFactory;

import javax.annotation.Nonnull;
import java.util.function.Function;

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
    public Function<Object, ?> create(@Nonnull Class<?> classObject,
                                      @Nonnull JsonDataFactoryContext context) {
        return nativeJsonConverter;
    }
}
