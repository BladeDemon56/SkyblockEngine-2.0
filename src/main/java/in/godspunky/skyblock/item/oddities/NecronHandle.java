package in.godspunky.skyblock.item.oddities;

import in.godspunky.skyblock.item.GenericItemType;
import in.godspunky.skyblock.item.MaterialFunction;
import in.godspunky.skyblock.item.MaterialStatistics;
import in.godspunky.skyblock.item.Rarity;

public class NecronHandle implements MaterialStatistics, MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Necron's Handle";
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

    @Override
    public boolean isStackable() {
        return false;
    }


}