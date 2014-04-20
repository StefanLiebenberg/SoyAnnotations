package slieb.soy.converters.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.data.restricted.NullData;
import slieb.soy.model.DefaultLazyResult;
import slieb.soy.model.LazyResult;
import slieb.soy.model.LazySoyMapData;

public class LazyMapDataConverter implements Converter<Object, SoyData> {

    private final Converter<Object, ? extends SoyMapData> soyMapDataConverter;


    public LazyMapDataConverter(Converter<Object, ? extends SoyMapData> soyMapDataConverter) {
        this.soyMapDataConverter = soyMapDataConverter;
    }

    public LazyResult<? extends SoyMapData> getLazyResult(Object from) {
        return new DefaultLazyResult<>(from, soyMapDataConverter);
    }

    @Override
    public SoyData convert(Object from) {
        if (from != null) {
            return new LazySoyMapData(getLazyResult(from));
        } else {
            return NullData.INSTANCE;
        }
    }
}
