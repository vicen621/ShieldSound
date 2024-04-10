/*
 * Copyright (c) 2022. Vicen621
 * All rights reserved.
 */

package io.github.vicen621.shieldsound.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import io.github.vicen621.shieldsound.ShieldSound;
import io.github.vicen621.shieldsound.utils.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("shieldsound|ss")
public class ShieldSoundCommand extends BaseCommand {
    @Dependency
    private ShieldSound plugin;

    @Default
    @CatchUnknown
    @Description("Shows the ShieldSound plugin's information")
    public void onDefault(CommandSender sender) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {

            final String version = plugin.getDescription().getVersion();
            final String official = plugin.getVersionChecker().getNewVersion();

            final String server = Bukkit.getBukkitVersion();
            String[] split = Bukkit.getVersion().split("-");
            final String serverType = split.length > 1 ? split[1] : split[0];
            String build = null;

            try {
                if (serverType.equalsIgnoreCase("Paper"))
                    build = Bukkit.getVersion().split("-")[2].split(" ")[0];
            } catch (Exception ignored) {
            }

            final String buildVersion = build == null ? "" : "(" + build + ")";

            Bukkit.getScheduler().runTask(plugin, () -> {
                sender.sendMessage(Utils.format("&e--------------------------------------------------"));

                TextComponent author = new TextComponent(Utils.format("&ePlugin made by: &6Vicen621"));
                author.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent(Utils.format("&r&2Click to go to author's page"))}));
                author.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Vicen621"));
                if (sender instanceof Player)
                    ((Player) sender).spigot().sendMessage(ChatMessageType.CHAT, author);
                else
                    sender.sendMessage(author.getText());

                sender.sendMessage(Utils.format("&eShieldSound: &6" + version));

                if (!version.equalsIgnoreCase(official)) {
                    TextComponent text = new TextComponent(Utils.format("&eNew version available: &6" + official));
                    text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent(Utils.format("&r&2Click to download"))}));
                    text.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/102577/updates"));
                    if (sender instanceof Player)
                        ((Player) sender).spigot().sendMessage(ChatMessageType.CHAT, text);
                    else
                        sender.sendMessage(text.getText());
                }

                sender.sendMessage(Utils.format("&eServer: &6" + serverType + buildVersion + " " + server));
                sender.sendMessage(Utils.format("&e--------------------------------------------------"));
            });
        });
    }

    @HelpCommand
    public void onHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("reload|rl")
    @CommandPermission("shieldsound.reload")
    @Description("Reloads the ShieldSound plugin config")
    public void onReload(CommandSender sender) {
        plugin.getConfigManager().updateConfig();

        sender.sendMessage(Utils.format("&7[&3ShieldSound&7] &8> &aConfig reloaded"));
    }
}
