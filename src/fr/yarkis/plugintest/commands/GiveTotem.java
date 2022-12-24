package fr.yarkis.plugintest.commands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveTotem implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player)sender;
			
			ItemStack totem = new ItemStack(Material.WITHER_ROSE);
			ItemMeta meta	= totem.getItemMeta();
			
			meta.setDisplayName(ChatColor.BLACK + "Totem de Réparation");
			meta.setLore(Arrays.asList("Un totem permettant de réparer tous les objets dans votre inventaire,", "et ceux chaque secondes."));
			
			totem.setItemMeta(meta);
			player.getInventory().setItemInOffHand(totem);
			
			
			
			// The following lines of code are only there to give an equipment to try the totem, and can totally be removed
			ItemStack helmet 	 = new ItemStack(Material.DIAMOND_HELMET); 
			
			Damageable meta_low_dura = (Damageable)helmet.getItemMeta();
			meta_low_dura.setDamage(50);
			
			helmet.setItemMeta((ItemMeta)meta_low_dura);
			
			ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
			chestplate.setItemMeta((ItemMeta)meta_low_dura);
			
			ItemStack leggings = new ItemStack(Material.GOLDEN_LEGGINGS);
			leggings.setItemMeta((ItemMeta)meta_low_dura);
			
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			boots.setItemMeta((ItemMeta)meta_low_dura);
			
			player.getInventory().setHelmet(helmet);
			player.getInventory().setChestplate(chestplate);
			player.getInventory().setLeggings(leggings);
			player.getInventory().setBoots(boots);
			
			ItemStack sword = new ItemStack(Material.STONE_SWORD);
			sword.setItemMeta((ItemMeta)meta_low_dura);
			
			player.getInventory().addItem(sword);
		}
		
		return true;
	}
}
