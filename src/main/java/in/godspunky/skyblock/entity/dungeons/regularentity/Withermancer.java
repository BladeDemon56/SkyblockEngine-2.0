package in.godspunky.skyblock.entity.dungeons.regularentity;

import com.google.common.util.concurrent.AtomicDouble;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import net.minecraft.server.v1_8_R3.AttributeInstance;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSkeleton;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import in.godspunky.skyblock.SkySimEngine;
import in.godspunky.skyblock.entity.EntityFunction;
import in.godspunky.skyblock.entity.SEntity;
import in.godspunky.skyblock.entity.SEntityEquipment;
import in.godspunky.skyblock.entity.SkeletonStatistics;
import in.godspunky.skyblock.item.SItem;
import in.godspunky.skyblock.item.SMaterial;
import in.godspunky.skyblock.user.User;
import in.godspunky.skyblock.util.EntityManager;
import in.godspunky.skyblock.util.SUtil;
import in.godspunky.skyblock.util.Sputnik;

import java.util.Iterator;

public class Withermancer implements SkeletonStatistics, EntityFunction {
    @Override
    public String getEntityName() {
        return "Withermancer";
    }

    @Override
    public double getEntityMaxHealth() {
        return 1.0E8;
    }

    @Override
    public double getDamageDealt() {
        return 1500000.0;
    }

    public static ItemStack b(final int hexcolor, final Material m) {
        final ItemStack stack = SUtil.applyColorToLeatherArmor(new ItemStack(m), Color.fromRGB(hexcolor));
        final ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.spigot().setUnbreakable(true);
        stack.setItemMeta(itemMeta);
        return stack;
    }

    @Override
    public boolean isWither() {
        return true;
    }

