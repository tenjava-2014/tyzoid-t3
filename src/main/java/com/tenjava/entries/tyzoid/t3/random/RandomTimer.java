package com.tenjava.entries.tyzoid.t3.random;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.tyzoid.t3.RandomPlayer;
import com.tenjava.entries.tyzoid.t3.TenJava;

public class RandomTimer extends BukkitRunnable {
	private TenJava plugin;
	private Random r;

	public RandomTimer(TenJava instance){
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
		 *     Give two diamonds (enough for sword)
		 *     Equipt Diamond Armor (if user has some, add to inventory/drop if full)
		 *     Award a random amount of XP (200-300 - good for ~10-15 levels)
		 *     Invincibility for 100 seconds (one minute, 40 seconds)
		 */
		
		// Select a pRandomPlayerdom
		ArrayList<RandomPlayer> playerlist = plugin.getPlayers();
		int size = playerlist.size();
		
		Player random = playerlist.get(r.nextInt(size));
		
	}
}
