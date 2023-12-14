package vn.giakhanhvn.skysim.entity.dungeons;

import com.google.common.util.concurrent.AtomicDouble;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import vn.giakhanhvn.skysim.extra.protocol.PacketInvoker;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.entity.SEntityEquipment;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.minecraft.server.v1_8_R3.AttributeInstance;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.util.EntityManager;
import org.bukkit.entity.Entity;
import vn.giakhanhvn.skysim.util.Sputnik;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import vn.giakhanhvn.skysim.entity.SEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.meta.ItemMeta;
import vn.giakhanhvn.skysim.util.SUtil;
import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import vn.giakhanhvn.skysim.entity.zombie.BaseZombie;

public class TestingMob extends BaseZombie
{
    private boolean isEating;
    private boolean isBowing;
    private boolean EatingCooldown;
    
    public TestingMob() {
        this.isEating = false;
        this.isBowing = false;
        this.EatingCooldown = false;
    }
    
    @Override
    public String getEntityName() {
        return "Human";
    }
    
    @Override
    public double getEntityMaxHealth() {
        return 10.0;
    }
    
    @Override
    public double getDamageDealt() {
        return 4.0E8;
    }
    
    public static ItemStack b(final int hexcolor, final Material m) {
        final ItemStack stack = SUtil.applyColorToLeatherArmor(new ItemStack(m), Color.fromRGB(hexcolor));
        final ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.spigot().setUnbreakable(true);
        stack.setItemMeta(itemMeta);
        return stack;
    }
    
    @Override
    public void onSpawn(final LivingEntity entity, final SEntity sEntity) {
        ((CraftZombie)entity).setBaby(false);
        final AttributeInstance followRange = ((CraftLivingEntity)entity).getHandle().getAttributeInstance(GenericAttributes.FOLLOW_RANGE);
        followRange.setValue(40.0);
        final PlayerDisguise pl = Sputnik.applyPacketNPC((Entity)entity, "ItsChimmyUwU", null, false);
        pl.getWatcher().setRightClicking(false);
        EntityManager.DEFENSE_PERCENTAGE.put((Entity)entity, 99);
        entity.setMetadata("LD", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
    }
    
    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(new ItemStack(Material.AIR), null, null, null, null);
    }
    
    @Override
    public void onDeath(final SEntity sEntity, final Entity killed, final Entity damager) {
        PacketInvoker.dropGoldenTigerPet((Player)damager, killed.getLocation(), SUtil.random(0, 1) == 1);
    }
    
    @Override
    public boolean isBaby() {
        return false;
    }
    
    @Override
    public boolean hasNameTag() {
        return false;
    }
    
    @Override
    public boolean isVillager() {
        return false;
    }
    
    @Override
    public void onDamage(final SEntity sEntity, final Entity damager, final EntityDamageByEntityEvent e, final AtomicDouble damage) {
    }
    
    @Override
    public double getXPDropped() {
        return 1.0;
    }
    
    @Override
    public double getMovementSpeed() {
        return 0.35;
    }
}
