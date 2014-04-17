package slieb.soy.converters.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.inject.Singleton;
import com.google.template.soy.data.restricted.StringData;

@Singleton
public class StringDataConverter implements Converter<Object, StringData> {

    @Override
    public StringData convert(Object from) {
        if (from instanceof String) {
            return StringData.forValue((String) from);
        } else {
            return null;
        }
    }
}
