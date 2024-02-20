package in.godspunky.skyblock.item.hoe.vanilla;

import in.godspunky.skyblock.item.*;
import in.godspunky.skyblock.item.*;

public class WoodenHoe implements ToolStatistics, MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Wooden Hoe";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.TOOL;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.HOE;
    }
}
