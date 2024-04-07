package io.github.vicen621.shieldsound.config;

import de.exlll.configlib.ConfigLib;
import de.exlll.configlib.NameFormatters;
import de.exlll.configlib.YamlConfigurationProperties;
import de.exlll.configlib.YamlConfigurations;
import io.github.vicen621.shieldsound.ShieldSound;
import lombok.Getter;

import java.io.File;

public class ConfigManager<C> {
    private final ShieldSound plugin;
    private final YamlConfigurationProperties PROPERTIES = ConfigLib.BUKKIT_DEFAULT_PROPERTIES.toBuilder()
            .setNameFormatter(NameFormatters.LOWER_UNDERSCORE)
            .header("\n                       Plugin made by:\n                          Vicen621")
            .build();
    private final String name;
    private final Class<C> clazz;
    @Getter
    private C config;

    public ConfigManager(ShieldSound plugin, String name, Class<C> clazz) {
        this.plugin = plugin;
        this.name = name;
        this.clazz = clazz;
        updateConfig();
    }

    public void updateConfig() {
        config = YamlConfigurations.update(new File(plugin.getDataFolder(), name).toPath(), clazz, PROPERTIES);
    }

    public void saveConfig() {
        YamlConfigurations.save(new File(plugin.getDataFolder(), name).toPath(), clazz, config, PROPERTIES);
    }
}