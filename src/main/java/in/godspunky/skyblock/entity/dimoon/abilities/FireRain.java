package in.godspunky.skyblock.entity.dimoon.abilities;

import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import in.godspunky.skyblock.SkyBlock;
import in.godspunky.skyblock.entity.dimoon.Dimoon;

public class FireRain implements Ability {
    @Override
    public void activate(final Player player, final Dimoon dimoon) {
        new BukkitRunnable() {
            int counter = 0;

            public void run() {
                final LivingEntity entity = dimoon.getEntity();
                final Fireball fireball = (Fireball) entity.getWorld().spawn(entity.getEyeLocation().add(entity.getEyeLocation().getDirection().multiply(3.0)), (Class) Fireball.class);
                fireball.setMetadata("FBL", new FixedMetadataValue(SkyBlock.getPlugin(), true));
                fireball.setDirection(player.getLocation().getDirection().multiply(-1.0).normalize());
                ++this.counter;
                if (this.counter == 10) {
                    this.cancel();
                }
            }
        }.runTaskTimer(SkyBlock.getPlugin(), 0L, 20L);
    }

    @Override
    public int getCooldown() {
        return 60;
    }
}