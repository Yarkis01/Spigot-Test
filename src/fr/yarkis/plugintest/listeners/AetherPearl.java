package fr.yarkis.plugintest.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.yarkis.plugintest.Main;

public class AetherPearl implements Listener {
	private Main plugin;
	
	public AetherPearl(Main main) {
		this.plugin = main;
		
		new BukkitRunnable() {
			@Override
			public void run() {
				for(World world : plugin.getServer().getWorlds()) {
					for(Entity entity : world.getEntitiesByClass(EnderPearl.class)) {
						if(entity.getCustomName() != null && entity.getCustomName().equals(ChatColor.LIGHT_PURPLE + "Aether Pearl")) {
							if(entity.getPassengers().size() == 0) {
								entity.remove();
							}
						}
					}
				}
			}
		}.runTaskTimer(plugin, 0, 1);
	}
	
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent event) {
		if(event.getEntityType().equals(EntityType.ENDER_PEARL)) {
			EnderPearl pearlEntity = (EnderPearl)event.getEntity();
			
			if(!(pearlEntity.getShooter() instanceof Player)) {
				return;
			}
			
			Player player = (Player)pearlEntity.getShooter();
			
			ItemStack enderPearl;
			if(player.getInventory().getItemInMainHand().getType().equals(Material.ENDER_PEARL)) {
				enderPearl = player.getInventory().getItemInMainHand();
			} else {
				enderPearl = player.getInventory().getItemInOffHand();
			}
			
			if(enderPearl.getItemMeta().getDisplayName() != null && enderPearl.getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Aether Pearl")) {
				pearlEntity.addPassenger(player);
				pearlEntity.setVelocity(pearlEntity.getVelocity().normalize());
				pearlEntity.setCustomName(ChatColor.LIGHT_PURPLE + "Aether Pearl");
				player.setCooldown(Material.ENDER_PEARL, 1);
			}
		}
	}

}
