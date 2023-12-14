package vn.giakhanhvn.skysim.item.dragon.wise;

import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.armor.LeatherArmorStatistics;
import vn.giakhanhvn.skysim.item.MaterialFunction;

public class WiseDragonChestplate implements MaterialFunction, LeatherArmorStatistics
{
    @Override
    public double getBaseIntelligence() {
        return 75.0;
    }
    
    @Override
    public double getBaseHealth() {
        return 120.0;
    }
    
    @Override
    public double getBaseDefense() {
        return 160.0;
    }
    
    @Override
    public int getColor() {
        return 2748649;
    }
    
    @Override
    public String getDisplayName() {
        return "Wise Dragon Chestplate";
    }
    
    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }
    
    @Override
    public GenericItemType getType() {
        return GenericItemType.ARMOR;
    }
    
    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.CHESTPLATE;
    }
    
    @Override
    public String getLore() {
        return null;
    }
}
