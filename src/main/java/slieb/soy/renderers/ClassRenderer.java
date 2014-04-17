package slieb.soy.renderers;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import slieb.soy.converters.soydata.ClassToSoyMapDataConverter;
import slieb.soy.factories.rendering.Renderer;

import javax.annotation.Nullable;


public class ClassRenderer implements Renderer<Object> {

    private final Converter<Object, ? extends SoyData> dataConverter;

    private final Renderer<SoyData> dataRenderer;

    public ClassRenderer(Converter<Object, ? extends SoyData> dataConverter, Renderer<SoyData> dataRenderer) {
        this.dataConverter = dataConverter;
        this.dataRenderer = dataRenderer;
    }

    @Nullable
    @Override
    public String render(@Nullable Object instanceObject) {
        return dataRenderer.render(dataConverter.convert(instanceObject));
    }
}
