package slieb.soy.factories.rendering;

import com.google.common.collect.ImmutableList;
import com.google.template.soy.tofu.SoyTofu;
import slieb.soy.factories.internal.AbstractFactoryContext;
import slieb.soy.factories.internal.Factory;
import slieb.soy.factories.soydata.SoyDataConverterFactoryContext;
import slieb.soy.helpers.DefaultFactoryHelper;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;


public class RendererFactoryContext extends AbstractFactoryContext<Renderer<Object>> {

    private final FactoryHelper factoryHelper;

    private final SoyDataConverterFactoryContext soyDataConverterFactoryContext;

    private final SoyTofu soyTofu;

    public RendererFactoryContext(FactoryHelper factoryHelper, SoyDataConverterFactoryContext soyDataConverterFactoryContext, SoyTofu soyTofu) {
        this.factoryHelper = factoryHelper;
        this.soyDataConverterFactoryContext = soyDataConverterFactoryContext;
        this.soyTofu = soyTofu;
    }

    public RendererFactoryContext(FactoryHelper factoryHelper, SoyTofu soyTofu) {
        this(factoryHelper, new SoyDataConverterFactoryContext(factoryHelper), soyTofu);
    }

    public RendererFactoryContext(SoyTofu soyTofu) {
        this(new DefaultFactoryHelper(), soyTofu);
    }


    @Nonnull
    public Factory<Renderer<Object>> getNativeRendererFactory() {
        return new NativeRendererFactory(this);
    }

    @Nonnull
    public Factory<Renderer<Object>> getClassRendererFactory() {
        return new ClassRendererFactory(this, factoryHelper, soyDataConverterFactoryContext.getClassConverterFactory());
    }

    @Nonnull
    @Override
    public ImmutableList<Factory<Renderer<Object>>> getFactories() {
        return new ImmutableList.Builder<Factory<Renderer<Object>>>()
                .add(getClassRendererFactory())
                .add(getNativeRendererFactory())
                .build();
    }


    public String render(Object objectInstance) {
        return createFromInstance(objectInstance).render(objectInstance);
    }

    public SoyTofu getSoyTofu() {
        return soyTofu;
    }
}
