package vn.giakhanhvn.skysim.reforge;

import java.util.Collections;
import vn.giakhanhvn.skysim.item.GenericItemType;
import java.util.List;
import vn.giakhanhvn.skysim.item.RarityValue;

public class Wise implements Reforge
{
    @Override
    public String getName() {
        return "Wise";
    }
    
    @Override
    public RarityValue<Double> getIntelligence() {
        return new RarityValue<Double>(25.0, 50.0, 75.0, 100.0, 125.0, 150.0);
    }
    
    @Override
    public List<GenericItemType> getCompatibleTypes() {
        return Collections.<GenericItemType>singletonList(GenericItemType.ARMOR);
    }
}
