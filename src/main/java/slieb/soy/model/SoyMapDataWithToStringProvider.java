package slieb.soy.model;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;

import java.util.Map;

/**
 *  Extends SoyMapData to override the toString method.
 *
 * <h3>example:</h3>
 * <pre>
 * {@code
 *   new SoyMapDataWithToStringProvider(new String("Boo")).toString() // "Boo";
 * }
 * </pre>
 *
 *
 */
public class SoyMapDataWithToStringProvider extends SoyMapData {

    private final Object toStringProvider;

    /**
     * @param toStringProvider an Object who provides a toString method that
     *                         will be used to override the map's toString.
     */
    public SoyMapDataWithToStringProvider(Object toStringProvider) {
        this.toStringProvider = toStringProvider;
    }

    @Override
    protected String toStringHelper(Map<String, SoyData> map) {
        return String.valueOf(toStringProvider);
    }
}
