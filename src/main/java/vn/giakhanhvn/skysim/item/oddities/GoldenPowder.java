package vn.giakhanhvn.skysim.item.oddities;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.MaterialStatistics;

public class GoldenPowder implements MaterialStatistics, MaterialFunction
{
    @Override
    public String getDisplayName() {
        return "Golden Powder";
    }
    
    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
    }
    
    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }
    
    @Override
    public boolean isEnchanted() {
        return true;
    }
}
