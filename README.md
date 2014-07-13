tyzoid's ten.java submission
==============================

[![ten.java](https://cdn.mediacru.sh/hu4CJqRD7AiB.svg)](https://tenjava.com/)

This is a submission for the 2014 ten.java contest.

- __Theme:__ What Random Events can occur in Minecraft?
- __Time:__ Time 3 (7/12/2014 14:00 to 7/13/2014 00:00 UTC)
- __MC Version:__ 1.7.9 (latest Bukkit beta)
- __Stream URL:__ https://twitch.tv/tyzoid

<!-- put chosen theme above -->

---------------------------------------

Compilation
-----------

- Download & Install [Maven 3](http://maven.apache.org/download.html) or run `sudo apt-get install maven` on any ubuntu/debian machine.
- Clone the repository: `git clone https://github.com/tenjava/tyzoid-t3`
- Compile and create the plugin package using Maven: `mvn`

Maven will download all required dependencies and build a ready-for-use plugin package!

---------------------------------------

Usage
-----

1. Install plugin
2. Wait for things to happen to your players


Command Effect Types:
Events:
		 *   Negative:
		 *     Zombie Spawn (night only)
		 *     Angry Wolves
		 *     Teleport to random location/cave
		 *     Encase in ice
		 *     Lightning Strikes player
		 *   Positive:
		 *     Give diamonds (1-4)
		 *     Equipt Diamond Armor (if user has some, add to inventory/drop if full)
		 *     Award a random amount of XP (200-300 - good for ~10-15 levels)
		 *     Invincibility for 100 seconds (one minute, 40 seconds)
		 *   Neutral:
		 *     Gibberish (make chat messages unable to be understood)
		 *
<!-- Hi, tyzoid! This is the default README for every ten.java submission. -->
<!-- We encourage you to edit this README with some information about your submission – keep in mind you'll be scored on documentation! -->
