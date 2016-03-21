package org.slieb.soy.configuration.soydata;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.slieb.soy.factories.SoyConverterFactory;
import org.slieb.soy.factories.soydata.LazyClassToSoyMapDataConverterFactory;
import org.slieb.soy.factories.soydata.LazySoyListDataConverterFactory;
import org.slieb.soy.factories.soydata.LazySoyMapDataConverterFactory;

import static com.google.inject.multibindings.Multibinder.newSetBinder;


public class LazyClassSoyFactoriesModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<SoyConverterFactory> binder = newSetBinder(binder(), SoyConverterFactory.class);
        binder.addBinding().to(LazySoyListDataConverterFactory.class);
        binder.addBinding().to(LazySoyMapDataConverterFactory.class);
        binder.addBinding().to(LazyClassToSoyMapDataConverterFactory.class);
    }
}
