package vn.giakhanhvn.skysim.skill;

import org.bukkit.ChatColor;
import java.util.ArrayList;
import vn.giakhanhvn.skysim.user.User;
import java.util.Arrays;
import java.util.List;

public class TankSkill extends Skill implements DungeonsSkill
{
    public static final TankSkill INSTANCE;
    
    @Override
    public String getName() {
        return "Tank";
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
        t.add("Protective Barrier" + ChatColor.RED + " Soon!");
        t.add("Taunt");
        t.add("Diversion");
        t.add("Defensive Stance");
        return t;
    }
    
    @Override
    public List<String> getOrb() {
        final List<String> t = new ArrayList<String>();
        t.add("Seismic Wave");
        t.add("Castle of Stone");
        return t;
    }
    
    @Override
    public List<String> getGhost() {
        final List<String> t = new ArrayList<String>();
        t.add("Stun Potion");
        t.add("Absorption Potion");
        return t;
    }
    
    static {
        INSTANCE = new TankSkill();
    }
}
