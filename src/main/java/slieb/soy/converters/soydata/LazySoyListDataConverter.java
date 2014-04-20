package slieb.soy.converters.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyListData;
import slieb.soy.model.DefaultLazyResult;
import slieb.soy.model.DefaultLazyResultWithCustomToString;
import slieb.soy.model.LazyResult;
import slieb.soy.model.LazySoyListData;

import java.util.Collection;

public class LazySoyListDataConverter implements Converter<Object, SoyData> {

    private final Converter<Object, SoyListData> listDataConverter;
    private final Boolean isCustomString;


    public LazySoyListDataConverter(Converter<Object, SoyListData> listDataConverter, Boolean customString) {
        this.listDataConverter = listDataConverter;
        isCustomString = customString;
    }

    public LazyResult<SoyListData> getLazyResult(Object from) {
        if (isCustomString) {
            return new DefaultLazyResultWithCustomToString<>(from, listDataConverter);
        } else {
            return new DefaultLazyResult<>(from, listDataConverter);
        }
    }

    @Override
    public SoyListData convert(Object from) {
        if (from instanceof Collection) {
            return new LazySoyListData(getLazyResult(from));
        } else {
            return null;
        }
    }
}
