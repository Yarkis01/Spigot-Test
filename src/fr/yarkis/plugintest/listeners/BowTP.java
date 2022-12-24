package fr.yarkis.plugintest.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class BowTP implements Listener {
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		if(event.getEntityType().equals(EntityType.ARROW)) {
			Arrow arrow = (Arrow)event.getEntity();
			
			if (arrow.getShooter() instanceof Player) {
				Player player = (Player) arrow.getShooter();
				
				ItemStack bow;
				if(player.getInventory().getItemInMainHand().getType().equals(Material.BOW)) {
					bow = player.getInventory().getItemInMainHand();
				} else {
					bow = player.getInventory().getItemInOffHand();
				}
				
				if(bow.getItemMeta().getDisplayName().equals("Teleportation")) {
					arrow.remove();
					player.teleport(arrow.getLocation());
					player.sendMessage(ChatColor.GREEN + "Téléportation !");
				}
			}
		}
	}
	
}
