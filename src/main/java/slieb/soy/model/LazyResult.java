package slieb.soy.model;


import javax.annotation.Nonnull;

public interface LazyResult<A> {
    @Nonnull
    public A result();

}
