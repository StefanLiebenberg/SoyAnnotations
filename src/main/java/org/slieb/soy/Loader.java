package org.slieb.soy;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.template.soy.tofu.SoyTofu;
import org.slieb.soy.configuration.DefaultFactoryHelperModule;
import org.slieb.soy.configuration.SingletonModule;
import org.slieb.soy.configuration.jsondata.BasicJsonDataConvertersFactoryModule;
import org.slieb.soy.configuration.jsondata.MetaJsonDataConvertersFactoryModule;
import org.slieb.soy.configuration.meta.MetaFactoriesModule;
import org.slieb.soy.configuration.rendering.DelegateTemplates;
import org.slieb.soy.configuration.rendering.RenderingFactoriesModule;
import org.slieb.soy.configuration.rendering.SoyTofuModule;
import org.slieb.soy.configuration.soydata.BasicSoyDataConvertersFactoryModule;
import org.slieb.soy.configuration.soydata.LazyClassSoyFactoriesModule;
import org.slieb.soy.configuration.soydata.MetaClassBindingsModule;
import org.slieb.soy.context.JsonDataFactoryContext;
import org.slieb.soy.context.SoyDataFactoryContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

import static com.google.inject.Guice.createInjector;

@SuppressWarnings("WeakerAccess")
public class Loader {

    private static Injector basicInjector, fullInjector, lazyInjector;

    /**
     * @return A injector that enables basic types to be
     * converter to soy and json.
     */
    @Nonnull
    public static Injector getBasicInjector() {
        if (basicInjector == null) {
            basicInjector = createInjector(getBasicModules());
        }
        return basicInjector;
    }

    @Nonnull
    public static Module[] getBasicModules() {
        return new Module[]{new DefaultFactoryHelperModule(),
                new SingletonModule(SoyDataFactoryContext.class),
                new SingletonModule(JsonDataFactoryContext.class),
                new BasicSoyDataConvertersFactoryModule(),
                new BasicJsonDataConvertersFactoryModule()};
    }

    /**
     * @return A injector that enables basic types to be
     * converter to soy and json. As well as type annotations.
     */
    @Nonnull
    public static Injector getFullInjector() {
        if (fullInjector == null) {
            fullInjector = createInjector(getFullModules());
        }
        return fullInjector;
    }

    @Nonnull
    public static Module[] getFullModules() {
        return new Module[]{new DefaultFactoryHelperModule(),
                new BasicSoyDataConvertersFactoryModule(),
                new BasicJsonDataConvertersFactoryModule(),
                new MetaFactoriesModule(),
                new MetaClassBindingsModule(),
                new MetaJsonDataConvertersFactoryModule(),
                new SingletonModule(SoyDataFactoryContext.class),
                new SingletonModule(JsonDataFactoryContext.class)};
    }

    /**
     * @param soyTofu          The tofu object.
     * @param delegatePackages A set of delegate packages.
     * @return A injector that enables basic types to be
     * converter to soy and json. As well as type annotations.
     */
    @Nonnull
    public static Injector getFullInjector(@Nonnull SoyTofu soyTofu,
                                           @Nullable Set<String> delegatePackages) {
        return getFullInjector()
                .createChildInjector(getPackagesModules(soyTofu, delegatePackages));
    }

    public static Module[] getPackagesModules(final @Nonnull SoyTofu soyTofu,
                                              final @Nullable Set<String> delegatePackages) {
        return new Module[]{
                new RenderingFactoriesModule(),
                new DelegateTemplates(delegatePackages),
                new SoyTofuModule(soyTofu)};
    }

    /**
     * @return A injector that enables basic types to be
     * converter to soy and json. As well as type annotations.
     */
    @Nonnull
    public static Injector getLazyInjector() {
        if (lazyInjector == null) {
            lazyInjector = createInjector(
                    getLazyModules());
        }
        return lazyInjector;
    }

    public static Module[] getLazyModules() {
        return new Module[]{new DefaultFactoryHelperModule(),
                new BasicSoyDataConvertersFactoryModule(),
                new BasicJsonDataConvertersFactoryModule(),
                new MetaFactoriesModule(),
                new MetaClassBindingsModule(),
                new LazyClassSoyFactoriesModule(),
                new SingletonModule(SoyDataFactoryContext.class),
                new SingletonModule(JsonDataFactoryContext.class)};
    }

    /**
     * @param soyTofu          The tofu object.
     * @param delegatePackages set of delegate packages.
     * @return A injector that enables basic types to be
     * converter to soy and json. As well as type annotations.
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
