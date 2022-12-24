package fr.yarkis.plugintest.commands;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GivePickaxe implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player)sender;
			
			ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
			ItemMeta  meta    = pickaxe.getItemMeta();
			
			meta.setDisplayName("Pioche Cheaté");
			meta.setUnbreakable(true);
			meta.setLore(Arrays.asList("Explosion: 1/10", "Haste: 1/5", "Speed: 1/5"));
			
			pickaxe.setItemMeta(meta);
			
			player.getInventory().addItem(pickaxe);
		}
		
		return true;
	}
	
}
