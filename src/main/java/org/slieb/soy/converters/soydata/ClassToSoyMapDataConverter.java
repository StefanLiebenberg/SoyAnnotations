package org.slieb.soy.converters.soydata;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import org.slieb.soy.model.SoyMapDataWithToStringProvider;

import java.util.Map;
import java.util.function.Function;

public class ClassToSoyMapDataConverter implements Function<Object, SoyMapData> {

    /**
     * Important to keep this SoyData for the time being.
     */
    public final Map<String, Function<Object, ? extends SoyData>> membersConverters;

    private final Boolean useOriginalToString;

    public ClassToSoyMapDataConverter(Map<String, Function<Object, ? extends SoyData>> membersConverters,
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
            for (Map.Entry<String, Function<Object, ? extends SoyData>> entry : membersConverters.entrySet()) {
                soyMapData.putSingle(entry.getKey(), entry.getValue().apply(from));
            }
            return soyMapData;
        } else {
            return null;
        }
    }
}
