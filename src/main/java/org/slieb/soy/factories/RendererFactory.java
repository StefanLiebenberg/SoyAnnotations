package org.slieb.soy.factories;


import org.slieb.soy.context.RendererFactoryContext;
import org.slieb.soy.factories.rendering.Renderer;

public interface RendererFactory {

    public Boolean canCreate(Class<?> classObject);

    public Renderer<Object> create(Class<?> classObject, RendererFactoryContext context);
}
