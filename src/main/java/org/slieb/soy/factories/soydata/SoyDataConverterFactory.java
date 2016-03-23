package org.slieb.soy.factories.soydata;

import com.google.inject.Singleton;
import com.google.template.soy.data.SoyData;
import org.slieb.soy.context.SoyValueFactoryContext;
import org.slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;

@Singleton
public class SoyDataConverterFactory implements SoyConverterFactory {

    private static final SoyDataCastFunction CAST_FUNCTION_INSTANCE = new SoyDataCastFunction();

    public SoyDataConverterFactory() {}

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return SoyData.class.isAssignableFrom(classObject);
    }

    @Nonnull
    @Override
    public Function<Object, ? extends SoyData> create(@Nonnull Class<?> classObject,
                                                      @Nonnull SoyValueFactoryContext context) {
        checkArgument(canCreate(classObject));
        return CAST_FUNCTION_INSTANCE;
    }

    private static class SoyDataCastFunction implements Function<Object, SoyData> {

        @Override
        public SoyData apply(Object from) {
            if (from instanceof SoyData) {
                return (SoyData) from;
            } else {
                return null;
            }
        }
    }
}
