package slieb.soy.factories.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.inject.Singleton;
import com.google.template.soy.data.SoyData;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;

@Singleton
public class SoyDataConverterFactory implements SoyConverterFactory, Converter<Object, SoyData> {

    public static final SoyDataConverterFactory INSTANCE = new SoyDataConverterFactory();

    public SoyDataConverterFactory() {}


    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return SoyData.class.isAssignableFrom(classObject);
    }


    @Nonnull
    @Override
    public Converter<Object, ? extends SoyData> create(@Nonnull Class<?> classObject, @Nonnull SoyDataFactoryContext context) {
        checkArgument(canCreate(classObject));
        return this;
    }

    // todo, move out to own factory
    @Override
    public SoyData convert(Object from) {
        if (from instanceof SoyData) {
            return (SoyData) from;
        } else {
            return null;
        }
    }
}
