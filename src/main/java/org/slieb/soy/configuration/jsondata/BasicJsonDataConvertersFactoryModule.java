package org.slieb.soy.configuration.jsondata;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.slieb.soy.factories.JsonConverterFactory;
import org.slieb.soy.factories.jsondata.ListJsonConverterFactory;
import org.slieb.soy.factories.jsondata.MapJsonConverterFactory;
import org.slieb.soy.factories.jsondata.NativeJsonConverterFactory;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

public class BasicJsonDataConvertersFactoryModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<JsonConverterFactory> binder = newSetBinder(binder(), JsonConverterFactory.class);
        binder.addBinding().to(NativeJsonConverterFactory.class);
        binder.addBinding().to(MapJsonConverterFactory.class);
        binder.addBinding().to(ListJsonConverterFactory.class);
    }
}
