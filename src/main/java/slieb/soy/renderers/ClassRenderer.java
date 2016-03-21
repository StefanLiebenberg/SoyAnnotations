package slieb.soy.renderers;

import com.google.template.soy.data.SoyValue;
import slieb.soy.factories.rendering.Renderer;

import javax.annotation.Nullable;
import java.util.function.Function;

public class ClassRenderer implements Renderer<Object> {

    private final Function<Object, ? extends SoyValue> dataConverter;

    private final Renderer<SoyValue> dataRenderer;

    public ClassRenderer(Function<Object, ? extends SoyValue> dataConverter,
                         Renderer<SoyValue> dataRenderer) {
        this.dataConverter = dataConverter;
        this.dataRenderer = dataRenderer;
    }

    @Nullable
    @Override
    public String render(@Nullable Object instanceObject) {
        return dataRenderer.render(dataConverter.apply(instanceObject));
    }
}
