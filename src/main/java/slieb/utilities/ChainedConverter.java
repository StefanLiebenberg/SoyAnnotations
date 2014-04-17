package slieb.utilities;


import ch.lambdaj.function.convert.Converter;
import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ChainedConverter implements Converter {

    private final ImmutableList<Converter> converters;

    public ChainedConverter(List<Converter> converters) {
        this.converters = ImmutableList.copyOf(converters);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Object from) {
        Object value = from;
        for (Converter converter : converters) {
            value = converter.convert(value);
        }
        return value;
    }

}
