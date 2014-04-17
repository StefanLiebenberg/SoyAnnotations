package slieb.soy.factories.rendering;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import slieb.soy.context.RendererFactoryContext;
import slieb.soy.factories.RendererFactory;
import slieb.soy.renderers.NativeRenderer;

import javax.annotation.Nonnull;

@Singleton
public class NativeRendererFactory implements RendererFactory {

    private final NativeRenderer nativeRenderer;

    @Inject
    public NativeRendererFactory(NativeRenderer nativeRenderer) {
        this.nativeRenderer = nativeRenderer;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Boolean.TRUE;
    }

    @Override
    public Renderer<Object> create(Class<?> classObject, RendererFactoryContext context) {
        return nativeRenderer;
    }
}
