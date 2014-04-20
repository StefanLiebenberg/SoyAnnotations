package slieb.soy.model;

import com.google.template.soy.data.SoyData;

import javax.annotation.Nonnull;

public class LazySoyData extends SoyData {

    private final LazyResult<SoyData> container;

    public LazySoyData(@Nonnull LazyResult<SoyData> container) {
        this.container = container;
    }

    @Override
    public boolean toBoolean() {
        return container.result().toBoolean();
    }

    @Override
    public boolean equals(Object other) {
        return container.result().equals(other);
    }


    @Override
    public boolean booleanValue() {
        return container.result().booleanValue();
    }

    @Override
    public int integerValue() {
        return container.result().integerValue();
    }

    @Override
    public double floatValue() {
        return container.result().floatValue();
    }

    @Override
    public double numberValue() {
        return container.result().numberValue();
    }

    @Override
    public String stringValue() {
        return container.result().stringValue();
    }

    @Override
    public String toString() {
        return container.toString();
    }
}
