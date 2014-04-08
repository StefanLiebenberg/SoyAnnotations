package slieb.soy.converters.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.restricted.NullData;
import slieb.soy.model.LongData;

public class LongConverter implements Converter<Object, SoyData> {

    public static final LongConverter INSTANCE = new LongConverter();

    private LongConverter() {}

    @Override
    public SoyData convert(Object from) {
        if (from instanceof Long) {
            return new LongData((Long) from);
        } else {
            return NullData.INSTANCE;
        }
    }
}
