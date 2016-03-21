package example.models;

import example.renderer.SettingsRenderer;
import org.slieb.soy.annotations.CustomRenderer;
import org.slieb.soy.annotations.Soy;
import org.slieb.soy.context.RendererFactoryContext;

import static java.lang.String.format;

@Soy
@CustomRenderer(SettingsRenderer.class)
public class Setting<A> {

    @Soy.Field
    public String Name;

    @Soy.Field
    public A Value;

    public String toString(RendererFactoryContext factoryContext) {
        return format("%s: %s", Name, factoryContext.render(Value));
    }
}
