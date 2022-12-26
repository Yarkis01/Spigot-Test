package fr.yarkis.plugintest.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveAetherPearl implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			ItemStack pearl = new ItemStack(Material.ENDER_PEARL);
			ItemMeta meta   = pearl.getItemMeta();
			
			meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Aether Pearl");
			pearl.setItemMeta(meta);
			
			player.getInventory().addItem(pearl);
		}
		
		return true;
	}
}
