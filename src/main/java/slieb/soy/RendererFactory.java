package slieb.soy;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.tofu.SoyTofu;
import slieb.soy.annotations.SoyTemplate;
import sun.plugin.dom.exception.InvalidStateException;

public class RendererFactory {

    private final static String SOYTEMPLATE_NOT_FOUND =
            "@SoyTemplate annotation is not found on %s";

    private final SoyDataConverterFactory soyDataConverterFactory;

    private final SoyTofu soyTofu;

    public RendererFactory(SoyDataConverterFactory soyDataConverterFactory,
                           SoyTofu soyTofu) {
        this.soyDataConverterFactory = soyDataConverterFactory;
        this.soyTofu = soyTofu;
    }

    private Converter<Object, SoyData> getConverter(Class classObject) {
        return soyDataConverterFactory.create(classObject);
    }

    private SoyTofu.Renderer getRenderer(Class<? extends Object> classObject) {
        if (classObject.isAnnotationPresent(SoyTemplate.class)) {
            SoyTemplate annotation =
                    classObject.getAnnotation(SoyTemplate.class);
            String templateName = annotation.value();
            return soyTofu.newRenderer(templateName);
        } else {
            String name = classObject.getName();
            String message = String.format(SOYTEMPLATE_NOT_FOUND, name);
            throw new InvalidStateException(message);
        }
    }

    public Renderer create(Class<? extends Object> classObject) {
        Converter<Object, SoyData> converter = getConverter(classObject);
        SoyTofu.Renderer renderer = getRenderer(classObject);
        return new Renderer(renderer, converter);
    }
}
