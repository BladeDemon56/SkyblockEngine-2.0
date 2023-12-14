package vn.giakhanhvn.skysim.skill;

import org.bukkit.ChatColor;
import java.util.ArrayList;
import vn.giakhanhvn.skysim.user.User;
import java.util.Arrays;
import java.util.List;

public class HealerSkill extends Skill implements DungeonsSkill
{
    public static final HealerSkill INSTANCE;
    
    @Override
    public String getName() {
        return "Healer";
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
        t.add("Renew");
        t.add("Healing Aura");
        t.add("Revive");
        t.add("Orbies" + ChatColor.RED + " Soon!");
        t.add("Soul Tether" + ChatColor.RED + " Soon!");
        return t;
    }
    
    @Override
    public List<String> getOrb() {
        final List<String> t = new ArrayList<String>();
        t.add("Healing Circle");
        t.add("Wish");
        return t;
    }
    
    @Override
    public List<String> getGhost() {
        final List<String> t = new ArrayList<String>();
        t.add("Healing Potion");
        t.add("Revive Self");
        return t;
    }
    
    static {
        INSTANCE = new HealerSkill();
    }
}
