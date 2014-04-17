package slieb.soy.configuration.soydata;


import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import slieb.soy.factories.SoyConverterFactory;
import slieb.soy.factories.soydata.*;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

public class BasicSoyDataConvertersFactoryModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<SoyConverterFactory> binder = newSetBinder(binder(), SoyConverterFactory.class);
        binder.addBinding().to(SoyMapDataConverterFactory.class);
        binder.addBinding().to(SoyListDataConverterFactory.class);
        binder.addBinding().to(LongDataConverterFactory.class);
        binder.addBinding().to(BooleanDataConverterFactory.class);
        binder.addBinding().to(FloatDataConverterFactory.class);
        binder.addBinding().to(IntegerDataConverterFactory.class);
        binder.addBinding().to(StringDataConverterFactory.class);
        binder.addBinding().to(SoyDataConverterFactory.class);
    }
}
