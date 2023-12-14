package vn.giakhanhvn.skysim.item.armor.sorrow;

import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.ToolStatistics;

public class SorrowArmorBoots implements ToolStatistics, MaterialFunction
{
    @Override
    public String getDisplayName() {
        return "Sorrow Boots";
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
        return SpecificItemType.BOOTS;
    }
    
    @Override
    public double getBaseMagicFind() {
        return 0.05;
    }
    
    @Override
    public double getBaseDefense() {
        return 75.0;
    }
}
