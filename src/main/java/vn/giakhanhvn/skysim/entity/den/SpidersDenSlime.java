package vn.giakhanhvn.skysim.entity.den;

import org.bukkit.plugin.Plugin;
import vn.giakhanhvn.skysim.SkySimEngine;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.entity.EntityFunction;
import vn.giakhanhvn.skysim.entity.SlimeStatistics;

public class SpidersDenSlime implements SlimeStatistics, EntityFunction
{
    @Override
    public String getEntityName() {
        return "Slime";
    }
    
    @Override
    public double getEntityMaxHealth() {
        return SUtil.random(200.0, 400.0);
    }
    
    @Override
    public double getDamageDealt() {
        return 140.0;
    }
    
    @Override
    public int getSize() {
        return 9;
    }
    
    @Override
    public void onAttack(final EntityDamageByEntityEvent e) {
        new BukkitRunnable() {
            public void run() {
                e.getEntity().setVelocity(e.getEntity().getVelocity().clone().setY(1.5));
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 1L);
    }
    
    @Override
    public double getXPDropped() {
        return 4.0;
    }
}
