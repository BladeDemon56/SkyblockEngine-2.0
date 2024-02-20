package in.godspunky.skyblock.item.oddities;

import in.godspunky.skyblock.item.*;
import in.godspunky.skyblock.item.*;

public class ShardoftheShredded implements MaterialStatistics, MaterialFunction, SkullStatistics {
    @Override
    public String getDisplayName() {
        return "Shard of The Shredded";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
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
    public String getURL() {
        return "b10857e872711e7e6925c9ef1115353b47f58ef07eb5c2d565f1f86023e7a284";
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    @Override
    public String getLore() {
        return "The core of a powerful weapon, dropped by the Atoned Horror.";
    }
}
