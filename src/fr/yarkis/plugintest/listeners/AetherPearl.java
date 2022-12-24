package fr.yarkis.plugintest.listeners;

import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class AetherPearl implements Listener {
	
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent event) {
		if(event.getEntityType().equals(EntityType.ENDER_PEARL)) {
			EnderPearl pearl = (EnderPearl)event.getEntity();
			
			if(!(pearl.getShooter() instanceof Player)) {
				return;
			}
			
			Player player = (Player)pearl.getShooter();
			
			pearl.addPassenger(player);
			
			if(pearl.isOnGround()) {
				pearl.removePassenger(player);
			}
		}
	}
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		if(event.getCause().equals(TeleportCause.ENDER_PEARL)) {
			event.setCancelled(true);
		}
	}

}
