package vn.giakhanhvn.skysim.item.foraging;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.skill.ForagingSkill;
import vn.giakhanhvn.skysim.skill.Skill;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.ExperienceRewardStatistics;

public class DarkOakWood implements ExperienceRewardStatistics, MaterialFunction
{
    @Override
    public double getRewardXP() {
        return 6.0;
    }
    
    @Override
    public Skill getRewardedSkill() {
        return ForagingSkill.INSTANCE;
    }
    
    @Override
    public String getDisplayName() {
        return "Dark Oak Wood";
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
