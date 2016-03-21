package org.slieb.soy.renderers;


import org.slieb.soy.factories.rendering.Renderer;
import org.slieb.soy.context.RendererFactoryContext;

import javax.annotation.Nullable;

public class DynamicRenderer implements Renderer<Object> {

    private final RendererFactoryContext factoryContext;

    public DynamicRenderer(RendererFactoryContext factoryContext) {
        this.factoryContext = factoryContext;
    }

    @Nullable
    @Override
    public String render(@Nullable Object instanceObject) {
        if (instanceObject != null) {
            return this.factoryContext.render(instanceObject);
        } else {
            return null;
        }
    }
}
