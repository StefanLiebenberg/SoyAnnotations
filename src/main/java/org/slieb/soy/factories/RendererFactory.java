package org.slieb.soy.factories;

import org.slieb.soy.context.RendererFactoryContext;
import org.slieb.soy.factories.rendering.Renderer;

public interface RendererFactory {

    Boolean canCreate(Class<?> classObject);

    Renderer<Object> create(Class<?> classObject,
                            RendererFactoryContext context);
}
