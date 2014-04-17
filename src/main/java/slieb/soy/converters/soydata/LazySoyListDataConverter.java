package slieb.soy.converters.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyListData;
import slieb.soy.model.DefaultLazyResult;
import slieb.soy.model.LazySoyListData;

import java.util.Collection;

public class LazySoyListDataConverter implements Converter<Object, SoyData> {

    private final Converter<Object, SoyListData> listDataConverter;

    public LazySoyListDataConverter(Converter<Object, SoyListData> listDataConverter) {
        this.listDataConverter = listDataConverter;
    }

    @Override
    public SoyListData convert(Object from) {
        if (from instanceof Collection) {
            return new LazySoyListData(new DefaultLazyResult<>(from, listDataConverter));
        } else {
            return null;
        }
    }
}
