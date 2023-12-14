package vn.giakhanhvn.skysim.entity.caverns;

import java.util.Arrays;
import vn.giakhanhvn.skysim.enchantment.EnchantmentType;
import vn.giakhanhvn.skysim.enchantment.Enchantment;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.entity.EntityDropType;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.entity.EntityDrop;
import java.util.List;
import vn.giakhanhvn.skysim.util.SUtil;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import vn.giakhanhvn.skysim.entity.SEntityEquipment;
import vn.giakhanhvn.skysim.entity.zombie.BaseZombie;

public class EnchantedDiamondZombie extends BaseZombie
{
    @Override
    public String getEntityName() {
        return "Zombie";
    }
    
    @Override
    public double getEntityMaxHealth() {
        return 300.0;
    }
    
    @Override
    public double getDamageDealt() {
        return 275.0;
    }
    
    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD)), SUtil.enchant(new ItemStack(Material.DIAMOND_BLOCK)), SUtil.enchant(new ItemStack(Material.DIAMOND_CHESTPLATE)), SUtil.enchant(new ItemStack(Material.DIAMOND_LEGGINGS)), SUtil.enchant(new ItemStack(Material.DIAMOND_BOOTS)));
    }
    
    @Override
    public List<EntityDrop> drops() {
        return Arrays.<EntityDrop>asList(new EntityDrop(SMaterial.ROTTEN_FLESH, EntityDropType.GUARANTEED, 1.0), new EntityDrop(SUtil.enchant(SItem.of(SMaterial.MINER_HELMET), new Enchantment(EnchantmentType.PROTECTION, 5)).getStack(), EntityDropType.RARE, 0.05), new EntityDrop(SUtil.enchant(SItem.of(SMaterial.MINER_CHESTPLATE), new Enchantment(EnchantmentType.PROTECTION, 5)).getStack(), EntityDropType.RARE, 0.05), new EntityDrop(SUtil.enchant(SItem.of(SMaterial.MINER_LEGGINGS), new Enchantment(EnchantmentType.PROTECTION, 5)).getStack(), EntityDropType.RARE, 0.05), new EntityDrop(SUtil.enchant(SItem.of(SMaterial.MINER_BOOTS), new Enchantment(EnchantmentType.PROTECTION, 5)).getStack(), EntityDropType.RARE, 0.05));
    }
    
    @Override
    public boolean isBaby() {
        return false;
    }
    
    @Override
    public boolean isVillager() {
        return false;
    }
    
    @Override
    public double getXPDropped() {
        return 24.0;
    }
}
