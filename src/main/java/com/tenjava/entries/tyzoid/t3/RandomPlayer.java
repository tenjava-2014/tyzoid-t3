package com.tenjava.entries.tyzoid.t3;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RandomPlayer {
	private String name;
	private UUID uuid;
	private boolean invincible;
	private int gibberish;
	
	public RandomPlayer(Player p){
		this.name = p.getName();
		this.uuid = p.getUniqueId();
		
		this.invincible = false;
		this.gibberish = 0;
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
	
	public int getGibberish() {
		return gibberish;
	}

	public void setGibberish(int gibberish) {
		this.gibberish = gibberish;
	}

	public void resetEffects(){
		this.invincible = false;
		this.gibberish = 0;
	}
}