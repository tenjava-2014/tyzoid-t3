/**
 * @author tyzoid
 * @date 2014-7-12
 * @copyright 2014 tyzoid. Licensed under the terms of the TPL (Tyzoid Public License)
 * 
 */

package com.tenjava.entries.tyzoid.t3;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.tyzoid.t3.random.Effects;
import com.tenjava.entries.tyzoid.t3.random.RandomTimer;

public class TenJava extends JavaPlugin implements Listener {
	private Words randomwords;
	private RandomTimer randtimer;
	private ConcurrentHashMap<UUID, RandomPlayer> playerlist;
	
	@Override
	public void onEnable() {
		playerlist = new ConcurrentHashMap<UUID, RandomPlayer>();
		randomwords = new Words();
		
		/*
		 * Check if there are any users already online
		 * This happens if the server is reloaded
		 */
		for(Player p : Bukkit.getOnlinePlayers()) {
			playerlist.put(p.getUniqueId(), new RandomPlayer(p));
		}
		
		randtimer = new RandomTimer(this);
		
		// Run task every 100 seconds
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, randtimer, 200L, 2000L);
		
		//Register events
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equals("effect")) {
			if(args.length > 1 && args.length < 4) {
				String[] types = {"Zombie", "Wolf", "Teleport", "Ice", "Lightning",
						"Diamond", "Armour|Armor", "Xp", "Invincibile", "Nonsense"};
				int type;
				double severity;
				
				try {
					type = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					/*type = -1;
					for(int i = 0; i < types.length && type == -1; i++){
						System.out.println(types[i]);
						String[] split = types[i].split("|");
						for (String s : split){
							System.out.println(s);
							if(s.equalsIgnoreCase(args[1])){
								System.out.println("Matched with " + s);
								type = i;
								break;
							}
						}
					}
					
					if(type == -1){
						sender.sendMessage("Not a valid type. Valid types:");
						String message = "";
						for (int i = 0; i < types.length; i++){
							System.out.println(types[i]);
							String[] split = types[i].split("|");
							if(i % 3 == 2){
								sender.sendMessage(message + ", " + split[0]);
							} else if(i%3 == 0) {
								message = split[0];
							} else {
								message += ", " + split[0];
							}
						}
						return false;
					}*/
					
					sender.sendMessage("Not a valid type. Please use a number from 0 to 9");
				}
				
				try {
					severity = 4;
					if(args.length == 3) severity = Integer.parseInt(args[2]) / 10d;
					if(severity > 100 || severity < 0) throw new NumberFormatException();
				} catch (NumberFormatException e) {
					sender.sendMessage("Severity is invalid. Please specify a number from 0 to 100");
					return false;
				}
				
				for(RandomPlayer p : playerlist.values()) {
					if(p.getName().equals(args[0])) {
						Effects effect = new Effects(p);
						effect.startEvent((short) type, severity);
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Adds the player to the player list on connect
	 * 
	 * @param e
	 *            the event
	 */
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		playerlist.put(p.getUniqueId(), new RandomPlayer(p));
	}
	
	/**
	 * Removes the player from the player list on disconnect
	 * 
	 * @param e the event
	 */
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		playerlist.remove(p.getUniqueId());
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if(!(e.getEntity() instanceof Player)) return;
		
		Player p = (Player) e.getEntity();
		
		if(playerlist.get(p.getUniqueId()).isInvincible()) e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		int gibberishLevel = playerlist.get(e.getPlayer().getUniqueId()).getGibberish();
		
		if(gibberishLevel == 0) return;
		
		String message = e.getMessage();
		String[] split = message.split(" ");
		
		switch (gibberishLevel) {
			case 1:
				// prefix with &k
				e.setMessage(ChatColor.MAGIC + message);
				break;
			case 2:
				// Replace every other word with a random(ish) word.
				message = "";
				for(int i = 0; i < split.length; i++){
					if(i%2 == 0) message += randomwords.getRandomWord();
					else message += split[i];
					
					if(i < split.length-1) message += " ";
				}
				
				e.setMessage(message);
				break;
			case 3:
				// Replace every word with a random(ish) word.
				message = "";
				for(int i = 0; i < split.length; i++){
					message += randomwords.getRandomWord();
					
					if(i < split.length-1) message += " ";
				}
				
				e.setMessage(message);
				break;
		}
	}
	
	/**
	 * Get the player hashmap
	 */
	public ConcurrentHashMap<UUID, RandomPlayer> getPlayerMap() {
		return playerlist;
	}
}
