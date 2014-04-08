package slieb.soy.annotations;


import ch.lambdaj.function.convert.Converter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CustomJsonConverter {
    Class<Converter<Object, ?>> value();
}
