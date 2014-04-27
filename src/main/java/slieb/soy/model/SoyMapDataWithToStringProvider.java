package slieb.soy.model;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;

import java.util.Map;

/**
 *  Extends SoyMapData to override the toString method.
 *
 * Usage:
 * * <pre>
 * {@code
 * Object object = new String("Boo");
 *
 * SoyMapData soyMapData = new SoyMapDataWithToStringProvider(object);
 *
 * soyMapData.toString() // "Boo"
 * }
 * </pre>
 *
 *
 */
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
