/*
 * Copyright (c) 2022. Vicen621
 * All rights reserved.
 */

package io.github.vicen621.shieldsound.nms;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public class SSEntityLivingCraftBukkit implements SSEntityLiving {
    private Method playerHandleMethod, absorptionHeartsMethod;
    private String[] arrayVersion;

    @Override
    public float getAbsorptionAmount(Player p) throws Exception {
        if (playerHandleMethod == null)
            playerHandleMethod = p.getClass().getDeclaredMethod("getHandle");

        Object handle = playerHandleMethod.invoke(p);

        if (absorptionHeartsMethod == null)
            absorptionHeartsMethod = handle.getClass().getSuperclass().getSuperclass().getDeclaredMethod("getAbsorptionHearts");

        return (float) absorptionHeartsMethod.invoke(handle);
    }

    private String[] getArrayVersion() {
        if (arrayVersion == null)
            arrayVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.");

        return arrayVersion;
    }
}
