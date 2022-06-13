/*
 * Copyright (c) 2022. Vicen621
 * All rights reserved.
 */

package io.github.vicen621.shieldsound.utils;

import io.github.vicen621.shieldsound.ShieldSound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

public class VersionChecker {

    private final ShieldSound plugin;

    public VersionChecker(ShieldSound plugin) {
        this.plugin = plugin;
    }

    public void versionCheck(final Player player) {
        if (!ShieldSound.getInstance().getConfiguration().checkUpdates)
            return;

        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            String newVersion = getNewVersion();
            String currentVersion = plugin.getDescription().getVersion();
            if (newVersion == null)
                return;

            int currentVer = Integer.parseInt(currentVersion.replace(".", ""));
            int newVer = Integer.parseInt(newVersion.replace(".", ""));

            if (currentVer >= newVer)
                return;

            List<String> msg = Arrays.asList(
                    ChatColor.AQUA + "==================== " + plugin.getDescription().getName() + " ====================",
                    ChatColor.AQUA + " >  " + newVersion + " is now available! Your version: " + currentVersion,
                    ChatColor.AQUA + " >  " + ChatColor.DARK_AQUA + plugin.getDescription().getWebsite() + ChatColor.AQUA,
                    ChatColor.AQUA + "===================================================");
            for (String one : msg)
                if (player != null) {
                    player.sendMessage(one);
                    player.playSound(player.getLocation(),
                            "block." + (ShieldSound.getInstance().getVersion() <= 12 ? "note" : "note_block") + ".pling",
                            1, 1);
                } else
                    Bukkit.getLogger().info(one);
        });
    }

    public String getNewVersion() {
        try {
            URLConnection con = new URL("https://api.spigotmc.org/legacy/update.php?resource=102577").openConnection();
            String version = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
            if (version.length() <= 8)
                return version;
        } catch (Throwable t) {
            Bukkit.getLogger().info(Utils.format("&cFailed to check for " + plugin.getDescription().getName() + " update on spigot web page."));
        }
        return null;
    }
}
