package io.github.seggan.slimefunwarfare.machines;

import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class BulletPress extends AContainer implements RecipeDisplayItem {

    public BulletPress() {
        super(Categories.MACHINES, Items.BULLET_PRESS, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_ALLOY_INGOT, new ItemStack(Material.PISTON), SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.ELECTRIC_MOTOR, null, SlimefunItems.ELECTRIC_MOTOR,
                SlimefunItems.REINFORCED_ALLOY_INGOT, new ItemStack(Material.PISTON), SlimefunItems.REINFORCED_ALLOY_INGOT
        });
    }

    @Override
    protected void registerDefaultRecipes() {
        ItemStack power = new ItemStack(Material.GUNPOWDER);
        registerRecipe(10, new ItemStack(Material.IRON_INGOT), new SlimefunItemStack(Items.IRON_BULLET, 9));
        registerRecipe(10, SlimefunItems.LEAD_INGOT, new SlimefunItemStack(Items.LEAD_BULLET, 9));
        registerRecipe(10, SlimefunItems.SMALL_URANIUM, new SlimefunItemStack(Items.DU_BULLET, 9));
        registerRecipe(10, SlimefunItems.GOLD_12K, new SlimefunItemStack(Items.GOLD_BULLET, 9));
        registerRecipe(10, Items.PYRO_POWDER, Items.TRINITROBULLETENE);
        registerRecipe(10, new ItemStack[]{Items.REINFORCED_SLIMESTEEL, power}, new ItemStack[]{Items.MACHINE_BULLET});
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.BOW);
    }

    @Override
    public int getEnergyConsumption() {
        return 16;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "BULLET_PRESS";
    }

    @Override
    public int getCapacity() {
        return 32;
    }
}
