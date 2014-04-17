package slieb.soy.converters.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;

import java.util.Map;

public class ClassToSoyMapDataConverter implements Converter<Object, SoyMapData> {

    public final Map<String, Converter<Object, ? extends SoyData>> membersConverters;

    public ClassToSoyMapDataConverter(Map<String, Converter<Object, ? extends SoyData>> membersConverters) {
        this.membersConverters = membersConverters;
    }

    @Override
    public SoyMapData convert(Object from) {
        if (from != null) {
            SoyMapData soyMapData = new SoyMapData();
            for (Map.Entry<String, Converter<Object, ? extends SoyData>> entry : membersConverters.entrySet()) {
                soyMapData.put(entry.getKey(), entry.getValue().convert(from));
            }
            return soyMapData;
        } else {
            return null;
        }
    }
}
