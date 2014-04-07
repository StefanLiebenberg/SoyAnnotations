package slieb.soy.factories.internal;


import javax.annotation.Nonnull;

public interface Factory<A> {

    @Nonnull
    public A create(@Nonnull Class<?> classObject);

    @Nonnull
    public Boolean canCreate(@Nonnull Class<?> classObject);

}
