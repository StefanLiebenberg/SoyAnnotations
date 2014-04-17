package slieb.soy.factories;


import slieb.soy.context.RendererFactoryContext;
import slieb.soy.factories.rendering.Renderer;

public interface RendererFactory {

    public Boolean canCreate(Class<?> classObject);

    public Renderer<Object> create(Class<?> classObject, RendererFactoryContext context);
}
