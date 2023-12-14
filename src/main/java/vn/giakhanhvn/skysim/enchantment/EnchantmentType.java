package vn.giakhanhvn.skysim.enchantment;

import vn.giakhanhvn.skysim.util.Sputnik;
import org.bukkit.ChatColor;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import java.util.List;
import org.bukkit.enchantments.Enchantment;
import java.util.Map;

public class EnchantmentType
{
    private static final Map<String, EnchantmentType> ENCHANTMENT_TYPE_CACHE;
    public static final EnchantmentType SHARPNESS;
    public static final EnchantmentType LIFE_STEAL;
    public static final EnchantmentType EXECUTE;
    public static final EnchantmentType FIRE_ASPECT;
    public static final EnchantmentType PROTECTION;
    public static final EnchantmentType GROWTH;
    public static final EnchantmentType AIMING;
    public static final EnchantmentType POWER;
    public static final EnchantmentType FLAME;
    public static final EnchantmentType ENDER_SLAYER;
    public static final EnchantmentType DRAGON_HUNTER;
    public static final EnchantmentType TURBO_GEM;
    public static final EnchantmentType EFFICIENCY;
    public static final EnchantmentType KNOCKBACK;
    public static final EnchantmentType AQUA_INFINITY;
    public static final EnchantmentType VAMPIRISM;
    public static final EnchantmentType FIRST_STRIKE;
    public static final EnchantmentType VICIOUS;
    public static final EnchantmentType SMITE;
    public static final EnchantmentType BANE_OF_ARTHROPODS;
    public static final EnchantmentType CRITICAL;
    public static final EnchantmentType FATAL_TEMPO;
    public static final EnchantmentType HARVESTING;
    public static final EnchantmentType TELEKINESIS;
    public static final EnchantmentType ULTIMATE_WISE;
    public static final EnchantmentType LUCKINESS;
    public static final EnchantmentType SOUL_EATER;
    public static final EnchantmentType CHIMERA;
    public static final EnchantmentType LEGION;
    public static final EnchantmentType ONE_FOR_ALL;
    private final String name;
    private final String namespace;
    private final String description;
    private final boolean ultimate;
    private final Enchantment vanilla;
    private final List<SpecificItemType> compatibleTypes;
    
    public EnchantmentType(final String name, final String namespace, final String description, final boolean ultimate, final Enchantment vanilla, final SpecificItemType... compatibleTypes) {
        this.name = name;
        this.namespace = namespace;
        this.description = description;
        this.ultimate = ultimate;
        this.vanilla = vanilla;
        this.compatibleTypes = new ArrayList<SpecificItemType>(Arrays.<SpecificItemType>asList(compatibleTypes));
        EnchantmentType.ENCHANTMENT_TYPE_CACHE.put(namespace, this);
    }
    
    public EnchantmentType(final String name, final String namespace, final String description, final boolean ultimate, final SpecificItemType... compatibleTypes) {
        this(name, namespace, description, ultimate, (Enchantment)null, compatibleTypes);
    }
    
    public EnchantmentType(final String name, final String namespace, final String description, final Enchantment vanilla, final SpecificItemType... compatibleTypes) {
        this(name, namespace, description, false, vanilla, compatibleTypes);
    }
    
    public EnchantmentType(final String name, final String namespace, final String description, final SpecificItemType... compatibleTypes) {
        this(name, namespace, description, false, compatibleTypes);
    }
    
    public static EnchantmentType getByNamespace(final String namespace) {
        return EnchantmentType.ENCHANTMENT_TYPE_CACHE.get(namespace.toLowerCase());
    }
    
