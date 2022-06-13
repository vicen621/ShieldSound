/*
 * Copyright (c) 2022. Vicen621
 * All rights reserved.
 */

package io.github.vicen621.shieldsound.config;

import de.exlll.configlib.annotation.ConfigurationElement;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Sound;

@ConfigurationElement
public class PlayableSound {
    private String sound;

    @Getter
    @Setter
    private float volume;

    @Getter
    @Setter
    private float pitch;

    private PlayableSound() {
        this("", 0, 0);
    }

    public PlayableSound(String sound, float volume, float pitch) {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
    }

    public Sound getSound() {
        return Sound.valueOf(sound);
    }

    public void setSound(Sound sound) {
        this.sound = sound.name();
    }
}
