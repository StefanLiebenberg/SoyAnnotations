package slieb.soy.renderers;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyMapData;
import slieb.soy.factories.rendering.Renderer;

import javax.annotation.Nullable;


public class ClassRenderer implements Renderer<Object> {

    private final Converter<Object, ? extends SoyMapData> dataConverter;

    private final Renderer<SoyMapData> dataRenderer;

    public ClassRenderer(Converter<Object, ? extends SoyMapData> dataConverter, Renderer<SoyMapData> dataRenderer) {
        this.dataConverter = dataConverter;
        this.dataRenderer = dataRenderer;
    }

    @Nullable
    @Override
    public String render(@Nullable Object instanceObject) {
        return dataRenderer.render(dataConverter.convert(instanceObject));
    }
}
