package com.tenjava.entries.tyzoid.t3.random;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.Player;

public class Zombies {
	private Player player;
	
	public Zombies(Player player, short number) {
		Random r = new Random();
		World w = player.getWorld();
		
		Zombie zombie;
		
		for(short i = 0; i < number; i++){
			Location l = player.getLocation();
			l.add(10-r.nextDouble()*20, 10-r.nextDouble()*20, 10-r.nextDouble()*20);
			zombie = (Zombie) w.spawnCreature(l, CreatureType.ZOMBIE);
			zombie.setTarget(player);
			
			// 25% chance of baby zombie
			if(r.nextInt(5) == 0) zombie.setBaby(true);
		}
	}
}
