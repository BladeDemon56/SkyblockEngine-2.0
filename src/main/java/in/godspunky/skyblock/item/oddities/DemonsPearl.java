package in.godspunky.skyblock.item.oddities;

import in.godspunky.skyblock.item.*;
import org.bukkit.ChatColor;
import in.godspunky.skyblock.item.*;

public class DemonsPearl implements MaterialStatistics, MaterialFunction, SkullStatistics {
    @Override
    public String getDisplayName() {
        return "Voidlings's Demon Pearl";
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
        return false;
    }

    @Override
    public String getLore() {
        return "A Deep Dark Ender Pearl containing mystical powers from the Voidlings, dropped from the " + ChatColor.DARK_PURPLE + "Voidgloom Seraph";
    }

    @Override
    public String getURL() {
        return "38be8abd66d09a58ce12d377544d726d25cad7e979e8c2481866be94d3b32f";
    }

    @Override
    public boolean isStackable() {
        return false;
    }
}
