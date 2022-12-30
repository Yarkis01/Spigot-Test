package fr.yarkis.plugintest.commands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveGun implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			ItemStack gun = new ItemStack(Material.DIAMOND_HOE, 16);
			ItemMeta meta = gun.getItemMeta();
			
			meta.setDisplayName(ChatColor.GOLD + "Piou Piou");
			meta.setLore(Arrays.asList("Un pistolet qui fait piou piou"));
			
			gun.setItemMeta(meta);
			player.getInventory().addItem(gun);
		}
		
		return true;
	}

}
