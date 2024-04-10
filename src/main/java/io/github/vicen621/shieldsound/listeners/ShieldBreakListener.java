/*
 * Copyright (c) 2022. Vicen621
 * All rights reserved.
 */

package io.github.vicen621.shieldsound.listeners;

import io.github.vicen621.shieldsound.ShieldSound;
import io.github.vicen621.shieldsound.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ShieldBreakListener implements Listener {
    private final ShieldSound plugin;

    public ShieldBreakListener(ShieldSound plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) throws Exception {
        if (!isInDisabledWorlds(e.getEntity().getLocation()) &&
                e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            Player d = (Player) e.getDamager();
            if (p.isBlocking() && d.getInventory().getItemInMainHand().getType().toString().contains("_AXE")) {
                double health1 = p.getHealth() + plugin.getEntityLiving().getAbsorptionAmount(p);
                delay(1, () -> {
                    try {

                        double health2 = p.getHealth() + plugin.getEntityLiving().getAbsorptionAmount(p);

                        if (health1 == health2) {
                            Config.PlayableSound sound = plugin.getConfiguration().breakSound;
                            d.playSound(p.getLocation(), sound.getSound(), sound.getVolume(), sound.getPitch());
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (plugin.getConfiguration().checkUpdates &&
                e.getPlayer().hasPermission("shieldsound.versioncheck"))
            plugin.getVersionChecker().versionCheck(e.getPlayer());
    }

    public void delay(long ticks, Runnable run) {
        new BukkitRunnable() {
            @Override
            public void run() {
                run.run();
            }
        }.runTaskLater(plugin, ticks);
    }

    public boolean isInDisabledWorlds(Location loc) {
        boolean bool = false;
        for (String worldName : plugin.getConfiguration().deactivatedWorlds) {
            World world = loc.getWorld();

            if (world == null)
                continue;

            bool = world.getName().equalsIgnoreCase(worldName);
            if (bool)
                break;
        }
        return bool;
    }
}
