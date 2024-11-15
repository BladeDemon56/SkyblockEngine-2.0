package net.hypixel.skyblock.entity.end;

import net.hypixel.skyblock.entity.*;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import net.hypixel.skyblock.entity.*;
import net.hypixel.skyblock.item.SItem;
import net.hypixel.skyblock.item.SMaterial;
import net.hypixel.skyblock.util.SUtil;

import java.util.Arrays;
import java.util.List;

public class Watcher implements EntityFunction, EntityStatistics {
    @Override
    public String getEntityName() {
        return "Watcher";
    }

    @Override
    public double getEntityMaxHealth() {
        return 9500.0;
    }

    @Override
    public double getDamageDealt() {
        return 475.0;
    }
    
    @Override
    public int mobLevel() {
        return 55;
    }

    @Override
    public double getXPDropped() {
        return 40.0;
    }

    @Override
    public List<EntityDrop> drops() {
        return Arrays.asList(new EntityDrop(new ItemStack(Material.ENDER_PEARL, SUtil.random(2, 4)), EntityDropType.GUARANTEED, 1.0), new EntityDrop(new ItemStack(Material.ARROW, SUtil.random(2, 4)), EntityDropType.GUARANTEED, 1.0), new EntityDrop(SMaterial.ENCHANTED_BONE, EntityDropType.RARE, 0.05), new EntityDrop(SMaterial.END_STONE_BOW, EntityDropType.EXTRAORDINARILY_RARE, 0.01));
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(SItem.of(SMaterial.END_STONE_BOW).getStack(), SItem.of(SMaterial.SUMMONING_EYE).getStack(), SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromRGB(0)), SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromRGB(0)), SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_BOOTS), Color.fromRGB(0)));
    }

}
