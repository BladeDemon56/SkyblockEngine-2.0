package net.hypixel.skyblock.entity.wolf;

import org.bukkit.ChatColor;
import net.hypixel.skyblock.entity.EntityDrop;
import net.hypixel.skyblock.entity.EntityDropType;
import net.hypixel.skyblock.item.SMaterial;

import java.util.Arrays;
import java.util.List;

public class SoulOfTheAlpha extends BaseWolf {
    @Override
    public String getEntityName() {
        return ChatColor.DARK_AQUA + "Soul of the Alpha";
    }

    @Override
    public double getEntityMaxHealth() {
        return 31150.0;
    }

    @Override
    public double getDamageDealt() {
        return 1282.0;
    }
    
    @Override
    public int mobLevel() {
        return 55;
    }

    @Override
    public List<EntityDrop> drops() {
        return Arrays.asList(new EntityDrop(SMaterial.JUNGLE_WOOD, EntityDropType.GUARANTEED, 1.0), new EntityDrop(SMaterial.WEAK_WOLF_CATALYST, EntityDropType.VERY_RARE, 0.005));
    }

    @Override
    public double getXPDropped() {
        return 50.0;
    }

    @Override
    public boolean isAngry() {
        return true;
    }
}
