package slieb.soy.converters.soydata;

import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.data.SoyValue;
import slieb.soy.model.SoyMapDataWithToStringProvider;

import java.util.Map;
import java.util.function.Function;

public class ClassToSoyMapDataConverter implements Function<Object, SoyMapData> {

    public final Map<String, Function<Object, ? extends SoyValue>> membersConverters;

    private final Boolean useOriginalToString;

    public ClassToSoyMapDataConverter(Map<String, Function<Object, ? extends SoyValue>> membersConverters,
                                      Boolean useOriginalToString) {
        this.membersConverters = membersConverters;
        this.useOriginalToString = useOriginalToString;
    }

    private SoyMapData getSoyMapData(Object from) {
        if (useOriginalToString) {
            return new SoyMapDataWithToStringProvider(from);
        } else {
            return new SoyMapData();
        }
    }

    @Override
    public SoyMapData apply(Object from) {
        if (from != null) {
            SoyMapData soyMapData = getSoyMapData(from);
            for (Map.Entry<String, Function<Object, ? extends SoyValue>> entry : membersConverters.entrySet()) {
                soyMapData.put(entry.getKey(), entry.getValue().apply(from));
            }
            return soyMapData;
        } else {
            return null;
        }
    }
}
