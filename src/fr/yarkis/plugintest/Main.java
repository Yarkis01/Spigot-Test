package fr.yarkis.plugintest;

import org.bukkit.plugin.java.JavaPlugin;

import fr.yarkis.plugintest.commands.GiveBow;
import fr.yarkis.plugintest.commands.GivePickaxe;
import fr.yarkis.plugintest.commands.GiveTotem;
import fr.yarkis.plugintest.listeners.*;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		// Pioche Cheaté
		this.getCommand("pioche").setExecutor(new GivePickaxe());
		getServer().getPluginManager().registerEvents(new DiamondPickaxe(this), this);
		
		// Bow TP
		this.getCommand("bow").setExecutor(new GiveBow());
		getServer().getPluginManager().registerEvents(new BowTP(), this);
		
		// Aether Pearl
		getServer().getPluginManager().registerEvents(new AetherPearl(), this);
		
		// Totem de réparation
		this.getCommand("totem").setExecutor(new GiveTotem());
		getServer().getPluginManager().registerEvents(new TotemReparateur(this), this);
		
		System.out.println("Plugin start");
	}
	
	@Override
	public void onDisable() {
		System.out.println("Plugin stop");
	}
}