package org.slieb.soy.configuration.soydata;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.slieb.soy.factories.SoyConverterFactory;
import org.slieb.soy.factories.soydata.MetaInformationSoyDataConverterFactory;

import static com.google.inject.multibindings.Multibinder.newSetBinder;


public class MetaClassBindingsModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<SoyConverterFactory> binder = newSetBinder(binder(), SoyConverterFactory.class);
        binder.addBinding().to(MetaInformationSoyDataConverterFactory.class);
    }
}
