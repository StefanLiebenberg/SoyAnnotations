package org.slieb.soy.renderers;


import com.google.inject.Singleton;
import org.slieb.soy.factories.rendering.Renderer;

import javax.annotation.Nullable;

@Singleton
public class NativeRenderer implements Renderer<Object> {

    @Nullable
    @Override
    public String render(@Nullable Object instanceObject) {
        return String.valueOf(instanceObject);
    }
}