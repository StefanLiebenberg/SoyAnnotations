package slieb.soy.model;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;

import java.util.Map;


public class SoyMapDataWithToStringProvider extends SoyMapData {

    private final Object toStringProvider;

    public SoyMapDataWithToStringProvider(Object toStringProvider) {
        this.toStringProvider = toStringProvider;
    }

    @Override
    protected String toStringHelper(Map<String, SoyData> map) {
        return String.valueOf(toStringProvider);
    }
}
