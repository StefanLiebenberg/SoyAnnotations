package slieb.soy;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;

import java.util.Map;

public class SoyMapDataConverter
        implements Converter<Map<String, SoyData>, SoyData> {

    @Override
    public SoyData convert(Map<String, SoyData> stringSoyDataMap) {
        SoyMapData soyMapData = new SoyMapData();
        for (Map.Entry<String, SoyData> entry : stringSoyDataMap.entrySet()) {
            soyMapData.putSingle(entry.getKey(), entry.getValue());
        }
        return soyMapData;
    }
}
