package slieb.soy.factories.internal;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import slieb.soy.factories.rendering.Renderer;

public interface FactoryProvider {
    public Renderer<Object> getRenderer();

    public Converter<Object, ? extends SoyData> getSoyDataConverter();

    public Converter<Object, ?> getDataConverter();
}
