package slieb.soy.converters.json;

import ch.lambdaj.function.convert.Converter;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class StrictClassToJsonMapConverter implements Converter<Object, Map<String, Object>> {

    private final ImmutableMap<String, Converter<Object, ?>> memberConverters;

    public StrictClassToJsonMapConverter(ImmutableMap<String, Converter<Object, ?>> memberConverters) {
        this.memberConverters = memberConverters;
    }

    @Override
    public Map<String, Object> convert(Object from) {
        Map<String, Object> map = new HashMap<>();
        for (Entry<String, Converter<Object, ?>> entry : memberConverters.entrySet()) {
            map.put(entry.getKey(), entry.getValue().convert(from));
        }
        return map;
    }
}
