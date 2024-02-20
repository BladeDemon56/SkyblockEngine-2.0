package in.godspunky.skyblock.item.pickaxe.vanilla;

import in.godspunky.skyblock.item.*;
import in.godspunky.skyblock.item.*;

public class StonePickaxe implements ToolStatistics, MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Stone Pickaxe";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public int getBaseDamage() {
        return 20;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.TOOL;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.PICKAXE;
    }
}
