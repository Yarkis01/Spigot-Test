package fr.yarkis.plugintest.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import fr.yarkis.plugintest.Main;

public class TotemReparateur implements Listener {
	private Main plugin;
	
	public TotemReparateur(Main main) {
		this.plugin = main;
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(Player player : plugin.getServer().getOnlinePlayers()) {
					ItemStack left_hand_item = player.getInventory().getItemInOffHand();
					
					if(left_hand_item == null || left_hand_item.getType().equals(Material.AIR)) {
						return;
					}
					
					if(left_hand_item.getType().equals(Material.WITHER_ROSE) && left_hand_item.getItemMeta().getDisplayName().equals(ChatColor.BLACK + "Totem de Réparation")) {
						for (ItemStack item : player.getInventory().getContents()) {
							if(item == null || item.getType().equals(Material.AIR)) {
								continue;
							}
							
							Damageable meta = (Damageable)item.getItemMeta();
							
							if(meta.getDamage() == 0) {
								continue;
							}
							
							meta.setDamage(meta.getDamage() - 1);
							item.setItemMeta((ItemMeta)meta);
						}
					}
				}
			}
		}.runTaskTimer(plugin, 1, 20);
	}
}
