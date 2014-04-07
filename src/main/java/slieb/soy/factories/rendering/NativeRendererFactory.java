package slieb.soy.factories.rendering;


import slieb.soy.factories.internal.AbstractRendererFactory;
import slieb.soy.renderers.ObjectRenderer;

import javax.annotation.Nonnull;

public class NativeRendererFactory extends AbstractRendererFactory {

    public NativeRendererFactory(RendererFactoryContext factoryContext) {
        super(factoryContext);
    }

    @Nonnull
    @Override
    public Renderer<Object> create(@Nonnull Class<?> classObject) {
        return ObjectRenderer.INSTANCE;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Boolean.TRUE;
    }
}
