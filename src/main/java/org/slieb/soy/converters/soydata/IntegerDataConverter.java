package org.slieb.soy.converters.soydata;


import org.slieb.soy.internal.Converter;
import com.google.inject.Singleton;
import com.google.template.soy.data.restricted.IntegerData;

import javax.annotation.Nullable;

@Singleton
public class IntegerDataConverter implements Converter<Object, IntegerData> {

    @Override
    @Nullable
    public IntegerData apply(@Nullable Object from) {
        if (from instanceof Integer) {
            return IntegerData.forValue((Integer) from);
        } else {
            return null;
        }
    }
}
