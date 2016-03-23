package org.slieb.soy.meta;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import org.slieb.soy.context.SoyValueFactoryContext;
import org.slieb.soy.converters.soydata.ClassToSoyMapDataConverter;
import org.slieb.soy.converters.soydata.DynamicConverter;
import org.slieb.soy.converters.soydata.SoyMapDataConverter;
import org.slieb.soy.exceptions.NeedsDynamicConverterException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.slieb.soy.converters.soydata.NullSafeConverter.wrapConverterWithNullSafe;

public class MetaInformationToSoyDataConverter implements Function<MetaClassInformation, Function<Object, SoyMapData>> {

    private final SoyValueFactoryContext soyDataFactoryFactoryContext;

    private final DynamicConverter dynamicConverter;

    public MetaInformationToSoyDataConverter(SoyValueFactoryContext soyDataFactoryFactoryContext) {
        this.soyDataFactoryFactoryContext = soyDataFactoryFactoryContext;
        this.dynamicConverter = new DynamicConverter(soyDataFactoryFactoryContext);
    }

    private Map<String, Function<Object, ? extends SoyData>> getConverterMap(MetaClassInformation metaClassInformation) {
        Map<String, Function<Object, ? extends SoyData>> map = new HashMap<>();
        for (Map.Entry<String, MetaMemberInformation> entry : metaClassInformation.getMembersInformation().entrySet()) {
            map.put(entry.getKey(), getValueConverter(entry.getValue()));
        }
        return map;
    }

    public Function<Object, SoyMapData> getClassConverter(MetaClassInformation metaClassInformation) {
        Function<Object, ?> valueConverter = metaClassInformation.getValueConverter();
        if (valueConverter == null) {
            final Map<String, Function<Object, ? extends SoyData>> converterMap = getConverterMap(metaClassInformation);
            final Boolean useOriginalToString = metaClassInformation.getUseOriginalToString();
            return new ClassToSoyMapDataConverter(converterMap, useOriginalToString);
        } else {
            return valueConverter.andThen(new SoyMapDataConverter(dynamicConverter));
        }
    }

    @SuppressWarnings("unchecked")
    public Function<Object, ? extends SoyData> getValueConverter(MetaValueConvertableInformation valueConvertableInformation) {
        Class<?> memberType = valueConvertableInformation.getType();
        Function<Object, ?> valueConverter = valueConvertableInformation.getValueConverter();
        try {
            Function<Object, ? extends SoyData> soyConverter =
                    valueConvertableInformation.getDynamic() ?
                            wrapConverterWithNullSafe(dynamicConverter) :
                            soyDataFactoryFactoryContext.create(memberType);
            return valueConverter.andThen(soyConverter);
        } catch (StackOverflowError e) {
            throw new NeedsDynamicConverterException(memberType, e);
        }
    }

    @Override
    public Function<Object, SoyMapData> apply(MetaClassInformation from) {
        return getClassConverter(from);
    }
}
