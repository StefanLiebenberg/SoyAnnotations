package org.slieb.soy.factories;

import com.google.template.soy.data.SoyValue;
import org.slieb.soy.context.SoyDataFactoryContext;

import javax.annotation.Nonnull;
import java.util.function.Function;

public interface SoyConverterFactory {

    @Nonnull
    Boolean canCreate(@Nonnull Class<?> classObject);

    @Nonnull
    Function<Object, ? extends SoyValue> create(@Nonnull Class<?> classObject,
                                                @Nonnull SoyDataFactoryContext context);
}
