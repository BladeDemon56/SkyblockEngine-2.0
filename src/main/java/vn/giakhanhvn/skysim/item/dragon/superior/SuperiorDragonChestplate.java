package vn.giakhanhvn.skysim.item.dragon.superior;

import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.armor.LeatherArmorStatistics;
import vn.giakhanhvn.skysim.item.MaterialFunction;

public class SuperiorDragonChestplate implements MaterialFunction, LeatherArmorStatistics
{
    @Override
    public double getBaseStrength() {
        return 10.0;
    }
    
    @Override
    public double getBaseCritChance() {
        return 0.02;
    }
    
    @Override
    public double getBaseCritDamage() {
        return 0.08;
    }
    
    @Override
    public double getBaseIntelligence() {
        return 25.0;
    }
    
    @Override
    public double getBaseSpeed() {
        return 0.03;
    }
    
    @Override
    public double getBaseHealth() {
        return 150.0;
    }
    
    @Override
    public double getBaseDefense() {
        return 190.0;
    }
    
    @Override
    public int getColor() {
        return 15916817;
    }
    
    @Override
    public String getDisplayName() {
        return "Superior Dragon Chestplate";
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
