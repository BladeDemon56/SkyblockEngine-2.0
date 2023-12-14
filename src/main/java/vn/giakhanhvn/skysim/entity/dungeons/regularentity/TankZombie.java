package vn.giakhanhvn.skysim.entity.dungeons.regularentity;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.Entity;
import vn.giakhanhvn.skysim.util.EntityManager;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import org.bukkit.entity.LivingEntity;
import vn.giakhanhvn.skysim.util.SUtil;
import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import vn.giakhanhvn.skysim.entity.SEntityEquipment;
import vn.giakhanhvn.skysim.entity.EntityStatistics;
import vn.giakhanhvn.skysim.entity.EntityFunction;

public class TankZombie implements EntityFunction, EntityStatistics
{
    @Override
    public String getEntityName() {
        return "Tank Zombie";
    }
    
    @Override
    public double getEntityMaxHealth() {
        return 5.0E7;
    }
    
    @Override
    public double getDamageDealt() {
        return 1300500.0;
    }
    
    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(new ItemStack(Material.AIR), SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_HELMET), Color.fromRGB(15132390)), SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromRGB(5923940)), SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromRGB(5923940)), SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_BOOTS), Color.fromRGB(15132390)));
    }
    
    @Override
    public void onSpawn(final LivingEntity entity, final SEntity sEntity) {
        entity.setMetadata("DungeonMobs", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("SlayerBoss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        EntityManager.DEFENSE_PERCENTAGE.put((Entity)entity, 95);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 1));
    }
    
    @Override
    public double getXPDropped() {
        return 30.0;
    }
}
