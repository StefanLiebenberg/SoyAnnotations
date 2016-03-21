package slieb.soy.meta;


import slieb.soy.internal.Converter;

import java.util.Map;

public interface MetaConverter extends Converter<Object, Map<String, ?>> {

    @Override
    public Map<String, ?> apply(Object from);
}
