package in.godspunky.skyblock.entity.dungeons.minibosses;

import com.google.common.util.concurrent.AtomicDouble;
import de.slikey.effectlib.effect.ConeEffect;
import de.slikey.effectlib.util.ParticleEffect;
import in.godspunky.skyblock.Skyblock;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Material;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.Entity;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import in.godspunky.skyblock.entity.SEntity;
import in.godspunky.skyblock.entity.SEntityEquipment;
import in.godspunky.skyblock.entity.zombie.BaseZombie;
import in.godspunky.skyblock.item.SItem;
import in.godspunky.skyblock.item.SMaterial;
import in.godspunky.skyblock.user.PlayerStatistics;
import in.godspunky.skyblock.user.PlayerUtils;
import in.godspunky.skyblock.user.User;
import in.godspunky.skyblock.util.EntityManager;
import in.godspunky.skyblock.util.SUtil;
import in.godspunky.skyblock.util.Sputnik;

import java.util.List;

public class FrozenAdv extends BaseZombie {
    private boolean isEating;
    private boolean isBowing;
    private boolean EatingCooldown;
    private boolean CDDR;
    private boolean CDLA;

    public FrozenAdv() {
        this.isEating = false;
        this.isBowing = false;
        this.EatingCooldown = false;
        this.CDDR = false;
        this.CDLA = false;
    }

    @Override
    public String getEntityName() {
        return Sputnik.trans("&d&lFrozen Adventurer");
    }

    @Override
    public double getEntityMaxHealth() {
        return 7.0E8;
    }

    @Override
    public double getDamageDealt() {
        return 6000000.0;
    }