    @Override
    public void onSpawn(final LivingEntity entity, final SEntity sEntity) {
        final AttributeInstance followRange = ((CraftLivingEntity) entity).getHandle().getAttributeInstance(GenericAttributes.FOLLOW_RANGE);
        followRange.setValue(40.0);
        final MobDisguise pl = Sputnik.applyPacketSkeleton(entity);
        EntityManager.DEFENSE_PERCENTAGE.put(entity, 40);
        entity.setMetadata("DungeonMobs", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
        entity.setMetadata("SlayerBoss", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
        new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                for (int i = 0; i < 20; ++i) {
                    entity.getWorld().spigot().playEffect(entity.getLocation().clone().add(0.0, 0.25, 0.0), Effect.MAGIC_CRIT, 0, 1, (float) SUtil.random(-0.5, 0.5), (float) SUtil.random(0.0, 0.6), (float) SUtil.random(-0.5, 0.5), 0.0f, 1, 20);
                }
            }
        }.runTaskTimer(SkySimEngine.getPlugin(), 0L, 15L);
        new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                entity.getEquipment().setItemInHand(new ItemStack(Material.STONE_SWORD));
                if (((CraftSkeleton) entity).getTarget() != null && ((CraftSkeleton) entity).getTarget().getLocation().distance(entity.getLocation()) > 2.0) {
                    SUtil.delay(() -> {
                        final Object val$entity = entity;
                        Withermancer.this.throwThickAssBone(entity);
                        final Iterator<Player> iterator = (Iterator<Player>) Bukkit.getOnlinePlayers().iterator();
                        while (iterator.hasNext()) {
                            final Player players = iterator.next();
                            ((CraftPlayer) players).getHandle().playerConnection.sendPacket(new PacketPlayOutAnimation(((CraftLivingEntity) entity).getHandle(), 0));
                        }
                        entity.getEquipment().setItemInHand(null);
                        SUtil.delay(() -> entity.getEquipment().setItemInHand(null), 30L);
                    }, 20L);
                }
            }
        }.runTaskTimer(SkySimEngine.getPlugin(), 2L, 100L);
        new BukkitRunnable() {
            public void run() {
                final EntityLiving nms = ((CraftLivingEntity) entity).getHandle();
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                final LivingEntity target1 = ((CraftSkeleton) entity).getTarget();
                for (final Entity entities : entity.getWorld().getNearbyEntities(entity.getLocation().add(entity.getLocation().getDirection().multiply(1.0)), 1.5, 1.5, 1.5)) {
                    if (!(entities instanceof Player)) {
                        continue;
                    }
                    final Player target2 = (Player) entities;
                    if (target2.getGameMode() == GameMode.CREATIVE) {
                        continue;
                    }
                    if (target2.getGameMode() == GameMode.SPECTATOR) {
                        continue;
                    }
                    if (target2.hasMetadata("NPC")) {
                        continue;
                    }
                    if (target2.getNoDamageTicks() == 7) {
                        continue;
                    }
                    if (SUtil.random(0, 10) > 8) {
                        continue;
                    }
                    entity.teleport(entity.getLocation().setDirection(target2.getLocation().subtract(entities.getLocation()).toVector()));
                    for (final Player players : Bukkit.getOnlinePlayers()) {
                        ((CraftPlayer) players).getHandle().playerConnection.sendPacket(new PacketPlayOutAnimation(((CraftLivingEntity) entity).getHandle(), 0));
                    }
                    nms.r(((CraftPlayer) target2).getHandle());
                    break;
                }
            }
        }.runTaskTimer(SkySimEngine.getPlugin(), 0L, 2L);
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(new ItemStack(Material.STONE_SWORD), null, null, null, null);
    }

    @Override
    public void onDeath(final SEntity sEntity, final Entity killed, final Entity damager) {
    }

    @Override
    public boolean hasNameTag() {
        return false;
    }

    @Override
    public void onDamage(final SEntity sEntity, final Entity damager, final EntityDamageByEntityEvent e, final AtomicDouble damage) {
    }

    @Override
    public double getXPDropped() {
        return 56.0;
    }

    @Override
    public double getMovementSpeed() {
        return 0.2;
    }

    public void throwThickAssBone(final Entity e) {
        final Vector throwVec = e.getLocation().add(e.getLocation().getDirection().multiply(10)).toVector().subtract(e.getLocation().toVector()).normalize().multiply(1.2);
        final Location throwLoc = e.getLocation().add(0.0, 0.5, 0.0);
        final ArmorStand armorStand1 = (ArmorStand) e.getWorld().spawnEntity(throwLoc, EntityType.ARMOR_STAND);
        armorStand1.getEquipment().setItemInHand(SItem.of(SMaterial.STONE_SWORD).getStack());
        armorStand1.setGravity(false);
        armorStand1.setVisible(false);
        armorStand1.setMarker(true);
        final Vector teleportTo = e.getLocation().getDirection().normalize().multiply(1);
        final Vector[] previousVector = {throwVec};
        new BukkitRunnable() {
            private int run = -1;

            public void run() {
                final int i;
                final int ran = i = 0;
                final int num = 90;
                final Location loc = null;
                ++this.run;
                if (this.run > 100) {
                    this.cancel();
                    return;
                }
                final Location locof = armorStand1.getLocation();
                locof.setY(locof.getY() + 1.0);
                if (locof.getBlock().getType() != Material.AIR) {
                    armorStand1.remove();
                    this.cancel();
                    return;
                }
                final double xPos = armorStand1.getRightArmPose().getX();
                armorStand1.setRightArmPose(new EulerAngle(xPos + 0.7, 0.0, 0.0));
                final Vector newVector = new Vector(throwVec.getX(), previousVector[0].getY() - 0.03, throwVec.getZ());
                previousVector[0] = newVector;
                armorStand1.setVelocity(newVector);
                if (i < 13) {
                    final int angle = i * 20 + num;
                    final boolean back = false;
                } else {
                    final int angle = i * 20 - num;
                    final boolean back = true;
                }
                if (locof.getBlock().getType() != Material.AIR && locof.getBlock().getType() != Material.WATER) {
                    armorStand1.remove();
                    this.cancel();
                    return;
                }
                if (i % 2 == 0 && i < 13) {
                    armorStand1.teleport(armorStand1.getLocation().add(teleportTo).multiply(1.0));
                    armorStand1.teleport(armorStand1.getLocation().add(teleportTo).multiply(1.0));
                } else if (i % 2 == 0) {
                    armorStand1.teleport(armorStand1.getLocation().subtract(loc.getDirection().normalize().multiply(1)));
                    armorStand1.teleport(armorStand1.getLocation().subtract(loc.getDirection().normalize().multiply(1)));
                }
                for (int j = 0; j < 20; ++j) {
                    armorStand1.getWorld().spigot().playEffect(armorStand1.getLocation().clone().add(0.0, 1.75, 0.0), Effect.CRIT, 0, 1, (float) SUtil.random(-0.5, 0.5), (float) SUtil.random(0.0, 0.5), (float) SUtil.random(-0.5, 0.5), 0.0f, 1, 20);
                }
                for (final Entity en : armorStand1.getNearbyEntities(1.0, 1.0, 1.0)) {
                    if (en instanceof Player) {
                        final Player p = (Player) en;
                        p.getWorld().playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
                        User.getUser(p.getUniqueId()).damage(p.getMaxHealth() * 50.0 / 100.0, EntityDamageEvent.DamageCause.ENTITY_ATTACK, e);
                        p.damage(1.0E-5);
                        armorStand1.remove();
                        this.cancel();
                        break;
                    }
                }
            }

            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
            }
        }.runTaskTimer(SkySimEngine.getPlugin(), 0L, 2L);
    }

    @Override
    public int mobLevel() {
        return 0;
    }
}