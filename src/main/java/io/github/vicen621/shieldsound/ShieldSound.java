/*
 * Copyright (c) 2022. Vicen621
 * All rights reserved.
 */

package io.github.vicen621.shieldsound;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.MessageType;
import io.github.vicen621.shieldsound.commands.ShieldSoundCommand;
import io.github.vicen621.shieldsound.config.Configuration;
import io.github.vicen621.shieldsound.listeners.ShieldBreakListener;
import io.github.vicen621.shieldsound.nms.SSEntityLiving;
import io.github.vicen621.shieldsound.nms.SSEntityLivingCraftbukkit;
import io.github.vicen621.shieldsound.nms.SSEntityLivingSpigot;
import io.github.vicen621.shieldsound.utils.Utils;
import io.github.vicen621.shieldsound.utils.VersionChecker;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public final class ShieldSound extends JavaPlugin {

    @Getter
    private static ShieldSound instance;
    @Getter(AccessLevel.PUBLIC)
    private Configuration configuration;
    @Getter(AccessLevel.PUBLIC)
    private BukkitCommandManager cmdManager;
    @Getter(AccessLevel.PUBLIC)
    private VersionChecker versionChecker;
    @Getter(AccessLevel.PUBLIC)
    private SSEntityLiving entityLiving;
    @Getter(AccessLevel.PUBLIC)
    private double version;

    protected ShieldSound(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    @Override
    public void onEnable() {
        instance = this;

        configuration = new Configuration();
        configuration.loadAndSave();
        getLogger().info(Utils.format("&7[&3ShieldSound&7] &8> &fLoaded configuration file: config.yml!"));

        versionChecker = new VersionChecker(this);
        versionChecker.versionCheck(null);

        commands();

        if (!setupEntityLiving()) {
            getLogger().severe("Failed to setup ShieldSound!");
            getLogger().severe("Your server version is not compatible with this plugin!");

            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getPluginManager().registerEvents(new ShieldBreakListener(), this);

        getLogger().info(Utils.format("&7[&3ShieldSound&7] &8> &fShieldSound has been enabled!"));
    }

    @Override
    public void onDisable() {
        configuration.save();
    }

    private void commands() {
        cmdManager = new BukkitCommandManager(getInstance());
        cmdManager.enableUnstableAPI("help");

        cmdManager.setFormat(MessageType.HELP, ChatColor.DARK_AQUA, ChatColor.AQUA, ChatColor.GRAY, ChatColor.DARK_GRAY);
        cmdManager.registerCommand(new ShieldSoundCommand());
    }

    private boolean setupEntityLiving() {
        version = Double.parseDouble(Bukkit.getBukkitVersion().split("-")[0].replaceFirst("1.", ""));

        if (version >= 14.4) // >= 1.14.4
            entityLiving = new SSEntityLivingSpigot();
        else if (version > 9.0) // >= 1.9
            entityLiving = new SSEntityLivingCraftbukkit();

        return entityLiving != null;
    }
}
