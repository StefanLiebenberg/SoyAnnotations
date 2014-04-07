package slieb.soy.model;


import javax.annotation.Nonnull;

public interface DynamicResult<A> {
    @Nonnull
    public A result();
}
