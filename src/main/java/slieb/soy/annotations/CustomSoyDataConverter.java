package slieb.soy.annotations;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CustomSoyDataConverter {
    Class<Converter<Object, ? extends SoyData>> value();
}
