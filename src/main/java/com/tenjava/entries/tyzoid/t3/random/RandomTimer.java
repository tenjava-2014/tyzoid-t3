package com.tenjava.entries.tyzoid.t3.random;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.tyzoid.t3.RandomPlayer;
import com.tenjava.entries.tyzoid.t3.TenJava;

public class RandomTimer extends BukkitRunnable {
	private TenJava plugin;
	private Random r;
	
	public RandomTimer(TenJava instance) {
		this.plugin = instance;
		r = new Random();
	}
	
	@Override
	public void run() {
		/*
		 * Events:
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
		 */
		
		// Select a player at random
		ArrayList<RandomPlayer> playerlist = new ArrayList<RandomPlayer>(plugin.getPlayerMap().values());
		int size = playerlist.size();
		
		if(size > 0) {
			// Reset any effects on the player
			for(RandomPlayer p : playerlist){
				p.resetEffects();
			}
			
			RandomPlayer random = playerlist.get(r.nextInt(size));
			
			// Select a random effect.
			Effects effect = new Effects(random);
			
			short func;
			long time = random.getPlayer().getWorld().getTime();
			
			// If it is day time, we cannot spawn zombies
			if(time > 13000 && time < 23000) {
				func = (short) r.nextInt(effect.getEventCount());
			} else {
				func = (short) (r.nextInt(effect.getEventCount() - 1) + 1);
			}
			
			Bukkit.broadcastMessage("Started effect!");
			effect.startEvent(func, r.nextDouble() * 10);
		}
	}
}