    public String getDescription(final Object... objects) {
        String description = this.description;
        for (final Object object : objects) {
            description = description.replaceFirst("%s", String.valueOf(object));
        }
        return description;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof EnchantmentType && ((EnchantmentType)o).namespace.equals(this.namespace);
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getNamespace() {
        return this.namespace;
    }
    
    public boolean isUltimate() {
        return this.ultimate;
    }
    
    public Enchantment getVanilla() {
        return this.vanilla;
    }
    
    public List<SpecificItemType> getCompatibleTypes() {
        return this.compatibleTypes;
    }
    
    static {
        ENCHANTMENT_TYPE_CACHE = new HashMap<String, EnchantmentType>();
        SHARPNESS = new EnchantmentType("Sharpness", "sharpness", "Increases damage dealt by " + ChatColor.GREEN + "%s%", new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE });
        LIFE_STEAL = new EnchantmentType("Life Steal", "life_steal", "Heals for " + ChatColor.GREEN + "%s%" + ChatColor.GRAY + " of your max health each time you hit a mob.", new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE });
        EXECUTE = new EnchantmentType("Execute", "execute", "Increases damage by " + ChatColor.GREEN + "%s%" + Sputnik.trans(" &7for each percent of Health missing on your target. "), new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE });
        FIRE_ASPECT = new EnchantmentType("Fire Aspect", "fire_aspect", "Gives whoever this weapon hits %s seconds of fire.", new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE });
        PROTECTION = new EnchantmentType("Protection", "protection", "Grants " + ChatColor.GREEN + "+%s ❈ Defense" + ChatColor.GRAY + ".", new SpecificItemType[] { SpecificItemType.HELMET, SpecificItemType.CHESTPLATE, SpecificItemType.LEGGINGS, SpecificItemType.BOOTS });
        GROWTH = new EnchantmentType("Growth", "growth", "Grants " + ChatColor.GREEN + "+%s " + ChatColor.RED + "❤ " + ChatColor.RED + "Health" + ChatColor.GRAY + ".", new SpecificItemType[] { SpecificItemType.HELMET, SpecificItemType.CHESTPLATE, SpecificItemType.LEGGINGS, SpecificItemType.BOOTS });
        AIMING = new EnchantmentType("Aiming", "aiming", "Arrows home towards nearby mobs if they are within %s blocks.", new SpecificItemType[] { SpecificItemType.BOW });
        POWER = new EnchantmentType("Power", "power", "Increases bow damage by " + ChatColor.GREEN + "%s%", new SpecificItemType[] { SpecificItemType.BOW });
        FLAME = new EnchantmentType("Flame", "flame", "Arrow ignites target for 3 seconds, dealing 5 damage every second.", new SpecificItemType[] { SpecificItemType.BOW });
        ENDER_SLAYER = new EnchantmentType("Ender Slayer", "ender_slayer", "Increases damage dealt to Ender Dragons and Endermen by " + ChatColor.GREEN + "%s% " + ChatColor.GRAY + "", new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE });
        DRAGON_HUNTER = new EnchantmentType("Dragon Hunter", "dragon_hunter", "Increases damage dealt to Ender Dragons by " + ChatColor.GREEN + "%s% " + ChatColor.GRAY + "", new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE, SpecificItemType.BOW });
        TURBO_GEM = new EnchantmentType("Turbo-Gem", "turbo_gem", "Grants " + ChatColor.AQUA + "%s" + ChatColor.GRAY + " extra Bits while killing mobs. Doesn't apply for" + ChatColor.YELLOW + " magic abilities", new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE, SpecificItemType.BOW });
        EFFICIENCY = new EnchantmentType("Efficiency", "efficiency", "Reduces the time in takes to mine.", Enchantment.DIG_SPEED, new SpecificItemType[] { SpecificItemType.AXE, SpecificItemType.PICKAXE, SpecificItemType.SHOVEL });
        KNOCKBACK = new EnchantmentType("Knockback", "knockback", Sputnik.trans("Increases knockback by &a%s&7 blocks."), Enchantment.KNOCKBACK, new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE });
        AQUA_INFINITY = new EnchantmentType("Aqua Infinity", "aqua_infinity", Sputnik.trans("Increases underwater mining rate to normal level mining rate."), Enchantment.WATER_WORKER, new SpecificItemType[] { SpecificItemType.HELMET });
        VAMPIRISM = new EnchantmentType("Vampirism", "vampirism", Sputnik.trans("Heals for &a%s% &7of your missing Health per level whenever you kill an enemy."), new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE });
        FIRST_STRIKE = new EnchantmentType("First Strike", "first_strike", Sputnik.trans("Increases the first melee damage dealt to a mob by &a%s%"), new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE });
        VICIOUS = new EnchantmentType("Vicious", "vicious", Sputnik.trans("Grant &c+%s⫽ Ferocity"), new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE, SpecificItemType.BOW });
        SMITE = new EnchantmentType("Smite", "smite", "Increases damage dealt to Zombies, Zombie Pigmen, Withers, Wither Skeletons, and Skeletons by " + ChatColor.GREEN + "%s% " + ChatColor.GRAY + "", new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE });
        BANE_OF_ARTHROPODS = new EnchantmentType("Bane of Arthropods", "bane_of_arthropods", "Increases damage dealt to Cave Spiders, Spiders, and Silverfish by " + ChatColor.GREEN + "%s% " + ChatColor.GRAY + "", new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE });
        CRITICAL = new EnchantmentType("Critical", "critical", "Increases " + ChatColor.BLUE + "☠ Crit Damage " + ChatColor.GRAY + "by " + ChatColor.GREEN + "%s%" + ChatColor.GRAY + ".", new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE });
        FATAL_TEMPO = new EnchantmentType("Fatal Tempo", "fatal_tempo", Sputnik.trans("&7Attack increases your &c⫽ &cFerocity &7by &c%s% &7per hit, capped at &c200% &7for 3 seconds after your &efirst &eattack &7that triggers the enchantment."), true, new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE });
        HARVESTING = new EnchantmentType("Harvesting", "harvesting", "Increases the chance for crops to drop double the amount of items by " + ChatColor.GREEN + "%s%" + ChatColor.GRAY + ".", new SpecificItemType[] { SpecificItemType.HOE });
        TELEKINESIS = new EnchantmentType("Telekinesis", "telekinesis", "Blocks and mob drops go directly into your inventory.", new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.BOW, SpecificItemType.AXE });
        ULTIMATE_WISE = new EnchantmentType("Ultimate Wise", "ultimate_wise", "Reduces the Mana Cost of this item's ability by " + ChatColor.GREEN + "%s%" + ChatColor.GRAY + ".", true, new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.SHOVEL, SpecificItemType.SHEARS, SpecificItemType.PICKAXE, SpecificItemType.BOW, SpecificItemType.AXE, SpecificItemType.ROD, SpecificItemType.HOE, SpecificItemType.WAND });
        LUCKINESS = new EnchantmentType("Luckiness", "luckiness", Sputnik.trans("&7Grant &b+%s ✯ Magic Find"), new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.SHOVEL, SpecificItemType.SHEARS, SpecificItemType.PICKAXE, SpecificItemType.BOW, SpecificItemType.AXE, SpecificItemType.ROD, SpecificItemType.HOE, SpecificItemType.HELMET, SpecificItemType.CHESTPLATE, SpecificItemType.LEGGINGS, SpecificItemType.BOOTS });
        SOUL_EATER = new EnchantmentType("Soul Eater", "soul_eater", Sputnik.trans("Your weapon gains &c%sx&7 damage of the latest monster killed and applies it on your next hit."), true, new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE, SpecificItemType.BOW });
        CHIMERA = new EnchantmentType("Chimera", "chimera", Sputnik.trans("Copies &a%s% &7of your active pet's stats."), true, new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE, SpecificItemType.BOW });
        LEGION = new EnchantmentType("Legion", "legion", Sputnik.trans("Increases most of your player stats by &e+%s% &7per player per level within &b30 &7blocks of you, up to &a20 &7players."), true, new SpecificItemType[] { SpecificItemType.HELMET, SpecificItemType.CHESTPLATE, SpecificItemType.LEGGINGS, SpecificItemType.BOOTS });
        ONE_FOR_ALL = new EnchantmentType("One for All", "one_for_all", Sputnik.trans("Removes all other enchants but increases your weapon damage by &a%s%"), true, new SpecificItemType[] { SpecificItemType.SWORD, SpecificItemType.LONGSWORD, SpecificItemType.AXE });
    }
}
