package slieb.soy.configuration.rendering;


import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.template.soy.tofu.SoyTofu;

import javax.annotation.Nonnull;

public class SoyTofuModule extends AbstractModule {

    private final SoyTofu soyTofu;

    public SoyTofuModule(@Nonnull SoyTofu soyTofu) {
        this.soyTofu = soyTofu;
    }

    @Override
    protected void configure() {
        bind(SoyTofu.class).toInstance(soyTofu);
    }


}
