package slieb.soy.renderers;


import slieb.soy.factories.rendering.Renderer;

import javax.annotation.Nullable;

public class ObjectRenderer implements Renderer<Object> {

    public static final ObjectRenderer INSTANCE = new ObjectRenderer();

    private ObjectRenderer() {}


    @Nullable
    @Override
    public String render(@Nullable Object instanceObject) {
        return String.valueOf(instanceObject);
    }
}