package org.slieb.soy.factories.rendering;

import com.google.inject.Singleton;
import org.slieb.soy.annotations.CustomRenderer;
import org.slieb.soy.context.RendererFactoryContext;
import org.slieb.soy.factories.RendererFactory;

import javax.annotation.Nonnull;

@Singleton
public class CustomRendererFactory implements RendererFactory {

    private Class<? extends Renderer<Object>> getRendererClass(Class<?> classObject) {
        return classObject.getAnnotation(CustomRenderer.class).value();
    }

    private Renderer<Object> getRendererInstance(Class<? extends Renderer<Object>> rendererClass,
                                                 RendererFactoryContext context) {
        try {
            Renderer<Object> renderer = rendererClass.newInstance();
            if (renderer instanceof RendererFactoryContextAware) {
                ((RendererFactoryContextAware) renderer).setRendererFactoryContext(context);
            }
            return renderer;
        } catch (ReflectiveOperationException r) {
            throw new RuntimeException(r);
        }
    }

    @Override
    public Renderer<Object> create(Class<?> classObject, RendererFactoryContext context) {
        return getRendererInstance(getRendererClass(classObject), context);
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return classObject.isAnnotationPresent(CustomRenderer.class);
    }
}
