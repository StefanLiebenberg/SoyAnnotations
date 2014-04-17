package slieb.soy.factories;


import javax.annotation.Nonnull;

public interface Factory<A> {

    @Nonnull
    public A create(@Nonnull Class<?> classObject);

    @Nonnull
    public Boolean canCreate(@Nonnull Class<?> classObject);

}
