package vn.giakhanhvn.skysim.reforge;

import java.util.Collections;
import vn.giakhanhvn.skysim.item.GenericItemType;
import java.util.List;
import vn.giakhanhvn.skysim.item.RarityValue;

public class FastReforge implements Reforge
{
    @Override
    public String getName() {
        return "Fast";
    }
    
    @Override
    public RarityValue<Double> getAttackSpeed() {
        return new RarityValue<Double>(10.0, 20.0, 30.0, 40.0, 50.0, 60.0);
    }
    
    @Override
    public List<GenericItemType> getCompatibleTypes() {
        return Collections.<GenericItemType>singletonList(GenericItemType.WEAPON);
    }
}
