package org.slieb.soy.configuration.jsondata;


import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.slieb.soy.factories.JsonConverterFactory;
import org.slieb.soy.factories.jsondata.MetaInformationToJsonDataConverterFactory;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

public class MetaJsonDataConvertersFactoryModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<JsonConverterFactory> binder = newSetBinder(binder(), JsonConverterFactory.class);
        binder.addBinding().to(MetaInformationToJsonDataConverterFactory.class);
    }
}
