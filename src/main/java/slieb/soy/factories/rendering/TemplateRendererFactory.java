package slieb.soy.factories.rendering;


import ch.lambdaj.function.convert.Converter;
import com.google.inject.Inject;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.tofu.SoyTofu;
import slieb.soy.context.RendererFactoryContext;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.factories.RendererFactory;
import slieb.soy.helpers.FactoryHelper;
import slieb.soy.renderers.ClassRenderer;
import slieb.soy.renderers.DataRenderer;

import javax.annotation.Nullable;

public class TemplateRendererFactory implements RendererFactory {

    private final FactoryHelper factoryHelper;

    private final SoyDataFactoryContext soyDataFactoryContext;

    private final SoyTofu soyTofu;

    @Inject
    public TemplateRendererFactory(FactoryHelper factoryHelper, SoyDataFactoryContext soyDataFactoryContext, @Nullable SoyTofu soyTofu) {
        this.factoryHelper = factoryHelper;
        this.soyDataFactoryContext = soyDataFactoryContext;
        this.soyTofu = soyTofu;
    }

    @Override
    public Boolean canCreate(Class<?> classObject) {
        return factoryHelper.hasTemplate(classObject);
    }

    @Override
    public Renderer<Object> create(Class<?> classObject, RendererFactoryContext context) {
        String templateName = factoryHelper.getTemplateName(classObject);
        Converter<Object, ? extends SoyData> dataConverter = soyDataFactoryContext.create(classObject);
        Renderer<SoyData> dataRenderer = new DataRenderer(soyTofu, templateName, null);
        return new ClassRenderer(dataConverter, dataRenderer);
    }
}
