package fr.yarkis.plugintest.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.yarkis.plugintest.Main;

public class BlackHole implements Listener {
	private Main plugin;
	
	public BlackHole(Main main) {
		this.plugin = main;
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(World world : plugin.getServer().getWorlds()) {
					for(Creeper creeper : world.getEntitiesByClass(Creeper.class)) {
						if(creeper.getName().equals(ChatColor.BLACK + "Black Hole")) {
							if(creeper.getTicksLived() >= 60 * 20) {
								creeper.remove();
								creeper.getWorld().createExplosion(creeper.getLocation(), 32.0f);
								return;
							}
							
							creeper.getWorld().spawnParticle(Particle.CLOUD, creeper.getLocation(), 6 * (creeper.getTicksLived() / 60), 4.0D, 4.0D, 4.0D);
							creeper.getWorld().playSound(creeper.getLocation(), Sound.ENTITY_TNT_PRIMED, 8.0f, 8.0f);
							
							for(Entity entity : creeper.getNearbyEntities(16.0f * (creeper.getTicksLived() / 120), 16.0f * (creeper.getTicksLived() / 120),  16.0f * (creeper.getTicksLived() / 120))) {
								if(entity instanceof Player) {
									continue;
								}
								
								entity.setVelocity(new Vector(
									(creeper.getLocation().getX() - entity.getLocation().getX()) / 4f, 
									(creeper.getLocation().getY() - entity.getLocation().getY()) / 2f, 
									(creeper.getLocation().getZ() - entity.getLocation().getZ()) / 4f
								));
							}
						}
					}
				}
			}

		}.runTaskTimer(plugin, 0, 5);
	}

	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if(event.getEntityType() == EntityType.CREEPER && event.getSpawnReason() == SpawnReason.SPAWNER_EGG) {
			Creeper creeper = (Creeper)event.getEntity();
			
			if(!creeper.getName().equals(ChatColor.BLACK + "Black Hole")) {
				return;
			}
			
			creeper.setAI(false);
			creeper.setGravity(false);
			creeper.setNoDamageTicks(120 * 20);
			creeper.setRemoveWhenFarAway(false);
			creeper.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 120 * 20, 1), true);
		}
	}

}
