package in.godspunky.skyblock.item.mining;

import in.godspunky.skyblock.skill.MiningSkill;
import in.godspunky.skyblock.skill.Skill;
import in.godspunky.skyblock.item.ExperienceRewardStatistics;
import in.godspunky.skyblock.item.GenericItemType;
import in.godspunky.skyblock.item.MaterialFunction;
import in.godspunky.skyblock.item.Rarity;

public class LapisLazuliOre implements ExperienceRewardStatistics, MaterialFunction {
    @Override
    public double getRewardXP() {
        return 7.0;
    }

    @Override
    public Skill getRewardedSkill() {
        return MiningSkill.INSTANCE;
    }

    @Override
    public String getDisplayName() {
        return "Lapis Lazuli Ore";
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
