package slieb.soy.internal;


import ch.lambdaj.function.convert.Converter;

import java.util.HashMap;
import java.util.Map;

public class ClassConverter<A>
        implements Converter<Object, A> {

    private final Map<String, Converter<Object, A>> converterMap;

    private final Converter<Map<String, A>, A> mapConverter;

    public ClassConverter(Map<String, Converter<Object, A>> converterMap,
                          Converter<Map<String, A>, A> mapConverter) {
        this.converterMap = converterMap;
        this.mapConverter = mapConverter;
    }

    @Override
    public A convert(Object object) {
        Map<String, A> map = new HashMap<>();
        for (Map.Entry<String, Converter<Object, A>> entry : converterMap
                .entrySet()) {
            String key = entry.getKey();
            Converter<Object, A> converter = entry.getValue();
            A value = converter.convert(object);
            map.put(key, value);
        }
        return mapConverter.convert(map);
    }
}
