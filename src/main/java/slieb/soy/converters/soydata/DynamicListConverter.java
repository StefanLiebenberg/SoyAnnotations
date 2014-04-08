package slieb.soy.converters.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyListData;
import com.google.template.soy.data.restricted.NullData;
import slieb.soy.model.DefaultDynamicResult;
import slieb.soy.model.DynamicSoyListData;

import java.util.Collection;

public class DynamicListConverter implements Converter<Object, SoyData> {

    private final Converter<Object, SoyListData> listDataConverter;

    public DynamicListConverter(Converter<Object, SoyListData> listDataConverter) {
        this.listDataConverter = listDataConverter;
    }

    @Override
    public SoyData convert(Object from) {
        if (from instanceof Collection) {
            return new DynamicSoyListData(new DefaultDynamicResult<>(from, listDataConverter));
        } else {
            return NullData.INSTANCE;
        }
    }
}
