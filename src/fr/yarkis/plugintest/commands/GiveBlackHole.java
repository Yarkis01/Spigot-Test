package fr.yarkis.plugintest.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveBlackHole implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player)sender;
			
			ItemStack blackHole = new ItemStack(Material.CREEPER_SPAWN_EGG);
			ItemMeta meta 		= blackHole.getItemMeta();
			
			meta.setDisplayName(ChatColor.BLACK + "Black Hole");
			
			blackHole.setItemMeta(meta);
			player.getInventory().addItem(blackHole);
		}
		
		return true;
	}

}
