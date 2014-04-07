package slieb.soy.converters;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.restricted.NullData;

public class SoyDataIdentityConverter implements Converter<Object, SoyData> {

    public static final SoyDataIdentityConverter INSTANCE =
            new SoyDataIdentityConverter();

    private SoyDataIdentityConverter() {}

    @Override
    public SoyData convert(Object from) {
        if (from != null && from instanceof SoyData) {
            return (SoyData) from;
        } else {
            return NullData.INSTANCE;
        }
    }
}
