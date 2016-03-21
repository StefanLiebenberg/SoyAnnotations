package slieb.soy.factories.soydata;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.template.soy.data.SoyValue;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.converters.soydata.DynamicConverter;
import slieb.soy.converters.soydata.LazySoyListDataConverter;
import slieb.soy.converters.soydata.NullSafeConverter;
import slieb.soy.converters.soydata.SoyListDataConverter;
import slieb.soy.factories.SoyConverterFactory;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;
import java.util.function.Function;

import static slieb.soy.converters.soydata.NullSafeConverter.wrapConverterWithNullSafe;

@Singleton
public class LazySoyListDataConverterFactory implements SoyConverterFactory {

    private final FactoryHelper factoryHelper;

    @Inject
    public LazySoyListDataConverterFactory(FactoryHelper factoryHelper) {
        this.factoryHelper = factoryHelper;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Iterable.class.isAssignableFrom(classObject);
    }

    @Nonnull
    @Override
    public Function<Object, ? extends SoyValue> create(@Nonnull Class<?> classObject,
                                                       @Nonnull SoyDataFactoryContext context) {
        Boolean useOriginalToString = factoryHelper.useOriginalToString(classObject);
        final DynamicConverter dynamicConverter = new DynamicConverter(context);
        final NullSafeConverter itemConverter = wrapConverterWithNullSafe(dynamicConverter);
        final SoyListDataConverter soyListDataConverter = new SoyListDataConverter(itemConverter);
        return new LazySoyListDataConverter(soyListDataConverter, useOriginalToString);
    }
}
