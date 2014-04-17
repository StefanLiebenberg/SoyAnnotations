package slieb.soy.configuration;


import com.google.inject.AbstractModule;
import slieb.soy.helpers.DefaultFactoryHelper;
import slieb.soy.helpers.FactoryHelper;

public class DefaultFactoryHelperModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(FactoryHelper.class)
                .to(DefaultFactoryHelper.class)
                .asEagerSingleton();

    }
}
