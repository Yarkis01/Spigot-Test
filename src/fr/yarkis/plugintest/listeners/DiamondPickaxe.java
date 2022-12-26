package fr.yarkis.plugintest.listeners;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.yarkis.plugintest.Main;

public class DiamondPickaxe implements Listener {
	private Inventory inventory;
	private Main plugin;
	
	public DiamondPickaxe(Main main) {
		 inventory = Bukkit.createInventory(null, 9, "Amélioration");
		 
		 this.plugin = main;
		 
		 new BukkitRunnable(){
			 @Override
			 public void run(){
				 for(Player player : plugin.getServer().getOnlinePlayers()) {
					 ItemStack item = player.getInventory().getItemInMainHand();
					 
					 if(item == null || item.getType().equals(Material.AIR)) {
						 return;
					 }
					 
					 if(item.getItemMeta().getDisplayName().equals(ChatColor.RED + "Pioche Cheaté")) {
						 ItemMeta meta = item.getItemMeta();
						 
						 Integer haste_level = Integer.parseInt(meta.getLore().get(1).replace("Haste: ", "").replace("/5", "")) - 1;
						 Integer speed_level = Integer.parseInt(meta.getLore().get(2).replace("Speed: ", "").replace("/5", "")) - 1;
						 
						 player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, haste_level, false, false));
						 player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, speed_level, false, false));
					 }
				 }
			 }
		 }.runTaskTimer(plugin, 1, 20); 
	}
	
	public ItemStack CreateGuiItem(Material material, String name, String... lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta  meta = item.getItemMeta();
		
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		
		item.setItemMeta(meta);
		item.setAmount(1);
		
		return item;
	}
	
	@EventHandler
	public void onInventoryDrag(InventoryDragEvent event) {
		if(event.getInventory().equals(inventory)) {
			event.setCancelled(true);
		}
	}
	
	public String addLevelOn(Player player, String lore, String upgrade, Integer max_level) {
		Integer level = Integer.parseInt(lore.replace(upgrade + ": ", "").replace("/" + max_level.toString(), ""));
		
		if(level == max_level) {
			player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 3F, 0.5F);
			player.sendMessage(ChatColor.RED + "Vous ne pouvez pas améliorer " + upgrade + " au dessus du niveau " + max_level.toString());
		} else {
			level += 1;
			
			lore = upgrade + ": " + level.toString() + "/" + max_level.toString();
			
			player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 3F, 0.5F);
			player.sendMessage(ChatColor.GREEN + "Vous avez améliorer " + upgrade + " au niveau " + level.toString());
		}
		
		return lore;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if(!event.getInventory().equals(inventory)) {
			return;
		}
		
		event.setCancelled(true);

		ItemStack clickedItem = event.getCurrentItem();
		if(clickedItem == null || clickedItem.getType().equals(Material.AIR)) {
			return;
		}

		Player player = (Player)event.getWhoClicked();
		
		ItemStack pickaxe = player.getInventory().getItemInMainHand();
		ItemMeta  meta    = pickaxe.getItemMeta();
		
		String explosion_lore = meta.getLore().get(0);
		String haste_lore	  = meta.getLore().get(1);
		String speed_lore	  = meta.getLore().get(2);
		
		if(clickedItem.getType().equals(Material.TNT)) {
			explosion_lore = addLevelOn(player, explosion_lore, "Explosion", 10);
		} else if(clickedItem.getType().equals(Material.GOLDEN_PICKAXE)) {
			haste_lore = addLevelOn(player, haste_lore, "Haste", 5);
		} else if(clickedItem.getType().equals(Material.SUGAR)) {
			speed_lore = addLevelOn(player, speed_lore, "Speed", 5);
		}
		
		meta.setLore(Arrays.asList(explosion_lore, haste_lore, speed_lore));
		pickaxe.setItemMeta(meta);
		
		player.closeInventory();
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = (Player)event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		if(item.getType().equals(Material.DIAMOND_PICKAXE)) {
			ItemMeta meta = item.getItemMeta();
			
			if(!meta.getDisplayName().equals(ChatColor.RED + "Pioche Cheaté")) {
				return;
			}
	
			if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				inventory.setItem(0, CreateGuiItem(Material.TNT, "Explosion !", "Niveau: " + meta.getLore().get(0).replace("Explosion: ", "")));
				inventory.setItem(1, CreateGuiItem(Material.GOLDEN_PICKAXE, "Haste", "Niveau: " + meta.getLore().get(1).replace("Haste: ", "")));
				inventory.setItem(2, CreateGuiItem(Material.SUGAR, "Speed", "Niveau: " + meta.getLore().get(2).replace("Speed: ", "")));
				
				player.openInventory(inventory);
			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		
		if(player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_PICKAXE)) {
			ItemStack pickaxe = player.getInventory().getItemInMainHand();
			
			if(pickaxe.getItemMeta().getDisplayName() != null && pickaxe.getItemMeta().getDisplayName().equals(ChatColor.RED + "Pioche Cheaté")) {
				player.getWorld().createExplosion(event.getBlock().getLocation(), Float.parseFloat(pickaxe.getItemMeta().getLore().get(0).replace("Explosion: ", "").replace("/10", "")));
			}
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if(event.getEntityType().equals(EntityType.PLAYER) && event.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
			event.setCancelled(true);
		}
	}
}
