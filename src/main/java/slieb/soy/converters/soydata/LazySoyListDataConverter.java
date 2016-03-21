package slieb.soy.converters.soydata;

import com.google.template.soy.data.SoyList;
import com.google.template.soy.data.SoyValue;
import slieb.soy.model.DefaultLazyResult;
import slieb.soy.model.DefaultLazyResultWithOriginalToString;
import slieb.soy.model.LazySoyListData;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

public class LazySoyListDataConverter implements Function<Object, SoyValue> {

    private final Function<Object, SoyList> listDataConverter;

    private final Boolean useOriginalToString;

    public LazySoyListDataConverter(Function<Object, SoyList> listDataConverter,
                                    Boolean useOriginalToString) {
        this.listDataConverter = listDataConverter;
        this.useOriginalToString = useOriginalToString;
    }

    public Supplier<SoyList> getLazyResult(Object from) {
        if (useOriginalToString) {
            return new DefaultLazyResultWithOriginalToString<>(from, listDataConverter);
        } else {
            return new DefaultLazyResult<>(from, listDataConverter);
        }
    }

    @Override
    public SoyList apply(Object from) {
        if (from instanceof Collection) {
            return new LazySoyListData(getLazyResult(from));
        } else {
            return null;
        }
    }
}
