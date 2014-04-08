package slieb.soy.factories.rendering;

import com.google.common.collect.ImmutableList;
import com.google.template.soy.tofu.SoyTofu;
import slieb.soy.factories.internal.AbstractFactoryContext;
import slieb.soy.factories.internal.Factory;
import slieb.soy.factories.soydata.SoyDataConverterFactoryContext;
import slieb.soy.helpers.DefaultFactoryHelper;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;


public class RendererFactoryContext extends AbstractFactoryContext<Renderer<Object>> {

    private final FactoryHelper factoryHelper;

    private final SoyDataConverterFactoryContext soyDataConverterFactoryContext;

    private final SoyTofu soyTofu;

    private final List<Factory<Renderer<Object>>> customFactories;

    public RendererFactoryContext(FactoryHelper factoryHelper, SoyDataConverterFactoryContext soyDataConverterFactoryContext, SoyTofu soyTofu) {
        this.customFactories = new ArrayList<>();
        this.factoryHelper = factoryHelper;
        this.soyDataConverterFactoryContext = soyDataConverterFactoryContext;
        this.soyTofu = soyTofu;
    }


    public RendererFactoryContext(FactoryHelper factoryHelper, SoyTofu soyTofu) {
        this(factoryHelper, new SoyDataConverterFactoryContext(factoryHelper), soyTofu);
    }

    public RendererFactoryContext(SoyTofu soyTofu) {
        this(DefaultFactoryHelper.INSTANCE, soyTofu);
    }

    @Override
    public void addCustomFactory(@Nonnull Factory<Renderer<Object>> factory) {
        checkState(factories == null);
        if (!customFactories.contains(factory)) {
            this.customFactories.add(factory);
        }
    }

    @Nonnull
    public Factory<Renderer<Object>> getNativeRendererFactory() {
        return new NativeRendererFactory(this);
    }

    @Nonnull
    public Factory<Renderer<Object>> getClassRendererFactory() {
        return new ClassRendererFactory(this, factoryHelper, soyDataConverterFactoryContext.getClassConverterFactory());
    }

    public Factory<Renderer<Object>> getCustomRendererFactory() {
        return new CustomRendererFactory(this, factoryHelper);
    }

    @Nonnull
    @Override
    public ImmutableList<Factory<Renderer<Object>>> getFactoriesInternal() {
        return new ImmutableList.Builder<Factory<Renderer<Object>>>()
                .add(getCustomRendererFactory())
                .addAll(customFactories)
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
