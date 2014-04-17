package slieb.soy.configuration.meta;


import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import slieb.soy.factories.MetaConverterFactory;
import slieb.soy.meta.MetaConverterClassConverter;
import slieb.soy.meta.MetaCustomClassConverterFactory;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

public class MetaFactoriesModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<MetaConverterFactory> binder = newSetBinder(binder(), MetaConverterFactory.class);
        binder.addBinding().to(MetaConverterClassConverter.class);
        binder.addBinding().to(MetaCustomClassConverterFactory.class);
    }
}
