package org.slieb.soy.factories.soydata;

import com.google.inject.Singleton;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyValue;
import org.slieb.soy.context.SoyDataFactoryContext;
import org.slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;

import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;

@Singleton
public class SoyDataConverterFactory implements SoyConverterFactory, Function<Object, SoyData> {

    public static final SoyDataConverterFactory INSTANCE = new SoyDataConverterFactory();

    public SoyDataConverterFactory() {}

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return SoyData.class.isAssignableFrom(classObject);
    }

    @Nonnull
    @Override
    public Function<Object, ? extends SoyValue> create(@Nonnull Class<?> classObject,
                                                       @Nonnull SoyDataFactoryContext context) {
        checkArgument(canCreate(classObject));
        return this;
    }

    // todo, move out to own factory
    @Override
    public SoyData apply(Object from) {
        if (from instanceof SoyData) {
            return (SoyData) from;
        } else {
            return null;
        }
    }
}
