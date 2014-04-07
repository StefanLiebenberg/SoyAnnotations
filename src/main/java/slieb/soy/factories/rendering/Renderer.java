package slieb.soy.factories.rendering;

import javax.annotation.Nullable;

public interface Renderer<A> {
    @Nullable
    public String render(@Nullable A instanceObject);
}
