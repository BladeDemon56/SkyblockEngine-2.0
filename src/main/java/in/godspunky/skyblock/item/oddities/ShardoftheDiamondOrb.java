package in.godspunky.skyblock.item.oddities;

import in.godspunky.skyblock.item.*;
import in.godspunky.skyblock.item.*;

public class ShardoftheDiamondOrb implements MaterialStatistics, MaterialFunction, SkullStatistics {
    @Override
    public String getDisplayName() {
        return "Shard of The Diamond Orb";
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
        return "e69d68101df62933b23d1c82340a760ea77f20ada14aff7b80b47a57ec290c38";
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    @Override
    public String getLore() {
        return "A Powerful shard from Dimoon's Remnant, dropped by the Atoned Horror";
    }
}