package in.godspunky.skyblock.entity.dungeons.regularentity;

import in.godspunky.skyblock.entity.*;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import in.godspunky.skyblock.SkySimEngine;
import in.godspunky.skyblock.entity.*;
import in.godspunky.skyblock.item.SMaterial;
import in.godspunky.skyblock.util.EntityManager;
import in.godspunky.skyblock.util.SUtil;

import java.util.Arrays;
import java.util.List;

public class SkeletonSoldier implements EntityFunction, EntityStatistics {
    @Override
    public String getEntityName() {
        return "Skeleton Master";
    }

    @Override
    public double getEntityMaxHealth() {
        return 1.0E8;
    }

    @Override
    public double getDamageDealt() {
        return 1500000.0;
    }

    @Override
    public double getXPDropped() {
        return 40.0;
    }

    @Override
    public void onSpawn(final LivingEntity entity, final SEntity sEntity) {
        entity.setMetadata("DungeonMobs", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
        entity.setMetadata("SlayerBoss", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
        EntityManager.DEFENSE_PERCENTAGE.put(entity, 65);
    }

    @Override
    public List<EntityDrop> drops() {
        return Arrays.asList(new EntityDrop(new ItemStack(Material.ARROW, SUtil.random(2, 4)), EntityDropType.GUARANTEED, 1.0), new EntityDrop(SMaterial.ENCHANTED_BONE, EntityDropType.RARE, 0.05));
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(new ItemStack(Material.BOW), SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_HELMET), Color.fromRGB(16739083)), SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromRGB(16759819)), SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromRGB(16759819)), SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_BOOTS), Color.fromRGB(16759819)));
    }
}