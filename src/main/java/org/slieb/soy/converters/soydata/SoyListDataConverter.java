package org.slieb.soy.converters.soydata;

import com.google.template.soy.data.SoyList;
import com.google.template.soy.data.SoyListData;
import com.google.template.soy.data.SoyValue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.function.Function;

public class SoyListDataConverter implements Function<Object, SoyList> {

    private final Function<Object, ? extends SoyValue> itemConverter;

    public SoyListDataConverter(@Nonnull Function<Object, ? extends SoyValue> itemConverter) {
        this.itemConverter = itemConverter;
    }

    @Override
    @Nullable
    public SoyList apply(@Nullable Object collection) {
        if (collection instanceof Collection) {
            SoyListData soyListData = new SoyListData();
            for (Object item : (Collection) collection) {
                soyListData.add(itemConverter.apply(item));
            }
            return soyListData;
        } else {
            return null;
        }
    }
}
