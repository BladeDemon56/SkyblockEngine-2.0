package in.godspunky.skyblock.entity.dragon.type;

import in.godspunky.skyblock.entity.dragon.Dragon;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

public class YoungDragon extends Dragon {
    public YoungDragon(World world) {
        super(world, 1.8, DEFAULT_DAMAGE_DEGREE_RANGE, 300L);
    }

    public YoungDragon() {
        this(((CraftWorld) Bukkit.getWorlds().get(0)).getHandle());
    }

    public String getEntityName() {
        return "Young Dragon";
    }

    public double getEntityMaxHealth() {
        return 7500000.0;
    }

    public double getDamageDealt() {
        return 1600.0;
    }
}