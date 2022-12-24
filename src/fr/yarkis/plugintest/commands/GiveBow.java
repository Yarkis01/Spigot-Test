package fr.yarkis.plugintest.commands;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveBow implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			ItemStack bow = new ItemStack(Material.BOW);
			ItemMeta meta = bow.getItemMeta();
			
			meta.setDisplayName("Teleportation");
			meta.setLore(Arrays.asList("Permet de téléporter ton gros cul là"));
			
			bow.setItemMeta(meta);
			bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 666);
			
			player.getInventory().addItem(bow);
		}
		
		return true;
	}
}
