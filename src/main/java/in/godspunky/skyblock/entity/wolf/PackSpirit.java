package in.godspunky.skyblock.entity.wolf;

import net.md_5.bungee.api.ChatColor;
import in.godspunky.skyblock.entity.EntityDrop;
import in.godspunky.skyblock.entity.EntityDropType;
import in.godspunky.skyblock.item.SMaterial;

import java.util.Arrays;
import java.util.List;

public class PackSpirit extends BaseWolf {
    @Override
    public String getEntityName() {
        return ChatColor.AQUA + "Pack Spirit";
    }

    @Override
    public double getEntityMaxHealth() {
        return 6000.0;
    }

    @Override
    public double getDamageDealt() {
        return 270.0;
    }

    @Override
    public List<EntityDrop> drops() {
        return Arrays.asList(new EntityDrop(SMaterial.BONE, EntityDropType.COMMON, 0.5), new EntityDrop(SMaterial.OAK_WOOD, EntityDropType.COMMON, 0.1), new EntityDrop(SMaterial.BIRCH_WOOD, EntityDropType.COMMON, 0.1));
    }

    @Override
    public double getXPDropped() {
        return 15.0;
    }

    @Override
    public boolean isAngry() {
        return true;
    }

    @Override
    public int mobLevel() {
        return 30;
    }
}