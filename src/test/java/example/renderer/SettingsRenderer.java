package example.renderer;


import example.models.Setting;
import org.slieb.soy.context.RendererFactoryContext;
import org.slieb.soy.factories.rendering.Renderer;
import org.slieb.soy.factories.rendering.RendererFactoryContextAware;

import javax.annotation.Nullable;

public class SettingsRenderer implements Renderer<Object>, RendererFactoryContextAware {

    private RendererFactoryContext factoryContext;

    @Override
    public void setRendererFactoryContext(RendererFactoryContext context) {
        this.factoryContext = context;
    }

    @Nullable
    @Override
    public String render(@Nullable Object instanceObject) {
        if (instanceObject instanceof Setting) {
            return ((Setting) instanceObject).toString(factoryContext);
        } else {
            return null;
        }
    }
}
