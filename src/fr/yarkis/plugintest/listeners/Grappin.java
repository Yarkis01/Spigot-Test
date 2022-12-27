package fr.yarkis.plugintest.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Grappin implements Listener {

	@EventHandler
	public void onPlayerFish(PlayerFishEvent event) {
		Player player = event.getPlayer();
		
		ItemStack rod;
		if(player.getInventory().getItemInMainHand().getType().equals(Material.FISHING_ROD)) {
			rod = player.getInventory().getItemInMainHand();
		} else {
			rod = player.getInventory().getItemInOffHand();
		}
		
		if(rod.getItemMeta().getDisplayName() != null && rod.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Grappin")) {
			player.setVelocity(player.getEyeLocation().getDirection().add(new Vector(0.25F, 0.25F, 0.25F)).multiply(2.5D));
			player.setCooldown(Material.FISHING_ROD, 10);
			
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onFall(EntityDamageEvent event) {
		if(event.getCause().equals(DamageCause.FALL) && event.getEntityType().equals(EntityType.PLAYER)) {
			Player player = (Player)event.getEntity();
			
			ItemStack rod;
			if(player.getInventory().getItemInMainHand().getType().equals(Material.FISHING_ROD)) {
				rod = player.getInventory().getItemInMainHand();
			} else {
				rod = player.getInventory().getItemInOffHand();
			}
			
			if(rod != null && rod.getType().equals(Material.FISHING_ROD)) {
				if(rod.getItemMeta().getDisplayName() != null && rod.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Grappin")) {
					event.setCancelled(true);
				}
			}
		}
	}
}
