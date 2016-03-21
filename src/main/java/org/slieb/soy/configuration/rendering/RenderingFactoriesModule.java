package org.slieb.soy.configuration.rendering;


import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.slieb.soy.factories.RendererFactory;
import org.slieb.soy.factories.rendering.CustomRendererFactory;
import org.slieb.soy.factories.rendering.NativeRendererFactory;
import org.slieb.soy.factories.rendering.TemplateRendererFactory;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

public class RenderingFactoriesModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<RendererFactory> binder = newSetBinder(binder(), RendererFactory.class);
        binder.addBinding().to(NativeRendererFactory.class);
        binder.addBinding().to(TemplateRendererFactory.class);
        binder.addBinding().to(CustomRendererFactory.class);
    }
}
