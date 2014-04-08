package slieb.soy.factories.rendering;

import slieb.soy.annotations.CustomRenderer;
import slieb.soy.factories.internal.AbstractRendererFactory;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;


public class CustomRendererFactory extends AbstractRendererFactory {

    private final FactoryHelper factoryHelper;

    public CustomRendererFactory(RendererFactoryContext factoryContext, FactoryHelper factoryHelper) {
        super(factoryContext);
        this.factoryHelper = factoryHelper;
    }


    @Nonnull
    @Override
    public Renderer<Object> create(@Nonnull Class<?> classObject) {
        Class<? extends Renderer<Object>> rendererClass = classObject.getAnnotation(CustomRenderer.class).value();


        Constructor<? extends Renderer<Object>> rendererConstructor;
        try {
            rendererConstructor = rendererClass.getConstructor(FactoryHelper.class, RendererFactoryContext.class);
            if (rendererConstructor != null) {
                return rendererConstructor.newInstance(factoryHelper, factoryContext);
            }
        } catch (Exception exception) {
        }

        try {
            rendererConstructor = rendererClass.getConstructor(RendererFactoryContext.class);
            if (rendererConstructor != null) {
                return rendererConstructor.newInstance(factoryContext);
            }
        } catch (ReflectiveOperationException exception) {
        }

        try {
            return rendererClass.newInstance();
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return classObject.isAnnotationPresent(CustomRenderer.class);
    }
}
