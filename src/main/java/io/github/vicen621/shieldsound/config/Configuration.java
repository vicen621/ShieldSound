/*
 * Copyright (c) 2022. Vicen621
 * All rights reserved.
 */

package io.github.vicen621.shieldsound.config;

import de.exlll.configlib.annotation.Comment;
import de.exlll.configlib.configs.yaml.BukkitYamlConfiguration;
import de.exlll.configlib.format.FieldNameFormatters;
import io.github.vicen621.shieldsound.ShieldSound;
import org.bukkit.Sound;

import java.io.File;
import java.util.Collections;
import java.util.List;

//TODO: Update ConfigLib to the new version
@Comment({"",
        "                       Plugin made by:",
        "                          Vicen621"})
public class Configuration extends BukkitYamlConfiguration {

    @Comment({
            "",
            "This is the sound played when a player breaks another player's shield.",
            "Here you have a list of sounds you can choose from",
            "Remember to change the {version} field with your version.",
            "https://helpch.at/docs/{version}/org/bukkit/Sound.html"
    })
    public PlayableSound breakSound = new PlayableSound(Sound.ITEM_SHIELD_BREAK.name(), 1, 1);
    @Comment({"", "List of worlds where the sound will not be played"})
    public List<String> deactivatedWorlds = Collections.singletonList("test");
    @Comment({"", "Enable or disable the update checker"})
    public boolean checkUpdates = true;

    public Configuration() {
        super(
                new File(ShieldSound.getInstance().getDataFolder(), "config.yml").toPath(),
                BukkitYamlProperties.builder().setFormatter(FieldNameFormatters.LOWER_UNDERSCORE).build()
        );
    }
}
