package fr.yarkis.plugintest.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveGrappin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			ItemStack grappin = new ItemStack(Material.FISHING_ROD);
			ItemMeta  meta    = grappin.getItemMeta();
			
			meta.setDisplayName(ChatColor.AQUA + "Grappin");
			meta.setUnbreakable(true);
			
			grappin.setItemMeta(meta);
			grappin.addEnchantment(Enchantment.DURABILITY, 3);
			
			player.getInventory().addItem(grappin);
		}
		
		return true;
	}

}
