package vn.giakhanhvn.skysim.item.enchanted;

import vn.giakhanhvn.skysim.item.MaterialQuantifiable;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.MaterialFunction;

public class EnchantedDiamondBlock implements EnchantedMaterialStatistics, MaterialFunction
{
    @Override
    public String getDisplayName() {
        return "Enchanted Diamond Block";
    }
    
    @Override
    public Rarity getRarity() {
        return Rarity.RARE;
    }
    
    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }
    
    @Override
    public boolean isEnchanted() {
        return true;
    }
    
    @Override
    public SMaterial getCraftingMaterial() {
        return SMaterial.ENCHANTED_DIAMOND;
    }
    
    @Override
    public MaterialQuantifiable getResult() {
        return new MaterialQuantifiable(SMaterial.ENCHANTED_DIAMOND_BLOCK);
    }
}
