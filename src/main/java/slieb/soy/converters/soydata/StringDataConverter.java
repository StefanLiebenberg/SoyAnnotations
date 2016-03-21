package slieb.soy.converters.soydata;


import slieb.soy.internal.Converter;
import com.google.inject.Singleton;
import com.google.template.soy.data.restricted.StringData;

@Singleton
public class StringDataConverter implements Converter<Object, StringData> {

    @Override
    public StringData apply(Object from) {
        if (from != null) {
            return StringData.forValue(String.valueOf(from));
        } else {
            return null;
        }
    }
}
