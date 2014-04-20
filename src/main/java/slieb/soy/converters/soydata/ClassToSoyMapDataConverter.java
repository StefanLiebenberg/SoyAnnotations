package slieb.soy.converters.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import slieb.soy.model.SoyMapDataWithToStringProvider;

import java.util.Map;

public class ClassToSoyMapDataConverter implements Converter<Object, SoyMapData> {

    public final Map<String, Converter<Object, ? extends SoyData>> membersConverters;

    private final Boolean useOriginalToString;

    public ClassToSoyMapDataConverter(Map<String, Converter<Object, ? extends SoyData>> membersConverters, Boolean useOriginalToString) {
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
    public SoyMapData convert(Object from) {
        if (from != null) {
            SoyMapData soyMapData = getSoyMapData(from);
            for (Map.Entry<String, Converter<Object, ? extends SoyData>> entry : membersConverters.entrySet()) {
                soyMapData.put(entry.getKey(), entry.getValue().convert(from));
            }
            return soyMapData;
        } else {
            return null;
        }
    }
}
