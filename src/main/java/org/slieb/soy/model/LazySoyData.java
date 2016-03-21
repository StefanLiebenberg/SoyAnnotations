package org.slieb.soy.model;

import com.google.template.soy.data.SoyData;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.util.function.Supplier;

@ParametersAreNonnullByDefault
public class LazySoyData extends SoyData {

    private final Supplier<SoyData> container;

    public LazySoyData(@Nonnull Supplier<SoyData> container) {
        this.container = container;
    }

    @Override
    @Deprecated
    public boolean toBoolean() {
        return container.get().toBoolean();
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object other) {
        return container.get().equals(other);
    }

    /**
     * Renders this value to the given appendable.
     * <p>
     * <p>This should behave identically to {@code appendable.append(coerceToString())} but is
     * provided separately to allow more incremental approaches.
     *
     * @param appendable The appendable to render to.
     * @throws IOException
     */
    @Override
    public void render(final Appendable appendable) throws IOException {
        container.get().render(appendable);
    }

    @Override
    public boolean booleanValue() {
        return container.get().booleanValue();
    }

    @Override
    public int integerValue() {
        return container.get().integerValue();
    }

    @Override
    public double floatValue() {
        return container.get().floatValue();
    }

    @Override
    public double numberValue() {
        return container.get().numberValue();
    }

    @Override
    public String stringValue() {
        return container.get().stringValue();
    }

    @Override
    public String toString() {
        return container.toString();
    }
}
