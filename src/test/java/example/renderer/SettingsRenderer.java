package example.renderer;


import example.models.Setting;
import slieb.soy.factories.rendering.Renderer;
import slieb.soy.factories.rendering.RendererFactoryContext;

import javax.annotation.Nullable;

public class SettingsRenderer implements Renderer<Object> {

    private final RendererFactoryContext factoryContext;

    public SettingsRenderer(RendererFactoryContext factoryContext) {
        this.factoryContext = factoryContext;
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
