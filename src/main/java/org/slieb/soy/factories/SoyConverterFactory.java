package org.slieb.soy.factories;

import com.google.template.soy.data.SoyData;
import org.slieb.soy.context.SoyValueFactoryContext;

import javax.annotation.Nonnull;
import java.util.function.Function;

public interface SoyConverterFactory {

    @Nonnull
    Boolean canCreate(@Nonnull Class<?> classObject);

    @Nonnull
    Function<Object, ? extends SoyData> create(@Nonnull Class<?> classObject,
                                               @Nonnull SoyValueFactoryContext context);
}
