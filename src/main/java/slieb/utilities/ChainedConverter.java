package slieb.utilities;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.function.Function;

public class ChainedConverter implements Function {

    private final ImmutableList<Function> converters;

    public ChainedConverter(List<Function> converters) {
        this.converters = ImmutableList.copyOf(converters);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object apply(Object from) {
        Object value = from;
        for (Function converter : converters) {
            value = converter.apply(value);
        }
        return value;
    }
}
