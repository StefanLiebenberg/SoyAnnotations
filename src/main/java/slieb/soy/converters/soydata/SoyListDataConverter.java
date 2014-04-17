package slieb.soy.converters.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyListData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;


public class SoyListDataConverter implements Converter<Object, SoyListData> {

    private final Converter<Object, ? extends SoyData> itemConverter;

    public SoyListDataConverter(@Nonnull Converter<Object, ? extends SoyData> itemConverter) {
        this.itemConverter = itemConverter;
    }

    @Override
    @Nullable
    public SoyListData convert(@Nullable Object collection) {
        if (collection instanceof Collection) {
            SoyListData soyListData = new SoyListData();
            for (Object item : (Collection) collection) {
                soyListData.add(itemConverter.convert(item));
            }
            return soyListData;
        } else {
            return null;
        }
    }
}
