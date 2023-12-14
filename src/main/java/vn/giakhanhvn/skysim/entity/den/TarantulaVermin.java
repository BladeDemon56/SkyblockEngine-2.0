package vn.giakhanhvn.skysim.entity.den;

import java.util.Collections;
import vn.giakhanhvn.skysim.entity.EntityDropType;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.entity.EntityDrop;
import java.util.List;

public class TarantulaVermin extends BaseSpider
{
    @Override
    public String getEntityName() {
        return "Tarantula Vermin";
    }
    
    @Override
    public double getEntityMaxHealth() {
        return 54000.0;
    }
    
    @Override
    public double getDamageDealt() {
        return 900.0;
    }
    
    @Override
    public double getXPDropped() {
        return 150.0;
    }
    
    @Override
    public List<EntityDrop> drops() {
        return Collections.<EntityDrop>singletonList(new EntityDrop(SMaterial.TARANTULA_WEB, EntityDropType.GUARANTEED, 1.0));
    }
}
