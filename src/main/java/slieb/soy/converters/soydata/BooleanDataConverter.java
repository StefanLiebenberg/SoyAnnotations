package slieb.soy.converters.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.inject.Singleton;
import com.google.template.soy.data.restricted.BooleanData;

@Singleton
public class BooleanDataConverter implements Converter<Object, BooleanData> {

    @Override
    public BooleanData convert(Object from) {

        if (from instanceof Boolean) {
            return BooleanData.forValue((Boolean) from);
        } else {
            return null;
        }
    }
}
