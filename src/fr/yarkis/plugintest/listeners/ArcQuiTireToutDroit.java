package fr.yarkis.plugintest.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

public class ArcQuiTireToutDroit implements Listener {

	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent event) {
		if(event.getEntityType() != EntityType.ARROW) {
			return;
		}
		
		Arrow arrow = (Arrow)event.getEntity();
		
		if(arrow.getShooter() instanceof Player) {
			Player player = (Player)arrow.getShooter();
			
			ItemStack bow;
			if(player.getInventory().getItemInMainHand().getType().equals(Material.BOW) || player.getInventory().getItemInMainHand().getType().equals(Material.CROSSBOW)) {
				bow = player.getInventory().getItemInMainHand();
			} else {
				bow = player.getInventory().getItemInOffHand();
			}
			
			if(bow.getItemMeta().getDisplayName() != null && bow.getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Arc")) {
				arrow.setGravity(false);
				arrow.setVelocity(player.getLocation().getDirection().multiply(2.0D));
			}
		}
	}
	
}
