/**
 * @author tyzoid
 * @date 2014-7-12
 * @copyright 2014 tyzoid. Licensed under the terms of the TPL (Tyzoid Public License)
 * 
 */

package com.tenjava.entries.tyzoid.t3;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import com.tenjava.entries.tyzoid.t3.random.RandomTimer;

public class TenJava extends JavaPlugin implements Listener {
	private RandomTimer randtimer;
	private BukkitTask rttask;
	private ArrayList<RandomPlayer> playerlist;
	
	@Override
	public void onEnable() {
		playerlist = new ArrayList<RandomPlayer>();
		
		/*
		 * Check if there are any users already online
		 * This happens if the server is reloaded
		 */
		for(Player p : Bukkit.getOnlinePlayers()) {
			playerlist.add(new RandomPlayer(p));
		}
		
		randtimer = new RandomTimer(this);
		
		// Run task every 100 seconds
		rttask = randtimer.runTaskLater(this, 2000);
		
		//Register events
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return false; // for now
	}
	
	/**
	 * Adds the player to the player list on connect
	 * @param e the event
	 */
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		playerlist.add(new RandomPlayer(e.getPlayer()));
	}
	
	/**
	 * Removes the player from the player list on disconnect
	 * @param e the event
	 */
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		for(RandomPlayer p : playerlist) {
			if(p.getName() == e.getPlayer().getName()){
				playerlist.remove(p);
				break;
			}
		}
	}
	
	/**
	 * Gets the plugins player list.
	 * 
	 * @return playerlist the list of players recognized by the plugin
	 */
	public ArrayList<RandomPlayer> getPlayers() {
		return playerlist;
	}
}
