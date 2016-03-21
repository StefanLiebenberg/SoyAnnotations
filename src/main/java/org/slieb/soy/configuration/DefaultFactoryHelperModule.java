package org.slieb.soy.configuration;


import com.google.inject.AbstractModule;
import org.slieb.soy.helpers.DefaultFactoryHelper;
import org.slieb.soy.helpers.FactoryHelper;

public class DefaultFactoryHelperModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(FactoryHelper.class)
                .to(DefaultFactoryHelper.class)
                .asEagerSingleton();

    }
}
