package fr.yarkis.plugintest;

import org.bukkit.plugin.java.JavaPlugin;

import fr.yarkis.plugintest.commands.*;
import fr.yarkis.plugintest.listeners.*;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		// Pioche Cheaté
		this.getCommand("pioche").setExecutor(new GivePickaxe());
		getServer().getPluginManager().registerEvents(new DiamondPickaxe(this), this);
		
		// Bow TP
		this.getCommand("bow-tp").setExecutor(new GiveBow());
		getServer().getPluginManager().registerEvents(new BowTP(), this);
		
		// Aether Pearl
		this.getCommand("aether-pearl").setExecutor(new GiveAetherPearl());
		getServer().getPluginManager().registerEvents(new AetherPearl(this), this);
		
		// Totem de réparation
		this.getCommand("totem").setExecutor(new GiveTotem());
		getServer().getPluginManager().registerEvents(new TotemReparateur(this), this);
		
		// Arc qui tire droit
		this.getCommand("bow").setExecutor(new GiveArcQuiTireToutDroit());
		getServer().getPluginManager().registerEvents(new ArcQuiTireToutDroit(), this);
		
		// Grappin
		this.getCommand("grappin").setExecutor(new GiveGrappin());
		getServer().getPluginManager().registerEvents(new Grappin(), this);
		
		// Explosion des creepers
		getServer().getPluginManager().registerEvents(new DisablingCreepersExplosion(), this);
		
		// Black Hole
		this.getCommand("blackhole").setExecutor(new GiveBlackHole());
		getServer().getPluginManager().registerEvents(new BlackHole(this), this);
		
		// Tnt++
		getServer().getPluginManager().registerEvents(new TntPlusPlus(this), this);
		
		System.out.println("Plugin start");
	}
	
	@Override
	public void onDisable() {
		System.out.println("Plugin stop");
	}
}
