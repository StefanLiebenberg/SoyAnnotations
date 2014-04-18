package slieb.soy;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.template.soy.tofu.SoyTofu;
import slieb.soy.configuration.DefaultFactoryHelperModule;
import slieb.soy.configuration.jsondata.BasicJsonDataConvertersFactoryModule;
import slieb.soy.configuration.jsondata.MetaJsonDataConvertersFactoryModule;
import slieb.soy.configuration.meta.MetaFactoriesModule;
import slieb.soy.configuration.rendering.DelegateTemplates;
import slieb.soy.configuration.rendering.RenderingFactoriesModule;
import slieb.soy.configuration.rendering.SoyTofuModule;
import slieb.soy.configuration.soydata.BasicSoyDataConvertersFactoryModule;
import slieb.soy.configuration.soydata.LazyClassSoyFactoriesModule;
import slieb.soy.configuration.soydata.MetaClassBindingsModule;
import slieb.soy.context.JsonDataFactoryContext;
import slieb.soy.context.RendererFactoryContext;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.factories.rendering.Renderer;

import java.util.Set;

import static com.google.inject.Guice.createInjector;

public class Loader {

    public static Injector getBasicSoyInjector() {
        return Guice.createInjector(new DefaultFactoryHelperModule(),
                new BasicSoyDataConvertersFactoryModule());
    }

    public static Injector getBasicJsonInjector() {
        return createInjector(new DefaultFactoryHelperModule(), new BasicJsonDataConvertersFactoryModule());
    }

    public static SoyDataFactoryContext getBasicSoyDataContext() {
        return getBasicSoyInjector().getInstance(SoyDataFactoryContext.class);
    }

    public static JsonDataFactoryContext getBasicJsonDataContext() {
        return getBasicJsonInjector().getInstance(JsonDataFactoryContext.class);
    }

    public static Injector getFullSoyInjector() {
        return createInjector(new DefaultFactoryHelperModule(), new BasicSoyDataConvertersFactoryModule(), new MetaFactoriesModule(), new MetaClassBindingsModule());
    }

    public static SoyDataFactoryContext getFullSoyDataContext() {
        return getFullSoyInjector().getInstance(SoyDataFactoryContext.class);
    }

    public static Injector getFullJsonInjector() {
        return createInjector(
                new DefaultFactoryHelperModule(),
                new BasicJsonDataConvertersFactoryModule(),
                new MetaFactoriesModule(),
                new MetaJsonDataConvertersFactoryModule());
    }

    public static JsonDataFactoryContext getFullJsonDataContext() {
        return getFullJsonInjector().getInstance(JsonDataFactoryContext.class);
    }

    public static Injector getLazySoyDataInjector() {
        return createInjector(
                new DefaultFactoryHelperModule(),
                new BasicSoyDataConvertersFactoryModule(),
                new MetaFactoriesModule(),
                new MetaClassBindingsModule(),
                new LazyClassSoyFactoriesModule());
    }

    public static SoyDataFactoryContext getLazySoyDataContext() {
        return getLazySoyDataInjector().getInstance(SoyDataFactoryContext.class);
    }

    public static RendererFactoryContext getRendererContext(SoyTofu soyTofu, Set<String> delegatePackages) {
        return createInjector(
                new DefaultFactoryHelperModule(),
                new DelegateTemplates(delegatePackages),
                new RenderingFactoriesModule(),
                new SoyTofuModule(soyTofu),
                new BasicSoyDataConvertersFactoryModule(),
                new MetaFactoriesModule(),
                new MetaClassBindingsModule())
                .getInstance(RendererFactoryContext.class);
    }

    public static Sauce getContext(SoyTofu soyTofu, Set<String> delegatePackages) {
        return createInjector(
                new DefaultFactoryHelperModule(),
                new DelegateTemplates(delegatePackages),
                new RenderingFactoriesModule(),
                new SoyTofuModule(soyTofu),
                new BasicSoyDataConvertersFactoryModule(),
                new BasicJsonDataConvertersFactoryModule(),
                new MetaFactoriesModule(),
                new MetaClassBindingsModule(),
                new MetaJsonDataConvertersFactoryModule()
        ).getInstance(Sauce.class);
    }

    public static Sauce getContext() {
        return getContext(null, null);
    }

    public static Renderer<Object> getRenderer(SoyTofu tofu, Set<String> delegatePackages) {
        return getRendererContext(tofu, delegatePackages);
    }


}
