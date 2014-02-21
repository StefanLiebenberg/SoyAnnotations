package slieb.soy;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.tofu.SoyTofu;

public class Renderer {

    private final SoyTofu.Renderer tofuRenderer;

    private final Converter<Object, SoyData> soyDataConverter;

    public Renderer(SoyTofu.Renderer tofuRenderer,
                    Converter<Object, SoyData> soyDataConverter) {
        this.tofuRenderer = tofuRenderer;
        this.soyDataConverter = soyDataConverter;
    }

    public String render(Object object) {
        SoyData soyData = soyDataConverter.convert(object);
        if (soyData instanceof SoyMapData) {
            return tofuRenderer.setData((SoyMapData) soyData).render();
        } else {
            return null;
        }
    }
}