    @Override
    public void onSpawn(final LivingEntity entity, final SEntity sEntity) {
        ((CraftZombie) entity).setBaby(false);
        final AttributeInstance followRange = ((CraftLivingEntity) entity).getHandle().getAttributeInstance(GenericAttributes.FOLLOW_RANGE);
        followRange.setValue(40.0);
        final PlayerDisguise pl = Sputnik.applyPacketNPC(entity, "ewogICJ0aW1lc3RhbXAiIDogMTYwMTUyOTc4ODY1OSwKICAicHJvZmlsZUlkIiA6ICIyMWUzNjdkNzI1Y2Y0ZTNiYjI2OTJjNGEzMDBhNGRlYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJHZXlzZXJNQyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zZGVlNTAxODFkYmMzMzZkMGQ5MDM1ZjYyMjQ0OGM0M2RhYTdmZGI1ZDdjYWFhOTFmNjdiM2JjNjQ4NmMzMjEwIgogICAgfQogIH0KfQ==", "i0cF74RyF/YAl8m2VathjBpRKlY93rrqnBx/fZPwzaXhL+KLGGhGEJc0SPSzDqpQDXXQKeMO2qKQwDsIbXrNQT0TUMzjFObzPznx5LVNrjZIs9xYpOyh6olmPOxKb8S/5DKKIbtm1ZfejK4KFLuz1OP4idcgPf+xzhoXsPfX8KdbWXoTu192zQ/L6lo0N2dAMzjz6ymELXkpl05gruONtSF01OjcyvVL80lWR5YyecoycxqFPpVXOhxAYYa2PoircLwMg2Vtmkck0/u0gniDt3EEkZkQ44CjT/9bxjf4LEkeHMdnXkt/KYaTk934/eSgr8dL6zlU7v/IyX6Jn3vceQQz9XF04Q+COBsxfjvExc7/Awti+8OJASCvlWoBHL2jqQbXDKXk/OJgjh6F8rPECljiqrdfmEC+W3lM/mc8WBX1KheHtiZiMlyYPOZQ4hCdCJoiHi+jxIhV56UVvu911lhsyRB4ovyb6JWqty/9ztN8spEA4Mxk0xIcK7aVJ3nb8XrfCsMRC17oAwd6W79qSGKxmLJxTg25w+HJ1Sj4JRrLcD4Ix505ptLAdyGdd17xr5oXZ7G4cT3vm19sR1SqPYuyjHV9S1eJBtJAo7kFhFcoAKGBp8MdHXZ4MTZPZZSXdOwPGcYavANN7NA3EPecvfqBUCh9e3IhXJOP70Huv5A=", true);
        final PlayerWatcher skywatch = pl.getWatcher();
        final LivingEntity target = ((CraftZombie) entity).getTarget();
        EntityManager.DEFENSE_PERCENTAGE.put(entity, 87);
        entity.setMetadata("SlayerBoss", new FixedMetadataValue(Skyblock.getPlugin(), true));
        entity.setMetadata("LD", new FixedMetadataValue(Skyblock.getPlugin(), true));
        new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                if (FrozenAdv.this.isEating) {
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 65, 4));
                    entity.getEquipment().setItemInHand(new ItemStack(Material.GOLDEN_APPLE));
                    Sputnik.sendEatingAnimation(entity);
                }
            }
        }.runTaskTimer(Skyblock.getPlugin(), 0L, 4L);
        new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                for (int i = 0; i < 20; ++i) {
                    entity.getWorld().spigot().playEffect(entity.getLocation().clone().add(0.0, 0.25, 0.0), Effect.FLAME, 0, 1, (float) SUtil.random(-0.5, 0.5), (float) SUtil.random(0.0, 1.5), (float) SUtil.random(-0.5, 0.5), 0.0f, 1, 20);
                }
            }
        }.runTaskTimer(Skyblock.getPlugin(), 0L, 20L);
        new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                if (entity.getHealth() < entity.getMaxHealth() / 2.0 && !FrozenAdv.this.EatingCooldown && !FrozenAdv.this.isEating) {
                    FrozenAdv.this.EatingCooldown = true;
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 65, 4));
                    FrozenAdv.this.isBowing = false;
                    SUtil.delay(() -> FrozenAdv.this.isEating = true, 5L);
                    entity.getEquipment().setItemInHand(new ItemStack(Material.GOLDEN_APPLE));
                    new BukkitRunnable() {
                        public void run() {
                            if (entity.isDead()) {
                                return;
                            }
                            entity.getEquipment().setItemInHand(new ItemStack(Material.AIR));
                            entity.getWorld().playSound(entity.getLocation(), Sound.BURP, 1.0f, 1.0f);
                            final double healamount = FrozenAdv.this.getEntityMaxHealth() * SUtil.random(40, 60) / 100.0;
                            if (!entity.isDead()) {
                                entity.setHealth(Math.min(entity.getMaxHealth(), entity.getHealth() + healamount));
                            }
                            FrozenAdv.this.isEating = false;
                            SUtil.delay(() -> {
                                final Object val$entity = entity;
                                if (!FrozenAdv.this.isBowing) {
                                    entity.getEquipment().setItemInHand(SUtil.enchant(SItem.of(SMaterial.ICE_WAND).getStack()));
                                } else {
                                    entity.getEquipment().setItemInHand(SItem.of(SMaterial.BOW).getStack());
                                }
                            }, 5L);
                            SUtil.delay(() -> FrozenAdv.this.EatingCooldown = false, SUtil.random(600, 800));
                        }
                    }.runTaskLater(Skyblock.getPlugin(), 60L);
                }
            }
        }.runTaskTimer(Skyblock.getPlugin(), 0L, 10L);
        new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    Sputnik.zero(entity);
                    this.cancel();
                    return;
                }
                final LivingEntity target1 = ((CraftZombie) entity).getTarget();
                if (target1 != null) {
                    if (target1.getLocation().distance(entity.getLocation()) >= 6.0 && target1.getLocation().distance(entity.getLocation()) < 16.0) {
                        entity.teleport(entity.getLocation().setDirection(target1.getLocation().toVector().subtract(entity.getLocation().toVector())));
                        FrozenAdv.sendHeadRotation(entity, entity.getLocation().getYaw(), entity.getLocation().getPitch());
                    }
                    if ((target1.getLocation().distance(entity.getLocation()) < 6.0 || target1.getLocation().distance(entity.getLocation()) > 16.0) && !FrozenAdv.this.isEating) {
                        SUtil.delay(() -> {
                            final Object val$entity = entity;
                            entity.getEquipment().setItemInHand(SUtil.enchant(SItem.of(SMaterial.ICE_WAND).getStack()));
                        }, 0L);
                        FrozenAdv.this.isBowing = false;
                    }
                    if (target1.getLocation().distance(entity.getLocation()) >= 6.0 && target1.getLocation().distance(entity.getLocation()) < 16.0 && !FrozenAdv.this.isBowing && !FrozenAdv.this.isEating) {
                        FrozenAdv.this.isBowing = true;
                        skywatch.setRightClicking(false);
                        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000, 4));
                        entity.getEquipment().setItemInHand(SItem.of(SMaterial.BOW).getStack());
                        new BukkitRunnable() {
                            int t = 0;
                            int atkCharge = 20;
                            double bowPower = 2.2;
                            boolean crit = true;

                            public void run() {
                                if (target1.getLocation().distance(entity.getLocation()) <= 10.0) {
                                    this.atkCharge = 10;
                                    this.bowPower = 1.1;
                                    this.crit = false;
                                }
                                ++this.t;
                                if (!FrozenAdv.this.isBowing) {
                                    skywatch.setRightClicking(false);
                                    entity.removePotionEffect(PotionEffectType.SLOW);
                                    this.cancel();
                                    return;
                                }
                                if (this.t == 5) {
                                    if (!FrozenAdv.this.isBowing) {
                                        return;
                                    }
                                    skywatch.setRightClicking(true);
                                }
                                if (this.t == this.atkCharge) {
                                    if (!FrozenAdv.this.isBowing) {
                                        return;
                                    }
                                    skywatch.setRightClicking(false);
                                }
                                if (this.t >= this.atkCharge + 1) {
                                    if (!FrozenAdv.this.isBowing) {
                                        return;
                                    }
                                    final Location location = entity.getEyeLocation().add(entity.getEyeLocation().getDirection().toLocation(entity.getWorld()));
                                    final Location l = location.clone();
                                    l.setYaw(location.getYaw());
                                    final Arrow arr = entity.getWorld().spawnArrow(l, l.getDirection(), (float) this.bowPower, 1.6f);
                                    arr.setShooter(entity);
                                    if (!this.crit) {
                                        arr.setCritical(SUtil.random(0, 1) == 1);
                                    } else {
                                        arr.setCritical(true);
                                    }
                                    skywatch.setRightClicking(false);
                                    entity.removePotionEffect(PotionEffectType.SLOW);
                                    FrozenAdv.this.isBowing = false;
                                }
                            }
                        }.runTaskTimer(Skyblock.getPlugin(), 0L, 1L);
                    }
                    if (target1.getLocation().distance(entity.getLocation()) <= 10.0 && !FrozenAdv.this.isBowing && !FrozenAdv.this.isEating && SUtil.random(0, 100) < 10 && !FrozenAdv.this.CDLA) {
                        FrozenAdv.this.CDLA = true;
                        FrozenAdv.this.lightningPlayer(entity);
                        SUtil.delay(() -> FrozenAdv.this.CDLA = false, 300L);
                    }
                    if (target1.getLocation().distance(entity.getLocation()) <= 5.0 && !FrozenAdv.this.isBowing && !FrozenAdv.this.isEating) {
                        if (SUtil.random(0, 100) > 30) {
                            return;
                        }
                        if (FrozenAdv.this.CDDR) {
                            return;
                        }
                        FrozenAdv.this.CDDR = true;
                        skywatch.setRightClicking(true);
                        FrozenAdv.this.playPar(entity.getEyeLocation().setDirection(target1.getLocation().toVector().subtract(entity.getLocation().toVector())));
                        entity.getLocation().getWorld().playSound(entity.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
                        for (final Entity e : target1.getWorld().getNearbyEntities(entity.getLocation().add(entity.getLocation().getDirection().multiply(1)), 3.0, 3.0, 3.0)) {
                            if (e instanceof Player) {
                                final Player player = (Player) e;
                                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 20));
                                double b = 0.0;
                                for (int i = 0; i < 2; ++i) {
                                    final int d;
                                    if ((d = i) == 0) {
                                        b = 0.2;
                                    } else if (i == 1) {
                                        b = 0.4;
                                    } else if (i == 2) {
                                        b = 0.6;
                                    }
                                    final ArmorStand stands = (ArmorStand) player.getWorld().spawn(player.getLocation().add(0.0, b + 1.0, 0.0), (Class) ArmorStand.class);
                                    stands.setCustomNameVisible(false);
                                    stands.setVisible(false);
                                    stands.setArms(true);
                                    stands.setMarker(true);
                                    stands.setGravity(false);
                                    stands.setRightArmPose(new EulerAngle(0.0, 0.0, 12.0));
                                    stands.getEquipment().setItemInHand(new ItemStack(Material.PACKED_ICE));
                                    SUtil.delay(() -> {
                                        stands.remove();
                                        player.removeMetadata("frozen", Skyblock.getPlugin());
                                    }, 100L);
                                    new BukkitRunnable() {
                                        public void run() {
                                            double c = 0.0;
                                            if (d == 0) {
                                                c = 0.2;
                                            } else if (d == 1) {
                                                c = 0.4;
                                            } else if (d == 2) {
                                                c = 0.6;
                                            }
                                            if (stands.isDead()) {
                                                player.removePotionEffect(PotionEffectType.SLOW);
                                                player.removeMetadata("frozen", Skyblock.getPlugin());
                                                this.cancel();
                                                return;
                                            }
                                            if (player.isDead() || entity.isDead()) {
                                                stands.remove();
                                                player.removeMetadata("frozen", Skyblock.getPlugin());
                                            }
                                            stands.teleport(player.getLocation().add(0.0, c + 1.0, 0.0));
                                        }
                                    }.runTaskTimer(Skyblock.getPlugin(), 0L, 1L);
                                }
                                final PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(player.getUniqueId());
                                if (statistics == null) {
                                    return;
                                }
                                final double defense = statistics.getDefense().addAll();
                                final int dmglater = (int) Math.round(FrozenAdv.this.getDamageDealt() * 3.0 - FrozenAdv.this.getDamageDealt() * 3.0 * (defense / (defense + 100.0)));
                                User.getUser(player.getUniqueId()).damage(dmglater, EntityDamageEvent.DamageCause.ENTITY_ATTACK, entity);
                                player.setMetadata("frozen", new FixedMetadataValue(Skyblock.getPlugin(), true));
                                ((LivingEntity) e).damage(1.0E-6, null);
                            }
                        }
                        SUtil.delay(() -> {
                            final Object val$skywatch = skywatch;
                            if (!FrozenAdv.this.isBowing) {
                                skywatch.setRightClicking(false);
                            }
                        }, 20L);
                        SUtil.delay(() -> FrozenAdv.this.CDDR = false, 200L);
                    }
                } else if (!FrozenAdv.this.isEating) {
                    FrozenAdv.this.isBowing = false;
                    entity.getEquipment().setItemInHand(SUtil.enchant(SItem.of(SMaterial.ICE_WAND).getStack()));
                }
            }
        }.runTaskTimer(Skyblock.getPlugin(), 0L, 2L);
        new BukkitRunnable() {
            Location loc = entity.getLocation();
            final EntityLiving nms = ((CraftLivingEntity) entity).getHandle();

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                this.loc.setY(0.0);
                this.nms.setSprinting(false);
                final Location loc2 = entity.getLocation();
                loc2.setY(0.0);
                if (entity.hasMetadata("frozen")) {
                    return;
                }
                if (((CraftZombie) entity).getTarget() == null) {
                    return;
                }
                if (((CraftZombie) entity).getTarget().getWorld() != entity.getWorld()) {
                    return;
                }
                if (((CraftZombie) entity).getTarget().getLocation().distance(entity.getLocation()) <= 4.0 || FrozenAdv.this.isEating || FrozenAdv.this.isBowing) {
                    return;
                }
                if (this.loc.distance(loc2) >= 0.2) {
                    this.nms.setSprinting(true);
                    if (entity.isOnGround() && this.loc.distance(loc2) >= 0.5) {
                        double motY = 0.4199999868869782;
                        double motX = 0.0;
                        double motZ = 0.0;
                        if (this.nms.hasEffect(MobEffectList.JUMP)) {
                            motY += (this.nms.getEffect(MobEffectList.JUMP).getAmplifier() + 1) * 0.2f;
                        }
                        if (this.nms.isSprinting()) {
                            final float f = this.nms.yaw * 0.01745329f;
                            motX -= MathHelper.sin(f) * 0.9f;
                            motZ += MathHelper.cos(f) * 0.9f;
                        }
                        entity.setVelocity(new Vector(motX, motY, motZ));
                    }
                    this.loc = entity.getLocation().clone();
                    return;
                }
                this.nms.setSprinting(false);
            }
        }.runTaskTimer(Skyblock.getPlugin(), 0L, 7L);
        new BukkitRunnable() {
            public void run() {
                final EntityLiving nms = ((CraftLivingEntity) entity).getHandle();
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                for (final Entity entities : entity.getWorld().getNearbyEntities(entity.getLocation().add(entity.getLocation().getDirection().multiply(1.0)), 1.5, 1.5, 1.5)) {
                    if (FrozenAdv.this.isEating) {
                        continue;
                    }
                    if (FrozenAdv.this.isBowing) {
                        continue;
                    }
                    if (!(entities instanceof Player)) {
                        continue;
                    }
                    final Player target = (Player) entities;
                    if (target.getGameMode() == GameMode.CREATIVE) {
                        continue;
                    }
                    if (target.getGameMode() == GameMode.SPECTATOR) {
                        continue;
                    }
                    if (target.hasMetadata("NPC")) {
                        continue;
                    }
                    if (target.getNoDamageTicks() == 7) {
                        continue;
                    }
                    if (SUtil.random(0, 10) > 8) {
                        continue;
                    }
                    entity.teleport(entity.getLocation().setDirection(target.getLocation().subtract(entities.getLocation()).toVector()));
                    for (final Player players : Bukkit.getOnlinePlayers()) {
                        ((CraftPlayer) players).getHandle().playerConnection.sendPacket(new PacketPlayOutAnimation(((CraftLivingEntity) entity).getHandle(), 0));
                    }
                    nms.r(((CraftPlayer) target).getHandle());
                    break;
                }
            }
        }.runTaskTimer(Skyblock.getPlugin(), 0L, 2L);
    }

    @Override
    public void onDamage(final SEntity sEntity, final Entity damager, final EntityDamageByEntityEvent e, final AtomicDouble damage) {
        final Entity en = sEntity.getEntity();
        final Vector v = new Vector(0, 0, 0);
        SUtil.delay(() -> en.setVelocity(v), 1L);
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(SUtil.enchant(SItem.of(SMaterial.ICE_WAND).getStack()), SUtil.enchant(SUtil.getSkullURLStack("Frozen Blaze Helmet", "55a13bb48e3595b55de8dd6943fc38db5235371278c695bd453e49a0999", 1, "")), SUtil.enchant(st(10541807, Material.LEATHER_CHESTPLATE, "Frozen Blaze Chestplate")), SUtil.enchant(st(10541807, Material.LEATHER_LEGGINGS, "Frozen Blaze Leggings")), SUtil.enchant(st(10541807, Material.LEATHER_BOOTS, "Frozen Blaze Boots")));
    }

    public static ItemStack st(final int hexcolor, final Material m, final String name) {
        final ItemStack stack = SUtil.applyColorToLeatherArmor(new ItemStack(m), Color.fromRGB(hexcolor));
        final ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.spigot().setUnbreakable(true);
        stack.setItemMeta(itemMeta);
        return stack;
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
    public double getXPDropped() {
        return 0.0;
    }

    @Override
    public double getMovementSpeed() {
        return 0.25;
    }

    public void playPar(final Location l) {
        final ConeEffect Effect = new ConeEffect(Skyblock.effectManager);
        Effect.setLocation(l.clone().add(l.getDirection().normalize().multiply(-0.25)).add(0.0, -0.1, 0.0));
        Effect.particle = ParticleEffect.SNOW_SHOVEL;
        Effect.color = Color.WHITE;
        Effect.angularVelocity = 0.39269908169872414;
        Effect.lengthGrow = 0.025f;
        Effect.particles = 30;
        Effect.period = 3;
        Effect.iterations = 5;
        Effect.start();
    }

    public void lightningPlayer(final Entity en) {
        final List<Entity> a = en.getNearbyEntities(10.0, 10.0, 10.0);
        a.removeIf(entity -> entity.getType() != EntityType.PLAYER);
        if (a.size() < 3) {
            for (int i = 0; i < SUtil.random(1, 3); ++i) {
                en.getWorld().strikeLightningEffect(new Location(en.getWorld(), en.getLocation().getX() + SUtil.random(-2, 2), en.getLocation().getY(), en.getLocation().getZ() + SUtil.random(-2, 2), en.getLocation().getYaw(), en.getLocation().getPitch()));
            }
        }
        for (final Entity e : en.getNearbyEntities(10.0, 10.0, 10.0)) {
            if (e instanceof Player) {
                final Player p = (Player) e;
                p.getWorld().strikeLightningEffect(p.getLocation());
                User.getUser(p.getUniqueId()).damage(p.getMaxHealth() * 10.0 / 100.0, EntityDamageEvent.DamageCause.ENTITY_ATTACK, e);
            }
        }
    }

    public static void sendHeadRotation(final Entity e, final float yaw, final float pitch) {
        final net.minecraft.server.v1_8_R3.Entity pl = ((CraftZombie) e).getHandle();
        pl.setLocation(e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), yaw, pitch);
        final PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport(pl);
        Sputnik.sendPacket(e.getWorld(), packet);
    }
}
