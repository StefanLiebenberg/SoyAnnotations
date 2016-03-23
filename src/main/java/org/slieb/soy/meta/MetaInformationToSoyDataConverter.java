package org.slieb.soy.meta;

import com.google.template.soy.data.SoyMap;
import com.google.template.soy.data.SoyValue;
import org.slieb.soy.context.SoyValueFactoryContext;
import org.slieb.soy.converters.soydata.ClassToSoyMapDataConverter;
import org.slieb.soy.converters.soydata.DynamicConverter;
import org.slieb.soy.converters.soydata.SoyMapDataConverter;
import org.slieb.soy.exceptions.NeedsDynamicConverterException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.slieb.soy.converters.soydata.NullSafeConverter.wrapConverterWithNullSafe;

public class MetaInformationToSoyDataConverter implements Function<MetaClassInformation, Function<Object, SoyMap>> {

    private final SoyValueFactoryContext soyDataFactoryFactoryContext;

    private final DynamicConverter dynamicConverter;

    public MetaInformationToSoyDataConverter(SoyValueFactoryContext soyDataFactoryFactoryContext) {
        this.soyDataFactoryFactoryContext = soyDataFactoryFactoryContext;
        this.dynamicConverter = new DynamicConverter(soyDataFactoryFactoryContext);
    }

    private Map<String, Function<Object, ? extends SoyValue>> getConverterMap(MetaClassInformation metaClassInformation) {
        Map<String, Function<Object, ? extends SoyValue>> map = new HashMap<>();
        for (Map.Entry<String, MetaMemberInformation> entry : metaClassInformation.getMembersInformation().entrySet()) {
            map.put(entry.getKey(), getValueConverter(entry.getValue()));
        }
        return map;
    }

    public Function<Object, SoyMap> getClassConverter(MetaClassInformation metaClassInformation) {
        Function<Object, ?> valueConverter = metaClassInformation.getValueConverter();
        if (valueConverter == null) {
            final Map<String, Function<Object, ? extends SoyValue>> converterMap = getConverterMap(metaClassInformation);
            final Boolean useOriginalToString = metaClassInformation.getUseOriginalToString();
            return new ClassToSoyMapDataConverter(converterMap, useOriginalToString);
        } else {
            return valueConverter.andThen(new SoyMapDataConverter(dynamicConverter));
        }
    }

    @SuppressWarnings("unchecked")
    public Function<Object, ? extends SoyValue> getValueConverter(MetaValueConvertableInformation valueConvertableInformation) {
        Class<?> memberType = valueConvertableInformation.getType();
        Function<Object, ?> valueConverter = valueConvertableInformation.getValueConverter();
        try {
            Function<Object, ? extends SoyValue> soyConverter =
                    valueConvertableInformation.getDynamic() ?
                            wrapConverterWithNullSafe(dynamicConverter) :
                            soyDataFactoryFactoryContext.create(memberType);
            return valueConverter.andThen(soyConverter);
        } catch (StackOverflowError e) {
            throw new NeedsDynamicConverterException(memberType, e);
        }
    }

    @Override
    public Function<Object, SoyMap> apply(MetaClassInformation from) {
        return getClassConverter(from);
    }
}
