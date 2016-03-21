package org.slieb.soy.configuration.meta;


import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.slieb.soy.factories.MetaConverterFactory;
import org.slieb.soy.meta.MetaConverterClassConverter;
import org.slieb.soy.meta.MetaCustomClassConverterFactory;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

public class MetaFactoriesModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<MetaConverterFactory> binder = newSetBinder(binder(), MetaConverterFactory.class);
        binder.addBinding().to(MetaConverterClassConverter.class);
        binder.addBinding().to(MetaCustomClassConverterFactory.class);
    }
}
