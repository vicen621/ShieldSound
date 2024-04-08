package io.github.vicen621.shieldsound.config.serializers;

import de.exlll.configlib.Serializer;
import org.bukkit.Sound;

import java.util.Locale;

public class SoundSerializer implements Serializer<Sound, String> {
    @Override
    public String serialize(Sound sound) {
        return sound.name();
    }

    @Override
    public Sound deserialize(String s) {
        return Sound.valueOf(s.toUpperCase(Locale.ENGLISH));
    }
}
