/*
 * Copyright (c) 2022. Vicen621
 * All rights reserved.
 */

package io.github.vicen621.shieldsound;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.MessageType;
import io.github.vicen621.shieldsound.commands.ShieldSoundCommand;
import io.github.vicen621.shieldsound.config.Config;
import io.github.vicen621.shieldsound.config.ConfigManager;
import io.github.vicen621.shieldsound.listeners.ShieldBreakListener;
import io.github.vicen621.shieldsound.nms.SSEntityLiving;
import io.github.vicen621.shieldsound.nms.SSEntityLivingCraftBukkit;
import io.github.vicen621.shieldsound.nms.SSEntityLivingSpigot;
import io.github.vicen621.shieldsound.utils.VersionChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShieldSound extends JavaPlugin {

    private ConfigManager<Config> configManager;
    private VersionChecker versionChecker;
    private SSEntityLiving entityLiving;
    private double version;

    @Override
    public void onEnable() {
        configManager = new ConfigManager<>(this, "config.yml", Config.class);
        getLogger().info("[ShieldSound] > Loaded configuration file: config.yml!");

        versionChecker = new VersionChecker(this);
        versionChecker.versionCheck(null);

        commands();

        if (!setupEntityLiving()) {
            getLogger().severe("Failed to setup ShieldSound!");
            getLogger().severe("Your server version is not compatible with this plugin!");

            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        new ShieldBreakListener(this);

        getLogger().info("[ShieldSound] > ShieldSound has been enabled!");
    }

    @Override
    public void onDisable() {
        configManager.saveConfig();
    }

    @SuppressWarnings("deprecation")
    private void commands() {
        BukkitCommandManager cmdManager = new BukkitCommandManager(this);
        cmdManager.enableUnstableAPI("help");
        cmdManager.registerDependency(ShieldSound.class, "plugin", this);

        cmdManager.setFormat(MessageType.HELP, ChatColor.DARK_AQUA, ChatColor.AQUA, ChatColor.GRAY, ChatColor.DARK_GRAY);
        cmdManager.registerCommand(new ShieldSoundCommand());
    }

    private boolean setupEntityLiving() {
        version = Double.parseDouble(Bukkit.getBukkitVersion().split("-")[0].replaceFirst("1.", ""));

        if (version >= 14.4) // >= 1.14.4
            entityLiving = new SSEntityLivingSpigot();
        else if (version > 9.0) // >= 1.9
            entityLiving = new SSEntityLivingCraftBukkit();

        return entityLiving != null;
    }

    public Config getConfiguration() {
        return configManager.getConfig();
    }

    public ConfigManager<Config> getConfigManager() {
        return configManager;
    }

    public VersionChecker getVersionChecker() {
        return versionChecker;
    }

    public SSEntityLiving getEntityLiving() {
        return entityLiving;
    }

    public double getVersion() {
        return version;
    }
}
