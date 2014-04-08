package example.models;

import slieb.soy.annotations.Soy;

@Soy
public class BadDynamicFieldExample {

    @Soy.Field("Child")
    public BadDynamicFieldExample Child;
}
