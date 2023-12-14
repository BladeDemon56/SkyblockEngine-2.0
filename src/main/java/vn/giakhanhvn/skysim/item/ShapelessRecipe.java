package vn.giakhanhvn.skysim.item;

import java.util.Iterator;
import java.util.Arrays;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.util.SUtil;
import java.util.ArrayList;
import java.util.List;

public class ShapelessRecipe extends Recipe<ShapelessRecipe>
{
    private static final List<ShapelessRecipe> CACHED_RECIPES;
    private final List<MaterialQuantifiable> ingredientList;
    
    public ShapelessRecipe(final SItem result, final boolean usesExchangeables) {
        super(result, usesExchangeables);
        this.ingredientList = new ArrayList<MaterialQuantifiable>();
        ShapelessRecipe.CACHED_RECIPES.add(this);
    }
    
    public ShapelessRecipe(final SItem result) {
        this(result, false);
    }
    
    public ShapelessRecipe(final SMaterial material, final int amount) {
        this(SUtil.setSItemAmount(SItem.of(material), amount));
    }
    
    public ShapelessRecipe(final SMaterial material) {
        this(SItem.of(material));
    }
    
    @Override
    public ShapelessRecipe setResult(final SItem result) {
        this.result = result;
        return this;
    }
    
    public ShapelessRecipe add(final MaterialQuantifiable material) {
        this.ingredientList.add(material.clone());
        return this;
    }
    
    public ShapelessRecipe add(final SMaterial material, final int amount) {
        return this.add(new MaterialQuantifiable(material, amount));
    }
    
    @Override
    public List<MaterialQuantifiable> getIngredients() {
        return this.ingredientList;
    }
    
    @Override
    public String toString() {
        return "ShapelessRecipe{" + this.ingredientList.toString() + "}";
    }
    
    protected static ShapelessRecipe parseShapelessRecipe(final ItemStack[] stacks) {
        if (stacks.length != 9) {
            throw new UnsupportedOperationException("Recipe parsing requires a 9 element array!");
        }
        final MaterialQuantifiable[] materials = SUtil.<MaterialQuantifiable>unnest(Recipe.airless(new MaterialQuantifiable[][] { MaterialQuantifiable.of(Arrays.<ItemStack>copyOfRange(stacks, 0, 3)), MaterialQuantifiable.of(Arrays.<ItemStack>copyOfRange(stacks, 3, 6)), MaterialQuantifiable.of(Arrays.<ItemStack>copyOfRange(stacks, 6, 9)) }), MaterialQuantifiable.class);
        for (final ShapelessRecipe recipe : ShapelessRecipe.CACHED_RECIPES) {
            final List<MaterialQuantifiable> ingredients = recipe.getIngredientList();
            if (materials.length != ingredients.size()) {
                continue;
            }
            boolean found = true;
            final MaterialQuantifiable[] copy = Arrays.<MaterialQuantifiable>copyOf(materials, materials.length);
            for (final MaterialQuantifiable ingredient : ingredients) {
                if (!contains(recipe.useExchangeables, copy, ingredient)) {
                    found = false;
                    break;
                }
            }
            if (!found) {
                continue;
            }
            return recipe;
        }
        return null;
    }
    
    private static boolean contains(final boolean usesExchangeables, final MaterialQuantifiable[] grid, final MaterialQuantifiable test) {
        final List<SMaterial> exchangeables = Recipe.getExchangeablesOf(test.getMaterial());
        for (int i = 0; i < grid.length; ++i) {
            final MaterialQuantifiable material = grid[i];
            if (material != null) {
                if (usesExchangeables && exchangeables != null && exchangeables.contains(material.getMaterial()) && material.getAmount() >= test.getAmount()) {
                    grid[i] = null;
                    return true;
                }
                if (material.getMaterial() == test.getMaterial() && material.getAmount() >= test.getAmount()) {
                    grid[i] = null;
                    return true;
                }
            }
        }
        return false;
    }
    
    public List<MaterialQuantifiable> getIngredientList() {
        return this.ingredientList;
    }
    
    static {
        CACHED_RECIPES = new ArrayList<ShapelessRecipe>();
    }
}
