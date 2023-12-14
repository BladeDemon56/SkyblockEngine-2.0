package vn.giakhanhvn.skysim.item;

import java.util.Iterator;
import java.util.Arrays;
import org.bukkit.inventory.ItemStack;
import java.util.Collection;
import java.util.ArrayList;
import vn.giakhanhvn.skysim.util.SUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ShapedRecipe extends Recipe<ShapedRecipe>
{
    public static final List<ShapedRecipe> CACHED_RECIPES;
    protected String[] shape;
    private final Map<Character, MaterialQuantifiable> ingredientMap;
    
    public ShapedRecipe(final SItem result, final boolean usesExchangeables) {
        super(result, usesExchangeables);
        this.ingredientMap = new HashMap<Character, MaterialQuantifiable>();
        ShapedRecipe.CACHED_RECIPES.add(this);
    }
    
    public ShapedRecipe(final SItem result) {
        this(result, false);
    }
    
    public ShapedRecipe(final SMaterial material, final int amount) {
        this(SUtil.setSItemAmount(SItem.of(material), amount));
    }
    
    public ShapedRecipe(final SMaterial material) {
        this(SItem.of(material));
    }
    
    public ShapedRecipe shape(final String... lines) {
        this.shape = lines;
        return this;
    }
    
    @Override
    public ShapedRecipe setResult(final SItem result) {
        this.result = result;
        return this;
    }
    
    @Override
    public List<MaterialQuantifiable> getIngredients() {
        return new ArrayList<MaterialQuantifiable>(this.ingredientMap.values());
    }
    
    public ShapedRecipe set(final char k, final MaterialQuantifiable material) {
        this.ingredientMap.put(k, material.clone());
        return this;
    }
    
    public ShapedRecipe set(final char k, final SMaterial material, final int amount) {
        return this.set(k, new MaterialQuantifiable(material, amount));
    }
    
    public ShapedRecipe set(final char k, final SMaterial material) {
        return this.set(k, new MaterialQuantifiable(material));
    }
    
    public MaterialQuantifiable[][] toMQ2DArray() {
        final MaterialQuantifiable[][] materials = new MaterialQuantifiable[3][3];
        final String l1 = SUtil.pad(SUtil.<String>getOrDefault(this.shape, 0, "   "), 3);
        final String l2 = SUtil.pad(SUtil.<String>getOrDefault(this.shape, 1, "   "), 3);
        final String l3 = SUtil.pad(SUtil.<String>getOrDefault(this.shape, 2, "   "), 3);
        final String[] ls = { l1, l2, l3 };
        for (int i = 0; i < ls.length; ++i) {
            final String[] lps = ls[i].split("");
            for (int j = 0; j < lps.length; ++j) {
                materials[i][j] = this.ingredientMap.getOrDefault(lps[j].charAt(0), new MaterialQuantifiable(SMaterial.AIR, 1));
            }
        }
        return materials;
    }
    
    protected static ShapedRecipe parseShapedRecipe(final ItemStack[] stacks) {
        if (stacks.length != 9) {
            throw new UnsupportedOperationException("Recipe parsing requires a 9 element array!");
        }
        final MaterialQuantifiable[] l1 = MaterialQuantifiable.of(Arrays.<ItemStack>copyOfRange(stacks, 0, 3));
        final MaterialQuantifiable[] l2 = MaterialQuantifiable.of(Arrays.<ItemStack>copyOfRange(stacks, 3, 6));
        final MaterialQuantifiable[] l3 = MaterialQuantifiable.of(Arrays.<ItemStack>copyOfRange(stacks, 6, 9));
        final MaterialQuantifiable[][] grid = Recipe.airless(new MaterialQuantifiable[][] { l1, l2, l3 });
        final MaterialQuantifiable[] seg = segment(MaterialQuantifiable.of(stacks));
        for (final ShapedRecipe recipe : ShapedRecipe.CACHED_RECIPES) {
            final MaterialQuantifiable[][] airRecipeGrid = recipe.toMQ2DArray();
            final MaterialQuantifiable[][] recipeGrid = Recipe.airless(airRecipeGrid);
            final MaterialQuantifiable[] recipeSeg = segment(SUtil.<MaterialQuantifiable>unnest(airRecipeGrid, MaterialQuantifiable.class));
            if (recipeAccepted(recipe.useExchangeables, grid, recipeGrid)) {
                if (!recipeAccepted(recipe.useExchangeables, seg, recipeSeg)) {
                    continue;
                }
                return recipe;
            }
        }
        return null;
    }
    
    private static <T> boolean deepSameLength(final T[][] a1, final T[][] a2) {
        int c1 = 0;
        int c2 = 0;
        for (final T[] a3 : a1) {
            c1 += a3.length;
        }
        for (final T[] a3 : a2) {
            c2 += a3.length;
        }
        return c1 == c2;
    }
    
    private static MaterialQuantifiable[] segment(final MaterialQuantifiable[] materials) {
        int firstNonAir = -1;
        int lastNonAir = -1;
        for (int i = 0; i < materials.length; ++i) {
            final MaterialQuantifiable material = materials[i];
            if (firstNonAir == -1 && material.getMaterial() != SMaterial.AIR) {
                firstNonAir = i;
            }
            if (material.getMaterial() != SMaterial.AIR) {
                lastNonAir = i;
            }
        }
        if (firstNonAir == -1 || lastNonAir == -1) {
            return new MaterialQuantifiable[0];
        }
        return Arrays.<MaterialQuantifiable>copyOfRange(materials, firstNonAir, lastNonAir + 1);
    }
    
    private static boolean recipeAccepted(final boolean usesExchangeables, final MaterialQuantifiable[][] grid, final MaterialQuantifiable[][] recipeGrid) {
        if (!ShapedRecipe.<MaterialQuantifiable>deepSameLength(grid, recipeGrid)) {
            return false;
        }
        boolean found = true;
        try {
            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[i].length; ++j) {
                    final MaterialQuantifiable m1 = grid[i][j];
                    final MaterialQuantifiable m2 = recipeGrid[i][j];
                    final List<SMaterial> exchangeables = Recipe.getExchangeablesOf(m2.getMaterial());
                    if (!usesExchangeables || exchangeables == null || !exchangeables.contains(m1.getMaterial()) || m1.getAmount() < m2.getAmount()) {
                        if (m1.getMaterial() != m2.getMaterial() || m1.getAmount() < m2.getAmount()) {
                            found = false;
                            break;
                        }
                    }
                }
                if (!found) {
                    break;
                }
            }
        }
        catch (final IndexOutOfBoundsException ex) {
            return false;
        }
        return found;
    }
    
    private static boolean recipeAccepted(final boolean usesExchangeables, final MaterialQuantifiable[] grid1d, final MaterialQuantifiable[] recipeGrid1d) {
        if (grid1d.length != recipeGrid1d.length) {
            return false;
        }
        boolean found = true;
        for (int i = 0; i < grid1d.length; ++i) {
            final MaterialQuantifiable m1 = grid1d[i];
            final MaterialQuantifiable m2 = recipeGrid1d[i];
            final List<SMaterial> exchangeables = Recipe.getExchangeablesOf(m2.getMaterial());
            if (!usesExchangeables || exchangeables == null || !exchangeables.contains(m1.getMaterial()) || m1.getAmount() < m2.getAmount()) {
                if (m1.getMaterial() != m2.getMaterial() || m1.getAmount() < m2.getAmount()) {
                    found = false;
                    break;
                }
            }
        }
        return found;
    }
    
    public String[] getShape() {
        return this.shape;
    }
    
    public Map<Character, MaterialQuantifiable> getIngredientMap() {
        return this.ingredientMap;
    }
    
    static {
        CACHED_RECIPES = new ArrayList<ShapedRecipe>();
    }
}
