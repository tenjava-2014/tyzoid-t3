package com.tenjava.entries.tyzoid.t3.random;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.tenjava.entries.tyzoid.t3.RandomPlayer;

public class Effects {
	private RandomPlayer randomplayer;
	private Player player;
	
	public Effects(RandomPlayer randomplayer) {
		this.randomplayer = randomplayer;
		this.player = randomplayer.getPlayer();
	}
	
	public short getEventCount() {
		return 10;
	}
	
	/**
	 * 
	 * @param eventID
	 * @param severity
	 *            a double between 0.0 and 10.0
	 */
	public boolean startEvent(short eventID, double severity) {
		switch (eventID) {
			case 0:
				zombies((short) (severity + 10));
				break;
			case 1:
				angryWolves((short) (severity + 10));
				break;
			case 2:
				teleport((int) (severity * 100000));
				break;
			case 3:
				encaseIce();
				break;
			case 4:
				lightningStrike(severity / 10);
				break;
			case 5:
				giveDiamonds((int) (severity/3 + 1));
				break;
			case 6:
				equiptArmour((int) (severity/3));
				break;
			case 7:
				awardXp((int) (severity*10+100));
				break;
			case 8:
				invincibility();
				break;
			case 9:
				gibberish((int) (severity/4 + 1));
				break;
			default:
				return false;
		}
		
		return true;
	}
	
	public void zombies(short number) {
		Random r = new Random();
		World w = player.getWorld();
		
		Zombie zombie;
		
		for(short i = 0; i < number; i++) {
			Location loc = player.getLocation();
			loc.add(10 - r.nextDouble() * 20, 10 - r.nextDouble() * 20, 10 - r.nextDouble() * 20);
			zombie = (Zombie) w.spawnCreature(loc, CreatureType.ZOMBIE);
			zombie.setTarget(player);
			
			// 25% chance of baby zombie
			if(r.nextInt(5) == 0) zombie.setBaby(true);
		}
	}
	
	public void angryWolves(short number) {
		Random r = new Random();
		World w = player.getWorld();
		
		Wolf wolf;
		
		for(short i = 0; i < number; i++) {
			Location loc = player.getLocation();
			loc.add(10 - r.nextDouble() * 20, 10 - r.nextDouble() * 20, 10 - r.nextDouble() * 20);
			wolf = (Wolf) w.spawnCreature(loc, CreatureType.WOLF);
			
			if(wolf != null) {
				wolf.setTarget(player);
				wolf.setAngry(true);
				
				// Workaround to get the wolf to attack the player.
				wolf.damage(0, player);
			}
		}
	}
	
	public void teleport(int placement) {
		int placementx = (placement / 1000) - 500;
		int placementz = (placement % 1000) - 500;
		
		Location l = player.getLocation();
		
		int x = l.getBlockX() + placementx;
		int z = l.getBlockZ() + placementz;
		
		World w = player.getWorld();
		// Place them on top of the lowest solid block possible.
		for(int i = 4; i < w.getMaxHeight(); i++) {
			if(w.getBlockAt(x, i, z).getType().isSolid()
					&& w.getBlockAt(x, i + 1, z).getTypeId() == 0
					&& w.getBlockAt(x, i + 2, z).getTypeId() == 0) {
				player.teleport(new Location(w, x + .5, i + 1.5, z + .5));
				break;
			}
		}
	}
	
	public void encaseIce() {
		Location l = player.getLocation();
		World w = player.getWorld();
		
		int x = l.getBlockX();
		int y = l.getBlockY();
		int z = l.getBlockZ();
		
		for(int dy = -1; dy < 3; dy++) {
			for(int dx = -1; dx < 2; dx++) {
				for(int dz = -1; dz < 2; dz++) {
					Block b = w.getBlockAt(x + dx, y + dy, z + dz);
					if(!b.getType().isSolid()) b.setType(Material.ICE);
				}
			}
		}
	}
	
	public void lightningStrike(double damage) {
		double health = player.getHealth() / player.getMaxHealth();
		
		// Allow me to control damage
		player.getWorld().strikeLightningEffect(player.getLocation());
		
		if(health - damage < 0) player.setHealth(.5); // approximately one health unit
		else player.setHealth((health - damage) * player.getMaxHealth());
		
		player.damage(0); //Damage effect
	}
	
	public void giveDiamonds(int number) {
		ItemStack diamonds = new ItemStack(Material.DIAMOND, number);
		HashMap<Integer, ItemStack> leftover = player.getInventory().addItem(diamonds);
		
		if(!leftover.isEmpty()) player.getWorld().dropItem(player.getLocation(), diamonds);
	}
	
	public void equiptArmour(int type) {
		ItemStack diamondArmour;
		
		switch (type) {
			case 0:
				diamondArmour = new ItemStack(Material.DIAMOND_HELMET);
				player.getInventory().setHelmet(diamondArmour);
				break;
			case 1:
				diamondArmour = new ItemStack(Material.DIAMOND_CHESTPLATE);
				player.getInventory().setChestplate(diamondArmour);
				break;
			case 2:
				diamondArmour = new ItemStack(Material.DIAMOND_LEGGINGS);
				player.getInventory().setLeggings(diamondArmour);
				break;
			case 3:
				diamondArmour = new ItemStack(Material.DIAMOND_BOOTS);
				player.getInventory().setBoots(diamondArmour);
				break;
		}
	}
	
	public void awardXp(int amount) {
		player.giveExp(amount);
	}
	
	public void invincibility() {
		randomplayer.setInvincible(true);
	}
	
	public void gibberish(int level){
		randomplayer.setGibberish(level);
	}
}
