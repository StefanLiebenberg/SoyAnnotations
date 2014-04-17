package slieb.soy.converters.json;


import ch.lambdaj.function.convert.Converter;

import java.util.HashMap;
import java.util.Map;

public class ClassToMapConverter implements Converter<Object, Map<String, Object>> {

    public final Map<String, Converter<Object, ?>> membersConverters;

    public ClassToMapConverter(Map<String, Converter<Object, ?>> membersConverters) {
        this.membersConverters = membersConverters;
    }

    @Override
    public Map<String, Object> convert(Object from) {
        if (from != null) {
            Map<String, Object> map = new HashMap<>();
            for (Map.Entry<String, Converter<Object, ?>> entry : membersConverters.entrySet()) {
                map.put(entry.getKey(), entry.getValue().convert(from));
            }
            return map;
        } else {
            return null;
        }
    }
}
