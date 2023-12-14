package vn.giakhanhvn.skysim.item.armor.miner;

import vn.giakhanhvn.skysim.user.DoublePlayerStatistic;
import vn.giakhanhvn.skysim.util.Groups;
import org.bukkit.entity.Entity;
import vn.giakhanhvn.skysim.region.Region;
import vn.giakhanhvn.skysim.listener.PlayerListener;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import vn.giakhanhvn.skysim.item.SItem;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.armor.TickingSet;

public class MinerSet implements TickingSet
{
    @Override
    public String getName() {
        return "Regeneration";
    }
    
    @Override
    public String getDescription() {
        return "Regenerates 5% of your max Health every second if you have been out of combat for the last 8 seconds.";
    }
    
    @Override
    public Class<? extends MaterialStatistics> getHelmet() {
        return MinerHelmet.class;
    }
    
    @Override
    public Class<? extends MaterialStatistics> getChestplate() {
        return MinerChestplate.class;
    }
    
    @Override
    public Class<? extends MaterialStatistics> getLeggings() {
        return MinerLeggings.class;
    }
    
    @Override
    public Class<? extends MaterialStatistics> getBoots() {
        return MinerBoots.class;
    }
    
    @Override
    public void tick(final Player owner, final SItem helmet, final SItem chestplate, final SItem leggings, final SItem boots, final List<AtomicInteger> counters) {
        final PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(owner.getUniqueId());
        final DoublePlayerStatistic defense = statistics.getDefense();
        final PlayerListener.CombatAction action = PlayerListener.getLastCombatAction(owner);
        counters.get(0).incrementAndGet();
        if ((action == null || (action.getTimeStamp() + 8000L <= System.currentTimeMillis() && helmet != null && chestplate != null && leggings != null && boots != null)) && counters.get(0).get() >= 2) {
            owner.setHealth(Math.min(owner.getMaxHealth(), owner.getHealth() + owner.getMaxHealth() * 0.05));
            counters.get(0).set(0);
        }
        final Region region = Region.getRegionOfEntity((Entity)owner);
        if (region == null) {
            return;
        }
        if (!Groups.DEEP_CAVERNS_REGIONS.contains(region.getType())) {
            return;
        }
        if (helmet != null) {
            defense.add(8, Double.valueOf(45.0));
        }
        if (chestplate != null) {
            defense.add(8, Double.valueOf(95.0));
        }
        if (leggings != null) {
            defense.add(8, Double.valueOf(70.0));
        }
        if (boots != null) {
            defense.add(8, Double.valueOf(45.0));
        }
    }
}
