package net.hypixel.skyblock.entity.caverns;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import net.hypixel.skyblock.SkyBlock;
import net.hypixel.skyblock.entity.EntityFunction;
import net.hypixel.skyblock.entity.SlimeStatistics;

public class MediumSlime implements SlimeStatistics, EntityFunction {
    @Override
    public String getEntityName() {
        return "Emerald Slime";
    }

    @Override
    public double getEntityMaxHealth() {
        return 150.0;
    }

    @Override
    public double getDamageDealt() {
        return 100.0;
    }
    
    @Override
    public int mobLevel() {
        return 10;
    }

    @Override
    public int getSize() {
        return 7;
    }

    @Override
    public void onAttack(final EntityDamageByEntityEvent e) {
        new BukkitRunnable() {
            public void run() {
                e.getEntity().setVelocity(e.getEntity().getVelocity().clone().setY(1.5));
            }
        }.runTaskLater(SkyBlock.getPlugin(), 1L);
    }

    @Override
    public double getXPDropped() {
        return 15.0;
    }
}
