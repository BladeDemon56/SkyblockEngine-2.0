package in.godspunky.skyblock.item.armor.hardened;

import in.godspunky.skyblock.item.*;
import in.godspunky.skyblock.item.*;

public class HardenedDiamondChestplate implements ToolStatistics, MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Hardened Diamond Chestplate";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.RARE;
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
    public double getBaseDefense() {
        return 120.0;
    }
}
