package vn.giakhanhvn.skysim.item.armor.minichad;

import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.util.Sputnik;
import vn.giakhanhvn.skysim.item.armor.ArmorSet;

public class MinichadSet implements ArmorSet
{
    @Override
    public String getName() {
        return "Minichad";
    }
    
    @Override
    public String getDescription() {
        return Sputnik.trans("&7Increase most of your stats by &a10%&7. Beautiful, my little comrade!");
    }
    
    @Override
    public Class<? extends MaterialStatistics> getHelmet() {
        return MinichadHelmet.class;
    }
    
    @Override
    public Class<? extends MaterialStatistics> getChestplate() {
        return MinichadChestplate.class;
    }
    
    @Override
    public Class<? extends MaterialStatistics> getLeggings() {
        return MinichadLeggings.class;
    }
    
    @Override
    public Class<? extends MaterialStatistics> getBoots() {
        return MinichadBoots.class;
    }
}
