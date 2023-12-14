package vn.giakhanhvn.skysim.entity.end;

import org.bukkit.material.MaterialData;
import java.util.Collections;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.entity.EntityFunction;
import vn.giakhanhvn.skysim.item.pet.PetAbility;
import vn.giakhanhvn.skysim.item.pet.Pet;
import vn.giakhanhvn.skysim.entity.SEntityType;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import com.google.common.util.concurrent.AtomicDouble;
import vn.giakhanhvn.skysim.user.User;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import vn.giakhanhvn.skysim.entity.SEntity;
import java.util.Arrays;
import vn.giakhanhvn.skysim.entity.EntityDropType;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.util.SUtil;
import org.bukkit.Material;
import vn.giakhanhvn.skysim.entity.EntityDrop;
import java.util.List;

public class Zealot extends BaseEnderman
{
    @Override
    public String getEntityName() {
        return "Zealot";
    }
    
    @Override
    public double getEntityMaxHealth() {
        return 13000.0;
    }
    
    @Override
    public double getDamageDealt() {
        return 1250.0;
    }
    
    @Override
    public int mobLevel() {
        return 55;
    }
    
    @Override
    public List<EntityDrop> drops() {
        return Arrays.<EntityDrop>asList(new EntityDrop(new ItemStack(Material.ENDER_PEARL, SUtil.random(3, 5)), EntityDropType.GUARANTEED, 1.0), new EntityDrop(new ItemStack(Material.ENDER_PEARL, SUtil.random(3, 5)), EntityDropType.GUARANTEED, 0.05));
    }
    
    @Override
    public void onDeath(final SEntity sEntity, final Entity killed, final Entity damager) {
        final Player player = (Player)damager;
        final User user = User.getUser(player.getUniqueId());
        final Pet pet = user.getActivePetClass();
        final AtomicDouble chance = new AtomicDouble(420.0);
        if (pet != null) {
            pet.runAbilities(ability -> ability.onZealotAttempt(chance), user.getActivePet());
        }
        if (SUtil.random(1.0, chance.get()) != 1.0) {
            return;
        }
        player.playSound(player.getLocation(), Sound.WITHER_SPAWN, 1.0f, 1.0f);
        SUtil.sendTitle(player, ChatColor.RED + "SPECIAL ZEALOT");
        player.sendMessage(ChatColor.GREEN + "A special " + ChatColor.LIGHT_PURPLE + "Zealot" + ChatColor.GREEN + " has spawned nearby!");
        new SEntity(killed, SEntityType.SPECIAL_ZEALOT, new Object[0]);
    }
    
    @Override
    public double getXPDropped() {
        return 40.0;
    }
    
    public static class SpecialZealot implements EndermanStatistics, EntityFunction
    {
        @Override
        public String getEntityName() {
            return "Zealot";
        }
        
        @Override
        public double getEntityMaxHealth() {
            return 2000.0;
        }
        
        @Override
        public double getDamageDealt() {
            return 1250.0;
        }
        
        @Override
        public int mobLevel() {
            return 55;
        }
        
        @Override
        public List<EntityDrop> drops() {
            return Collections.<EntityDrop>singletonList(new EntityDrop(SItem.of(SMaterial.SUMMONING_EYE).getStack(), EntityDropType.RARE, 1.0));
        }
        
        @Override
        public MaterialData getCarriedMaterial() {
            return new MaterialData(Material.ENDER_PORTAL_FRAME);
        }
        
        @Override
        public double getXPDropped() {
            return 40.0;
        }
    }
    
    public static class EnderChestZealot implements EndermanStatistics, EntityFunction
    {
        @Override
        public String getEntityName() {
            return "Zealot";
        }
        
        @Override
        public double getEntityMaxHealth() {
            return 13000.0;
        }
        
        @Override
        public double getDamageDealt() {
            return 1250.0;
        }
        
        @Override
        public int mobLevel() {
            return 55;
        }
        
        @Override
        public List<EntityDrop> drops() {
            return Arrays.<EntityDrop>asList(new EntityDrop(new ItemStack(Material.ENDER_PEARL, SUtil.random(3, 5)), EntityDropType.GUARANTEED, 1.0), SUtil.<EntityDrop>getRandom((List<EntityDrop>)Arrays.asList(new EntityDrop(SUtil.setStackAmount(SItem.of(SMaterial.ENCHANTED_ENDER_PEARL).getStack(), SUtil.random(1, 2)), EntityDropType.OCCASIONAL, 1.0), new EntityDrop(SMaterial.CRYSTAL_FRAGMENT, EntityDropType.OCCASIONAL, 1.0), new EntityDrop(SMaterial.ENCHANTED_END_STONE, EntityDropType.OCCASIONAL, 1.0), new EntityDrop(SMaterial.ENCHANTED_OBSIDIAN, EntityDropType.OCCASIONAL, 1.0))));
        }
        
        @Override
        public MaterialData getCarriedMaterial() {
            return new MaterialData(Material.ENDER_CHEST);
        }
        
        @Override
        public void onDeath(final SEntity sEntity, final Entity killed, final Entity damager) {
            final Player player = (Player)damager;
            final User user = User.getUser(player.getUniqueId());
            final Pet pet = user.getActivePetClass();
            final AtomicDouble chance = new AtomicDouble(420.0);
            if (pet != null) {
                pet.runAbilities(ability -> ability.onZealotAttempt(chance), user.getActivePet());
            }
            if (SUtil.random(1.0, chance.get()) != 1.0) {
                return;
            }
            player.playSound(player.getLocation(), Sound.WITHER_SPAWN, 1.0f, 1.0f);
            SUtil.sendTitle(player, ChatColor.RED + "SPECIAL ZEALOT");
            player.sendMessage(ChatColor.GREEN + "A special " + ChatColor.LIGHT_PURPLE + "Zealot" + ChatColor.GREEN + " has spawned nearby!");
            new SEntity(killed, SEntityType.SPECIAL_ZEALOT, new Object[0]);
        }
        
        @Override
        public double getXPDropped() {
            return 40.0;
        }
    }
}
