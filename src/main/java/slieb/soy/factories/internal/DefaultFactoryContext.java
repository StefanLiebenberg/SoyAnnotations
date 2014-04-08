package slieb.soy.factories.internal;


import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;

public class DefaultFactoryContext<A> extends AbstractFactoryContext<A> {

    @Nonnull
    @Override
    public ImmutableList<Factory<A>> getFactoriesInternal() {
        return new ImmutableList.Builder<Factory<A>>()
                .addAll(customFactories)
                .build();
    }

}
