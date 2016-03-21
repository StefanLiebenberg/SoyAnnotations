package org.slieb.soy.meta;


import org.slieb.soy.internal.Converter;

import javax.annotation.concurrent.Immutable;
import java.lang.reflect.Member;

@Immutable
public class MetaMemberInformation extends MetaValueConvertableInformation {

    private final Member member;

    public MetaMemberInformation(Boolean dynamic, Class<?> type, Converter<Object, ?> valueConverter, Member member) {
        super(dynamic, type, valueConverter);
        this.member = member;
    }

    public final Member getMember() {
        return member;
    }
}
