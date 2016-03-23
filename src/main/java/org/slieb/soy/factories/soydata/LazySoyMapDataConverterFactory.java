package org.slieb.soy.factories.soydata;


import com.google.inject.Singleton;
import com.google.template.soy.data.SoyValue;
import org.slieb.soy.context.SoyValueFactoryContext;
import org.slieb.soy.converters.soydata.DynamicConverter;
import org.slieb.soy.converters.soydata.LazyMapDataConverter;
import org.slieb.soy.converters.soydata.SoyMapDataConverter;
import org.slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.function.Function;

import static org.slieb.soy.converters.soydata.NullSafeConverter.wrapConverterWithNullSafe;

@Singleton
public class LazySoyMapDataConverterFactory implements SoyConverterFactory {

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Map.class.isAssignableFrom(classObject);
    }

    @Nonnull
    @Override
    public Function<Object, SoyValue> create(@Nonnull Class<?> classObject, @Nonnull SoyValueFactoryContext context) {
        return new LazyMapDataConverter(new SoyMapDataConverter(wrapConverterWithNullSafe(new DynamicConverter(context))));
    }
}
