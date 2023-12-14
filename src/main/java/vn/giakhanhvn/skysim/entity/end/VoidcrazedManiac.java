package vn.giakhanhvn.skysim.entity.end;

import java.util.Arrays;
import vn.giakhanhvn.skysim.entity.EntityDropType;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.entity.EntityDrop;
import java.util.List;
import net.md_5.bungee.api.ChatColor;

public class VoidcrazedManiac extends BaseEnderman
{
    @Override
    public String getEntityName() {
        return ChatColor.DARK_RED + "Voidcrazed Maniac";
    }
    
    @Override
    public double getEntityMaxHealth() {
        return 7.5E7;
    }
    
    @Override
    public double getDamageDealt() {
        return 15000.0;
    }
    
    @Override
    public double getXPDropped() {
        return 5000.0;
    }
    
    @Override
    public List<EntityDrop> drops() {
        return Arrays.<EntityDrop>asList(new EntityDrop(SMaterial.NULL_SPHERE, EntityDropType.GUARANTEED, 1.0));
    }
}
