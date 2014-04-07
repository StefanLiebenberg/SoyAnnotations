package slieb.soy.converters;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;

import static com.google.template.soy.data.SoyData.createFromExistingData;


public class ObjectConverter implements Converter<Object, SoyData> {

    public static final ObjectConverter INSTANCE = new ObjectConverter();

    private ObjectConverter() {}

    @Override
    public SoyData convert(Object from) {
        return createFromExistingData(from);
    }
}
