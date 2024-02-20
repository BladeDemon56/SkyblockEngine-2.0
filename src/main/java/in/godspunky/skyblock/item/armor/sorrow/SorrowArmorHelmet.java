package in.godspunky.skyblock.item.armor.sorrow;

import in.godspunky.skyblock.item.*;
import in.godspunky.skyblock.item.*;

public class SorrowArmorHelmet implements ToolStatistics, MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Sorrow Helmet";
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
        return SpecificItemType.HELMET;
    }

    @Override
    public double getBaseMagicFind() {
        return 0.05;
    }

    @Override
    public double getBaseDefense() {
        return 100.0;
    }
}
