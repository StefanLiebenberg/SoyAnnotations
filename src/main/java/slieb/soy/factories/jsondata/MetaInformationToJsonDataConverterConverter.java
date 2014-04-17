package slieb.soy.factories.jsondata;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import slieb.soy.context.JsonDataFactoryContext;
import slieb.soy.converters.json.ClassToMapConverter;
import slieb.soy.converters.json.DynamicConverter;
import slieb.soy.converters.json.JsonMapConverter;
import slieb.soy.meta.MetaClassInformation;
import slieb.soy.meta.MetaMemberInformation;
import slieb.soy.meta.MetaValueConvertableInformation;

import java.util.HashMap;
import java.util.Map;

import static slieb.utilities.ConverterUtils.chain;
import static slieb.utilities.ConverterUtils.join;

public class MetaInformationToJsonDataConverterConverter implements Converter<MetaClassInformation, Converter<Object, ?>> {

    private final JsonDataFactoryContext context;

    private final DynamicConverter dynamicConverter;


    public MetaInformationToJsonDataConverterConverter(JsonDataFactoryContext context) {
        this.context = context;
        this.dynamicConverter = new DynamicConverter(context);
    }

    private Map<String, Converter<Object, ?>> getConverterMap(MetaClassInformation metaClassInformation) {
        Map<String, Converter<Object, ?>> map = new HashMap<>();
        for (Map.Entry<String, MetaMemberInformation> entry : metaClassInformation.getMembersInformation().entrySet()) {
            map.put(entry.getKey(), getValueConverter(entry.getValue()));
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public Converter<Object, ?> getValueConverter(MetaValueConvertableInformation valueConvertableInformation) {
        Class<?> memberType = valueConvertableInformation.getType();
        Converter<Object, ?> valueConverter = valueConvertableInformation.getValueConverter();
        Converter<Object, ?> soyConverter = context.create(memberType);
        return (Converter<Object, ? extends SoyData>) chain(valueConverter, soyConverter);
    }


    private Converter<Object, ?> getClassConverter(MetaClassInformation classInformation) {
        Converter<Object, ?> valueConverter = classInformation.getValueConverter();
        if (valueConverter == null) {
            return new ClassToMapConverter(getConverterMap(classInformation));
        } else {
            return join(valueConverter, new JsonMapConverter(dynamicConverter));
        }
    }


    @Override
    public Converter<Object, ?> convert(MetaClassInformation from) {
        return getClassConverter(from);
    }
}
