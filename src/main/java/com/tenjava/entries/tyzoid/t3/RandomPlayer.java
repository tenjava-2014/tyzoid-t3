package com.tenjava.entries.tyzoid.t3;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RandomPlayer {
	private String name;
	private UUID uuid;
	private boolean invincible;
	
	public RandomPlayer(Player p){
		this.name = p.getName();
		this.uuid = p.getUniqueId();
	}
	
	public String getName() {
		return name;
	}
	
	public Player getPlayer(){
		return Bukkit.getPlayer(this.uuid);
	}
	
	public boolean isInvincible() {
		return invincible;
	}
	
	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}
}