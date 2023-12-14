package vn.giakhanhvn.skysim.dimoon.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.util.Vector;
import org.bukkit.entity.LivingEntity;
import vn.giakhanhvn.skysim.util.SUtil;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.Listener;

public class EntityListener implements Listener
{
    @EventHandler
    public void onEntityChangeBlock(final EntityChangeBlockEvent event) {
        if (event.getEntity() instanceof FallingBlock) {
            final FallingBlock fallingBlock = (FallingBlock)event.getEntity();
            if (fallingBlock.getWorld().getName().equalsIgnoreCase("arena")) {
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
                fallingBlock.getWorld().playSound(fallingBlock.getLocation(), Sound.DIG_STONE, 1.0f, 1.0f);
                for (int i = 0; i < 20; ++i) {
                    fallingBlock.getWorld().spigot().playEffect(fallingBlock.getLocation().clone().add(0.0, 0.0, 0.0), Effect.EXPLOSION, 0, 1, (float)SUtil.random(-0.5, 0.5), (float)SUtil.random(-0.5, 0.5), (float)SUtil.random(-0.5, 0.5), 0.0f, 1, 100);
                }
                event.getBlock().getLocation().getWorld().getNearbyEntities(event.getBlock().getLocation(), 0.5, 0.5, 0.5).forEach(entity -> {
                    if (entity instanceof LivingEntity) {
                        entity.setVelocity(new Vector(Math.random() - Math.random() * 2.0, Math.random() - Math.random() * 2.0, Math.random() - Math.random() * 2.0));
                    }
                });
            }
        }
    }
    
    @EventHandler
    public void onEntityDeath(final EntityDeathEvent event) {
        if (event.getEntity().hasMetadata("Dimoon")) {
            event.getDrops().clear();
        }
    }
}
