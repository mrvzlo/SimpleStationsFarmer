package com.ave.simplestationsfarmer.blockentity.handlers;

import com.ave.simplestationscore.resources.EnergyResource;
import com.ave.simplestationsfarmer.Config;

public class OptionalEnergyResource extends EnergyResource {

    public OptionalEnergyResource(int usage) {
        super(Config.POWER_MAX.get(), usage, Config.POWER_PER_RED.get());
    }

    @Override()
    public int getRequired() {
        return 0;
    }
}
