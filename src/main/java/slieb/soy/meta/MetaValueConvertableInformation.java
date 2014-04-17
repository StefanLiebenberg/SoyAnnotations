package slieb.soy.meta;


import ch.lambdaj.function.convert.Converter;

import javax.annotation.concurrent.Immutable;

@Immutable
public class MetaValueConvertableInformation extends MetaInformation {

    private final Converter<Object, ?> valueConverter;

    public MetaValueConvertableInformation(Boolean dynamic, Class<?> type, Converter<Object, ?> valueConverter) {
        super(dynamic, type);
        this.valueConverter = valueConverter;
    }

    public final Converter<Object, ?> getValueConverter() {
        return valueConverter;
    }
}
