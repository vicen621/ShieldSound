![ShieldSound Logo](assets/logo.png)

# ShieldSound

[![Build Status](https://github.com/Vicen621/ShieldSound/workflows/Build/badge.svg)](../../actions?query=workflow%3ABuild)
[![GitHub release (latest SemVer including pre-releases)](https://img.shields.io/github/v/release/Vicen621/ShieldSound?include_prereleases&label=release)](../../releases)
![Spiget tested server versions](https://img.shields.io/badge/tested%20versions-1.9--1.20-blue)
[![Spiget Downloads](https://img.shields.io/spiget/downloads/102577)](https://www.spigotmc.org/resources/1-9-1-19-shieldsound.102577/)
[![Spiget Rating](https://img.shields.io/spiget/rating/102577)](https://www.spigotmc.org/resources/1-9-1-19-shieldsound.102577/)
[![Commitizen friendly](https://img.shields.io/badge/commitizen-friendly-brightgreen.svg)](http://commitizen.github.io/cz-cli/)
[![semantic-release](https://img.shields.io/badge/%20%20%F0%9F%93%A6%F0%9F%9A%80-semantic--release-e10079.svg)](https://github.com/semantic-release/semantic-release)


An easy-to-use plugin that fixes the annoying error of when a shield breaks and does not play the sound


![Version](assets/version.png)
It's compatible with 1.9+ onwards
<br><br>

![Features](assets/features.png)
You can select the desired sound, which will be heard beside the volume and pitch you choose.
You can select on which worlds it works, being compatible with any world manager
<br><br>

![Default configuration](assets/default-config.png)
```yaml

#                        Plugin made by:
#                           Vicen621


# This is the sound played when a player breaks another player's shield.
# Here you have a list of sounds you can choose from
# Remember to change the {version} field with your version.
# https://helpch.at/docs/{version}/org/bukkit/Sound.html
break_sound:
  sound: ITEM_SHIELD_BREAK
  volume: 1.0
  pitch: 1.0

# List of worlds where the sound will not be played
deactivated_worlds:
- test

# Enable or disable the update checker
check_updates: true
```

### Supporting me
[If you like to support me, feel free to donate.](https://paypal.me/Vicen621)

### Bug Reports
If you find a bug, please do not put in the discussion section, report it [here](https://github.com/vicen621/ShieldSound/issues/new/choose)

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
Please make sure to update tests as appropriate.
https://github.com/vicen621/ShieldSound/blob/master/CONTRIBUTING.md
