package slieb.soy.factories.rendering;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyMapData;
import slieb.soy.factories.internal.AbstractRendererFactory;
import slieb.soy.factories.soydata.SoyDataClassConverterFactory;
import slieb.soy.helpers.FactoryHelper;
import slieb.soy.renderers.ClassRenderer;
import slieb.soy.renderers.DataRenderer;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;

public class ClassRendererFactory extends AbstractRendererFactory {

    private final FactoryHelper factoryHelper;

    private final SoyDataClassConverterFactory soyDataClassConverterFactory;

    public ClassRendererFactory(RendererFactoryContext factoryContext, FactoryHelper factoryHelper, SoyDataClassConverterFactory soyDataClassConverterFactory) {
        super(factoryContext);
        this.factoryHelper = factoryHelper;
        this.soyDataClassConverterFactory = soyDataClassConverterFactory;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return factoryHelper.hasTemplate(classObject);
    }

    @Nonnull
    @Override
    public Renderer<Object> create(@Nonnull Class<?> classObject) {
        checkArgument(soyDataClassConverterFactory.canCreate(classObject));
        final Converter<Object, ? extends SoyMapData> dataConverter = soyDataClassConverterFactory.create(classObject);
        final Renderer<SoyMapData> dataRenderer = new DataRenderer(factoryContext.getSoyTofu(), factoryHelper.getTemplateName(classObject), null);
        return new ClassRenderer(dataConverter, dataRenderer);
    }
}
