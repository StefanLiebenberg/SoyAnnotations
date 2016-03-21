package org.slieb.soy.helpers;


import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface FactoryHelper {

    @Nonnull
    public Boolean isFactoryClass(@Nonnull Class<?> classObject);

    @Nonnull
    public Boolean isDynamicFactoryClass(@Nonnull Class<?> classObject);

    @Nonnull
    public Boolean isFactoryMethod(@Nonnull Method method);

    @Nonnull
    public Boolean isDynamicFactoryMethod(@Nonnull Method method);

    @Nonnull
    public Boolean isFactoryField(@Nonnull Field field);

    @Nonnull
    public Boolean isDynamicFactoryField(@Nonnull Field field);

    @Nonnull
    public Boolean hasTemplate(@Nonnull Class<?> classObject);

    @Nonnull
    public String getTemplateName(@Nonnull Class<?> classObject);

    @Nonnull
    public String getFieldKey(@Nonnull Field field);

    @Nonnull
    public String getMethodKey(@Nonnull Method field);

    @Nonnull
    public Boolean useOriginalToString(@Nonnull Class<?> classObject);

}

