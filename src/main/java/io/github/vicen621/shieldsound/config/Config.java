/*
 * Copyright (c) 2022. Vicen621
 * All rights reserved.
 */

package io.github.vicen621.shieldsound.config;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import lombok.Data;
import org.bukkit.Sound;

import java.util.Collections;
import java.util.List;

@de.exlll.configlib.Configuration
public class Config {

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

    @Data
    @Configuration
    public static class PlayableSound {
        private final String sound;
        private final float volume;
        private final float pitch;
    }
}
