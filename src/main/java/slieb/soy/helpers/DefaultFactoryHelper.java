package slieb.soy.helpers;


import slieb.soy.annotations.Soy;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static com.google.common.base.Preconditions.checkState;

public class DefaultFactoryHelper implements FactoryHelper {

    public static DefaultFactoryHelper INSTANCE = new DefaultFactoryHelper();

    private DefaultFactoryHelper() {}

    @Override
    @Nonnull
    public Boolean isFactoryClass(@Nonnull Class<?> classObject) {
        return classObject.isAnnotationPresent(Soy.class);
    }

    @Override
    @Nonnull
    public Boolean isFactoryMethod(@Nonnull Method method) {
        return method.isAnnotationPresent(Soy.Method.class);
    }

    @Override
    @Nonnull
    public Boolean isFactoryField(@Nonnull Field field) {
        return field.isAnnotationPresent(Soy.Field.class);
    }

    @Override
    @Nonnull
    public Boolean isDynamicFactoryField(@Nonnull Field field) {
        return field.isAnnotationPresent(Soy.Dynamic.class);
    }

    @Override
    @Nonnull
    public Boolean isDynamicFactoryMethod(@Nonnull Method method) {
        return method.isAnnotationPresent(Soy.Dynamic.class);
    }

    @Override
    @Nonnull
    public Boolean isDynamicFactoryClass(@Nonnull Class<?> classObject) {
        return classObject.isAnnotationPresent(Soy.Dynamic.class);
    }

    @Override
    @Nonnull
    public Boolean hasTemplate(@Nonnull Class<?> classObject) {
        return classObject.isAnnotationPresent(Soy.Template.class);
    }

    @Override
    @Nonnull
    public String getTemplateName(@Nonnull Class<?> classObject) {
        checkState(hasTemplate(classObject));
        return classObject.getAnnotation(Soy.Template.class).value();
    }

    @Override
    @Nonnull
    public String getFieldKey(@Nonnull Field field) {
        checkState(isFactoryField(field));
        String fieldValue = field.getAnnotation(Soy.Field.class).value();
        return fieldValue.isEmpty() ? field.getName() : fieldValue;
    }

    @Override
    @Nonnull
    public String getMethodKey(@Nonnull Method field) {
        return field.getAnnotation(Soy.Method.class).value();
    }


}
