package in.godspunky.skyblock.item.farming;

import in.godspunky.skyblock.features.skill.FarmingSkill;
import in.godspunky.skyblock.features.skill.Skill;
import in.godspunky.skyblock.item.ExperienceRewardStatistics;
import in.godspunky.skyblock.item.GenericItemType;
import in.godspunky.skyblock.item.MaterialFunction;
import in.godspunky.skyblock.item.Rarity;

public class SugarCane implements ExperienceRewardStatistics, MaterialFunction {
    @Override
    public double getRewardXP() {
        return 2.0;
    }

    @Override
    public Skill getRewardedSkill() {
        return FarmingSkill.INSTANCE;
    }

    @Override
    public String getDisplayName() {
        return "Sugar Cane";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }
}
