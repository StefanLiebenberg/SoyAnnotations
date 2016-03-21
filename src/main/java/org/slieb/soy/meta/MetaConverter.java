package org.slieb.soy.meta;


import org.slieb.soy.internal.Converter;

import java.util.Map;

public interface MetaConverter extends Converter<Object, Map<String, ?>> {

    @Override
    public Map<String, ?> apply(Object from);
}
