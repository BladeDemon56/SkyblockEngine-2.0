package vn.giakhanhvn.skysim.skill;

import java.util.ArrayList;
import vn.giakhanhvn.skysim.user.User;
import java.util.Arrays;
import java.util.List;

public class MageSkill extends Skill implements DungeonsSkill
{
    public static final MageSkill INSTANCE;
    
    @Override
    public String getName() {
        return "Mage";
    }
    
    @Override
    public String getAlternativeName() {
        return "{skip}";
    }
    
    @Override
    public List<String> getDescription() {
        return Arrays.<String>asList("");
    }
    
    @Override
    public List<String> getLevelUpInformation(final int level, final int lastLevel, final boolean showOld) {
        return Arrays.<String>asList("");
    }
    
    @Override
    public boolean hasSixtyLevels() {
        return false;
    }
    
    @Override
    public void onSkillUpdate(final User user, final double previousXP) {
        super.onSkillUpdate(user, previousXP);
    }
    
    @Override
    public List<String> getPassive() {
        final List<String> t = new ArrayList<String>();
        t.add("Mage Staff");
        t.add("Efficent Spells");
        return t;
    }
    
    @Override
    public List<String> getOrb() {
        final List<String> t = new ArrayList<String>();
        t.add("Guided Sheep");
        t.add("Thunderstorm");
        return t;
    }
    
    @Override
    public List<String> getGhost() {
        final List<String> t = new ArrayList<String>();
        t.add("Pop-up Wall");
        t.add("Fireball");
        return t;
    }
    
    static {
        INSTANCE = new MageSkill();
    }
}
