package slieb.soy.configuration.rendering;


import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;

import java.util.Set;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

public class DelegateTemplates extends AbstractModule {

    public final Set<String> stringSet;

    public DelegateTemplates(Set<String> stringSet) {
        this.stringSet = stringSet;
    }

    @Override
    protected void configure() {
        Multibinder<String> binder = newSetBinder(binder(), String.class, Names.named("DelegatePackages"));
        if (stringSet != null) {
            for (String string : stringSet) {
                binder.addBinding().toInstance(string);
            }
        }
    }
}
