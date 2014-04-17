package slieb.soy.configuration.soydata;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import slieb.soy.factories.SoyConverterFactory;
import slieb.soy.factories.soydata.LazyClassToSoyMapDataConverterFactory;
import slieb.soy.factories.soydata.LazySoyListDataConverterFactory;
import slieb.soy.factories.soydata.LazySoyMapDataConverterFactory;

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
