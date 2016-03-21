package org.slieb.soy.converters.soydata;


import com.google.inject.Singleton;
import org.slieb.soy.model.LongData;

import java.util.function.Function;

@Singleton
public class LongDataConverter implements Function<Object, LongData> {

    @Override
    public LongData apply(Object from) {
        if (from instanceof Long) {
            return new LongData((Long) from);
        } else {
            return null;
        }
    }
}
