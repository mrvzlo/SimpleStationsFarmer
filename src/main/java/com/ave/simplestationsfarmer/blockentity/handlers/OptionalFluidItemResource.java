package com.ave.simplestationsfarmer.blockentity.handlers;

import com.ave.simplestationscore.resources.FluidItemResource;

public class OptionalFluidItemResource extends FluidItemResource {

    public OptionalFluidItemResource(int max, int usage, int inc, String key) {
        super(max, usage, inc, key);
    }

    @Override()
    public int getRequired() {
        return 0;
    }
}
