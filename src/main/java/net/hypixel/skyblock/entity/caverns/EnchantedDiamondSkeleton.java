package net.hypixel.skyblock.entity.caverns;

import net.hypixel.skyblock.features.enchantment.Enchantment;
import net.hypixel.skyblock.features.enchantment.EnchantmentType;
import net.hypixel.skyblock.entity.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import net.hypixel.skyblock.item.SItem;
import net.hypixel.skyblock.item.SMaterial;
import net.hypixel.skyblock.util.SUtil;

import java.util.Arrays;
import java.util.List;

public class EnchantedDiamondSkeleton implements EntityStatistics, EntityFunction {
    @Override
    public String getEntityName() {
        return "Miner Skeleton";
    }

    @Override
    public double getEntityMaxHealth() {
        return 300.0;
    }

    @Override
    public double getDamageDealt() {
        return 190.0;
    }
    
    @Override
    public int mobLevel() {
        return 20;
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(SUtil.enchant(new ItemStack(Material.BOW)), SUtil.enchant(new ItemStack(Material.DIAMOND_BLOCK)), SUtil.enchant(new ItemStack(Material.DIAMOND_CHESTPLATE)), SUtil.enchant(new ItemStack(Material.DIAMOND_LEGGINGS)), SUtil.enchant(new ItemStack(Material.DIAMOND_BOOTS)));
    }

    @Override
    public List<EntityDrop> drops() {
        return Arrays.asList(new EntityDrop(new ItemStack(Material.BONE, 4), EntityDropType.GUARANTEED, 1.0), new EntityDrop(SUtil.enchant(SItem.of(SMaterial.MINER_HELMET), new Enchantment(EnchantmentType.PROTECTION, 5)).getStack(), EntityDropType.RARE, 0.05), new EntityDrop(SUtil.enchant(SItem.of(SMaterial.MINER_CHESTPLATE), new Enchantment(EnchantmentType.PROTECTION, 5)).getStack(), EntityDropType.RARE, 0.05), new EntityDrop(SUtil.enchant(SItem.of(SMaterial.MINER_LEGGINGS), new Enchantment(EnchantmentType.PROTECTION, 5)).getStack(), EntityDropType.RARE, 0.05), new EntityDrop(SUtil.enchant(SItem.of(SMaterial.MINER_BOOTS), new Enchantment(EnchantmentType.PROTECTION, 5)).getStack(), EntityDropType.RARE, 0.05));
    }

    @Override
    public double getXPDropped() {
        return 24.0;
    }
}
