package in.godspunky.skyblock.reforge;

import in.godspunky.skyblock.item.GenericItemType;
import in.godspunky.skyblock.item.RarityValue;

import java.util.Collections;
import java.util.List;

public class Spiritual implements Reforge {
    @Override
    public String getName() {
        return "Spiritual";
    }

    @Override
    public RarityValue<Double> getStrength() {
        return new RarityValue<Double>(4.0, 8.0, 14.0, 20.0, 28.0, 38.0);
    }

    @Override
    public RarityValue<Double> getCritChance() {
        return new RarityValue<Double>(0.07, 0.08, 0.09, 0.1, 0.12, 0.14);
    }

    @Override
    public RarityValue<Double> getCritDamage() {
        return new RarityValue<Double>(0.1, 0.15, 0.23, 0.37, 0.55, 0.75);
    }

    @Override
    public List<GenericItemType> getCompatibleTypes() {
        return Collections.singletonList(GenericItemType.RANGED_WEAPON);
    }
}