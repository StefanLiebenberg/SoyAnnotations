package org.slieb.soy.meta;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.data.SoyValue;
import org.slieb.soy.context.SoyDataFactoryContext;
import org.slieb.soy.converters.soydata.ClassToSoyMapDataConverter;
import org.slieb.soy.converters.soydata.DynamicConverter;
import org.slieb.soy.converters.soydata.SoyMapDataConverter;
import org.slieb.soy.exceptions.NeedsDynamicConverterException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.slieb.soy.converters.soydata.NullSafeConverter.wrapConverterWithNullSafe;
import static org.slieb.utilities.ConverterUtils.chain;
import static org.slieb.utilities.ConverterUtils.join;

public class MetaInformationToSoyDataConverter implements Function<MetaClassInformation, Function<Object, ? extends SoyData>> {

    private final SoyDataFactoryContext soyDataFactoryFactoryContext;

    private final DynamicConverter dynamicConverter;

    public MetaInformationToSoyDataConverter(SoyDataFactoryContext soyDataFactoryFactoryContext) {
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

    public Function<Object, ? extends SoyMapData> getClassConverter(MetaClassInformation metaClassInformation) {
        Function<Object, ?> valueConverter = metaClassInformation.getValueConverter();
        if (valueConverter == null) {
            final Map<String, Function<Object, ? extends SoyValue>> converterMap = getConverterMap(metaClassInformation);
            final Boolean useOriginalToString = metaClassInformation.getUseOriginalToString();
            return new ClassToSoyMapDataConverter(converterMap, useOriginalToString);
        } else {
            return join(valueConverter, new SoyMapDataConverter(dynamicConverter));
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
            return (Function<Object, ? extends SoyValue>) chain(valueConverter, soyConverter);
        } catch (StackOverflowError e) {
            throw new NeedsDynamicConverterException(memberType, e);
        }
    }

    @Override
    public Function<Object, ? extends SoyMapData> apply(MetaClassInformation from) {
        return getClassConverter(from);
    }
}
