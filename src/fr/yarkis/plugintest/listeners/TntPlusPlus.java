package fr.yarkis.plugintest.listeners;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import fr.yarkis.plugintest.Main;

public class TntPlusPlus implements Listener {
	private Main plugin;

	public TntPlusPlus(Main main) {
		this.plugin = main;
		
		ItemStack TNT = new ItemStack(Material.TNT, 1);
		ItemMeta meta = TNT.getItemMeta();
		
		meta.setDisplayName(ChatColor.RED + "TNT++");
		meta.setLore(Arrays.asList("Ce n'est pas une simple TNT...", "C'est " + ChatColor.BOLD + "la TNT"));
		
		TNT.setItemMeta(meta);
		
		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "tntplusplus"), TNT);
		recipe.shape("TNT", "NTN", "TNT");
		recipe.setIngredient('T', Material.TNT);
		recipe.setIngredient('N', Material.GUNPOWDER);
		
		Bukkit.addRecipe(recipe);
	}
	
	public boolean checkTntPlusPlus(Block block) {
		if(block.getMetadata("tntplusplus").size() == 0) {
			return false;
		}

		return block.getMetadata("tntplusplus").get(0).asBoolean();
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if(event.getItemInHand().getItemMeta().getDisplayName() != null && event.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "TNT++")) {
			if(event.getBlockPlaced().getType() != Material.TNT) {
				return;
			}
			
			event.getBlockPlaced().setMetadata("tntplusplus", new FixedMetadataValue(plugin, true));
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(checkTntPlusPlus(event.getBlock()) && event.getBlock().getType() == Material.TNT) {
			event.setCancelled(true);
			
			Block block = event.getBlock();
			block.setType(Material.AIR);
			
			block.getWorld().createExplosion(block.getLocation(), 32.0f);
		}
	}

}
