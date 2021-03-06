package org.slieb.soy.factories.soydata;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.template.soy.data.SoyData;
import org.slieb.soy.context.SoyValueFactoryContext;
import org.slieb.soy.converters.soydata.FloatDataConverter;
import org.slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;

import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;

@Singleton
public class FloatDataConverterFactory implements SoyConverterFactory {

    private final FloatDataConverter floatDataConverter;

    @Inject
    public FloatDataConverterFactory(FloatDataConverter floatDataConverter) {
        this.floatDataConverter = floatDataConverter;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Float.class.equals(classObject) || Double.class.equals(classObject) ||
                classObject.equals(double.class) || classObject.equals(float.class);
    }

    @Nonnull
    @Override
    public Function<Object, ? extends SoyData> create(@Nonnull Class<?> classObject,
                                                      @Nonnull SoyValueFactoryContext context) {
        checkArgument(canCreate(classObject));
        return floatDataConverter;
    }
}
