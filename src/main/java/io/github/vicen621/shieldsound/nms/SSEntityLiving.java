/*
 * Copyright (c) 2022. Vicen621
 * All rights reserved.
 */

package io.github.vicen621.shieldsound.nms;

import org.bukkit.entity.Player;

public interface SSEntityLiving {
    float getAbsorptionAmount(Player p) throws Exception;
}
