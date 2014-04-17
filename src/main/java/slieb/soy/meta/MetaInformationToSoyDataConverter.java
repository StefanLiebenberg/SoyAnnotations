package slieb.soy.meta;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.converters.soydata.ClassToSoyMapDataConverter;
import slieb.soy.converters.soydata.DynamicConverter;
import slieb.soy.converters.soydata.SoyMapDataConverter;
import slieb.soy.exceptions.NeedsDynamicConverterException;

import java.util.HashMap;
import java.util.Map;

import static slieb.soy.converters.soydata.NullSafeConverter.wrapConverterWithNullSafe;
import static slieb.utilities.ConverterUtils.chain;
import static slieb.utilities.ConverterUtils.join;


public class MetaInformationToSoyDataConverter implements Converter<MetaClassInformation, Converter<Object, ? extends SoyData>> {

    private final SoyDataFactoryContext soyDataFactoryFactoryContext;

    private final DynamicConverter dynamicConverter;

    public MetaInformationToSoyDataConverter(SoyDataFactoryContext soyDataFactoryFactoryContext) {
        this.soyDataFactoryFactoryContext = soyDataFactoryFactoryContext;
        this.dynamicConverter = new DynamicConverter(soyDataFactoryFactoryContext);
    }

    private Map<String, Converter<Object, ? extends SoyData>> getConverterMap(MetaClassInformation metaClassInformation) {
        Map<String, Converter<Object, ? extends SoyData>> map = new HashMap<>();
        for (Map.Entry<String, MetaMemberInformation> entry : metaClassInformation.getMembersInformation().entrySet()) {
            map.put(entry.getKey(), getValueConverter(entry.getValue()));
        }
        return map;
    }

    public Converter<Object, ? extends SoyMapData> getClassConverter(MetaClassInformation metaClassInformation) {
        Converter<Object, ?> valueConverter = metaClassInformation.getValueConverter();
        if (valueConverter == null) {
            return new ClassToSoyMapDataConverter(getConverterMap(metaClassInformation));
        } else {
            return join(valueConverter, new SoyMapDataConverter(dynamicConverter));
        }
    }

    @SuppressWarnings("unchecked")
    public Converter<Object, ? extends SoyData> getValueConverter(MetaValueConvertableInformation valueConvertableInformation) {
        try {
            Class<?> memberType = valueConvertableInformation.getType();
            Converter<Object, ?> valueConverter = valueConvertableInformation.getValueConverter();
            Converter<Object, ? extends SoyData> soyConverter =
                    valueConvertableInformation.getDynamic() ?
                            wrapConverterWithNullSafe(dynamicConverter) :
                            soyDataFactoryFactoryContext.create(memberType);
            return (Converter<Object, ? extends SoyData>) chain(valueConverter, soyConverter);
        } catch (StackOverflowError e) {
            throw new NeedsDynamicConverterException();
        }
    }

    @Override
    public Converter<Object, ? extends SoyMapData> convert(MetaClassInformation from) {
        return getClassConverter(from);
    }
}
