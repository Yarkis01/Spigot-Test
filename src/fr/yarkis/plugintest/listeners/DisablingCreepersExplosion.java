package fr.yarkis.plugintest.listeners;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;

public class DisablingCreepersExplosion implements Listener {

	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) {
		if(event.getEntityType() == EntityType.CREEPER) {
			Creeper creeper = (Creeper)event.getEntity();
			
			creeper.getWorld().dropItem(creeper.getLocation(), new ItemStack(Material.POPPY));
			creeper.getWorld().spawnParticle(Particle.HEART, creeper.getLocation(), 10, 1.0D, 1.0D, 1.0D);
			
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onCreeperDamage(EntityDamageByEntityEvent event) {
		if(event.getEntityType() == EntityType.PLAYER && event.getDamager().getType() == EntityType.CREEPER && event.getCause() == DamageCause.ENTITY_EXPLOSION) {
			event.setCancelled(true);
		}
	}
	
}
