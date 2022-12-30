package fr.yarkis.plugintest.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import fr.yarkis.plugintest.Main;

public class Gun implements Listener {
	private List<Integer> bullets 			 = new ArrayList<>();
	private List<Player> players_need_reload = new ArrayList<>();
	
	private Main plugin;
	
	public Gun(Main main) {
		this.plugin = main;
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(Iterator<Player> iterator = players_need_reload.iterator(); iterator.hasNext();) {
					Player player = iterator.next();
					
					if(player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_HOE) {
						ItemStack item  = player.getInventory().getItemInMainHand();
						Damageable meta = (Damageable)item.getItemMeta();
						
						if(item.getItemMeta().getDisplayName() == null || !item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Piou Piou")) {
							continue;
						}
						if(meta.getDamage() == 0) {
							item.setAmount(16);
							iterator.remove();
							continue;
						} else {
							meta.setDamage(meta.getDamage() - 78);
							item.setItemMeta((ItemMeta)meta);
						}
					}
				}
			}
		}.runTaskTimer(plugin, 0, 1);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = (Player)event.getPlayer();
		
		if(event.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_HOE) {
			ItemStack  hoe  = player.getInventory().getItemInMainHand();
			Damageable meta = (Damageable) hoe.getItemMeta();
			
			if(hoe.getItemMeta().getDisplayName() == null || !hoe.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Piou Piou")) {
				return;
			}
			
			event.setCancelled(true);
			
			if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
				if(players_need_reload.contains(player)) {
					player.sendMessage(ChatColor.RED + "Vous êtes déjà entrain de recharger !");
				} else {
					hoe.setAmount(1);
					
					meta.setDamage(1560);
					hoe.setItemMeta((ItemMeta)meta);
					
					players_need_reload.add(player);
				}
			} else if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(meta.getDamage() == 0) {
					if(hoe.getAmount() != 1) {
						hoe.setAmount(hoe.getAmount() - 1);
					} else {
						meta.setDamage(1560);
						hoe.setItemMeta((ItemMeta)meta);
						players_need_reload.add(player);
					}
					
					Snowball bullet = player.launchProjectile(Snowball.class);
					bullet.setVelocity(player.getLocation().getDirection().multiply(2.0D));
					
					bullets.add(bullet.getEntityId());
				} else {
					player.sendMessage(ChatColor.RED + "Vous n'avez plus de munition !");
				}
			}
		}
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		if(event.getEntityType() == EntityType.SNOWBALL) {
			Snowball bullet = (Snowball)event.getEntity();
			
			if(bullets.contains(bullet.getEntityId())) {
				bullets.remove(bullets.indexOf(bullet.getEntityId()));
				
				bullet.getWorld().createExplosion(bullet.getLocation().getX(), bullet.getLocation().getY(), bullet.getLocation().getZ(), 8.0f, false, false);
			}
		}
	}
	
}
