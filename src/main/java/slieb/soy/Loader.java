package slieb.soy;


import com.google.inject.Injector;
import com.google.template.soy.tofu.SoyTofu;
import slieb.soy.configuration.DefaultFactoryHelperModule;
import slieb.soy.configuration.SingletonModule;
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
import slieb.soy.context.SoyDataFactoryContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

import static com.google.inject.Guice.createInjector;

public class Loader {

    private static Injector basicInjector, fullInjector,
            fullInjectorWithRendering, lazyInjector,
            lazyInjectorWithRendering;

    /**
     * @return A injector that enables basic types to be
     *         converter to soy and json.
     */
    @Nonnull
    public static Injector getBasicInjector() {
        if (basicInjector == null) {
            basicInjector = createInjector(
                    new DefaultFactoryHelperModule(),
                    new SingletonModule(SoyDataFactoryContext.class),
                    new SingletonModule(JsonDataFactoryContext.class),
                    new BasicSoyDataConvertersFactoryModule(),
                    new BasicJsonDataConvertersFactoryModule());
        }
        return basicInjector;
    }


    /**
     * @return A injector that enables basic types to be
     *         converter to soy and json. As well as type annotations.
     */
    @Nonnull
    public static Injector getFullInjector() {
        if (fullInjector == null) {
            fullInjector = createInjector(
                    new DefaultFactoryHelperModule(),
                    new BasicSoyDataConvertersFactoryModule(),
                    new BasicJsonDataConvertersFactoryModule(),
                    new MetaFactoriesModule(),
                    new MetaClassBindingsModule(),
                    new MetaJsonDataConvertersFactoryModule(),
                    new SingletonModule(SoyDataFactoryContext.class),
                    new SingletonModule(JsonDataFactoryContext.class));
        }
        return fullInjector;
    }


    /**
     * @return A injector that enables basic types to be
     *         converter to soy and json. As well as type annotations.
     */
    @Nonnull
    public static Injector getFullInjector(@Nonnull SoyTofu soyTofu,
                                           @Nullable Set<String> delegatePackages) {
        return getFullInjector()
                .createChildInjector(
                        new RenderingFactoriesModule(),
                        new DelegateTemplates(delegatePackages),
                        new SoyTofuModule(soyTofu));

    }

    /**
     * @return A injector that enables basic types to be
     *         converter to soy and json. As well as type annotations.
     */
    @Nonnull
    public static Injector getLazyInjector() {
        if (lazyInjector == null) {
            lazyInjector = createInjector(
                    new DefaultFactoryHelperModule(),
                    new BasicSoyDataConvertersFactoryModule(),
                    new BasicJsonDataConvertersFactoryModule(),
                    new MetaFactoriesModule(),
                    new MetaClassBindingsModule(),
                    new LazyClassSoyFactoriesModule(),
                    new SingletonModule(SoyDataFactoryContext.class),
                    new SingletonModule(JsonDataFactoryContext.class));
        }
        return lazyInjector;
    }

    /**
     * @return A injector that enables basic types to be
     *         converter to soy and json. As well as type annotations.
     */
    @Nonnull
    public static Injector getLazyInjector(@Nonnull SoyTofu soyTofu,
                                           @Nullable Set<String> delegatePackages) {
        return getLazyInjector()
                .createChildInjector(
                        new RenderingFactoriesModule(),
                        new DelegateTemplates(delegatePackages),
                        new SoyTofuModule(soyTofu));

    }

}
