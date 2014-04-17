package slieb.soy.converters.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.inject.Singleton;
import com.google.template.soy.data.restricted.FloatData;

@Singleton
public class FloatDataConverter implements Converter<Object, FloatData> {

    @Override
    public FloatData convert(Object from) {
        if (from instanceof Float) {
            return FloatData.forValue((Float) from);
        }
        if (from instanceof Double) {
            return FloatData.forValue((Double) from);
        }
        return null;
    }
}
