package in.godspunky.skyblock.item.armor.minichad;

import in.godspunky.skyblock.item.*;
import in.godspunky.skyblock.item.*;
import in.godspunky.skyblock.item.armor.LeatherArmorStatistics;

public class MinichadBoots implements MaterialFunction, LeatherArmorStatistics {
    @Override
    public double getBaseStrength() {
        return 60.0;
    }

    @Override
    public double getBaseCritChance() {
        return 0.05;
    }

    @Override
    public double getBaseCritDamage() {
        return 0.25;
    }

    @Override
    public double getBaseIntelligence() {
        return 2.0;
    }

    @Override
    public double getBaseMagicFind() {
        return 0.05;
    }

    @Override
    public double getBaseSpeed() {
        return 0.02;
    }

    @Override
    public double getBaseHealth() {
        return 70.0;
    }

    @Override
    public double getBaseDefense() {
        return 50.0;
    }

    @Override
    public int getColor() {
        return 3289650;
    }

    @Override
    public String getDisplayName() {
        return "Minichad Boots";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ARMOR;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.BOOTS;
    }

    @Override
    public String getLore() {
        return null;
    }

    @Override
    public void load() {
        final ShapedRecipe recipe = new ShapedRecipe(SMaterial.HIDDEN_MINICHAD_BOOTS);
        recipe.shape("123", "456", "789");
        recipe.set('1', SMaterial.HIDDEN_DIMOON_FRAG, 24);
        recipe.set('2', SMaterial.HIDDEN_SHARD_DIAMOND, 1);
        recipe.set('3', SMaterial.HIDDEN_DIMOON_FRAG, 24);
        recipe.set('4', SMaterial.HIDDEN_DIMOON_FRAG, 24);
        recipe.set('6', SMaterial.HIDDEN_DIMOON_FRAG, 24);
    }
}