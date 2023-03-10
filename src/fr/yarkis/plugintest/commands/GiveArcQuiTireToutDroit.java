package fr.yarkis.plugintest.commands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveArcQuiTireToutDroit implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player)sender;
			
			ItemStack bow = new ItemStack(Material.BOW);
			ItemMeta meta = bow.getItemMeta();
			
			meta.setDisplayName(ChatColor.BLUE + "Arc");
			meta.setLore(Arrays.asList("Un arc qui tire tout simplement droit"));
			
			bow.setItemMeta(meta);
			bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
			
			player.getInventory().addItem(bow);
		}
		
		return true;
	}
	
}
