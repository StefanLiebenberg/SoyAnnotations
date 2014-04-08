package slieb.soy.factories.internal;


import slieb.soy.factories.rendering.Renderer;
import slieb.soy.factories.rendering.RendererFactoryContext;
import slieb.soy.renderers.DynamicRenderer;

import javax.annotation.Nonnull;

public abstract class AbstractRendererFactory implements Factory<Renderer<Object>> {

    protected final RendererFactoryContext factoryContext;


    protected AbstractRendererFactory(RendererFactoryContext factoryContext) {
        this.factoryContext = factoryContext;
    }

    @Nonnull
    @Override
    public abstract Renderer<Object> create(@Nonnull Class<?> classObject);

    @Nonnull
    @Override
    public abstract Boolean canCreate(@Nonnull Class<?> classObject);

    public Renderer<Object> getDynamic() {
        return new DynamicRenderer(factoryContext);
    }
}
