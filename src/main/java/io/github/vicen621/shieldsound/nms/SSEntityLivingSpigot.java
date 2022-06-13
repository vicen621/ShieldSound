/*
 * Copyright (c) 2022. Vicen621
 * All rights reserved.
 */

package io.github.vicen621.shieldsound.nms;

import org.bukkit.entity.Player;

public class SSEntityLivingSpigot implements SSEntityLiving {
    @Override
    public float getAbsorptionAmount(Player p) {
        return (float) p.getAbsorptionAmount();
    }
}
