package slieb.soy.meta;


import ch.lambdaj.function.convert.Converter;

import java.util.Map;

public interface MetaConverter extends Converter<Object, Map<String, ?>> {

    @Override
    public Map<String, ?> convert(Object from);
}
