package io.github.seggan.slimefunwarfare.items.powersuits;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Heads;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import net.guizhanss.guizhanlib.minecraft.helper.potion.PotionEffectTypeHelper;
import net.guizhanss.minecraft.slimefunwarfare.ArmorPieceUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum Module {
    NANOFIBER_CUSHION(0, "纳米纤维垫", ArmorPiece.FEET, new ItemStack[]{
        new ItemStack(Material.FEATHER), Items.REINFORCED_SLIMESTEEL, new ItemStack(Material.FEATHER),
        Items.REINFORCED_SLIMESTEEL, Items.MODULE_CASE, Items.REINFORCED_SLIMESTEEL,
        new ItemStack(Material.FEATHER), Items.REINFORCED_SLIMESTEEL, new ItemStack(Material.FEATHER),
    }, 5, "&7该模组可以让你", "&7免疫摔落伤害"),
    MINI_JETS(1, "迷你喷气靴", ArmorPiece.FEET, new ItemStack[]{
        Items.OSMIUM_SUPERALLOY, Items.REINFORCED_SLIMESTEEL, Items.OSMIUM_SUPERALLOY,
        SlimefunItems.STEEL_THRUSTER, Items.MODULE_CASE, SlimefunItems.STEEL_THRUSTER,
        Items.OSMIUM_SUPERALLOY, Items.REINFORCED_SLIMESTEEL, Items.OSMIUM_SUPERALLOY
    }, 8, "&7获得创造模式的飞行能力"),
    LIFE_SUPPORT(2, "生命维持系统", PotionEffectType.REGENERATION, 2, ArmorPiece.CHEST, new ItemStack[]{
        SlimefunItems.ESSENCE_OF_AFTERLIFE, Items.SLIMESTEEL, SlimefunItems.ESSENCE_OF_AFTERLIFE,
        Items.SLIMESTEEL, Items.MODULE_CASE, Items.SLIMESTEEL,
        SlimefunItems.ESSENCE_OF_AFTERLIFE, Items.SLIMESTEEL, SlimefunItems.ESSENCE_OF_AFTERLIFE
    }, 5),
    HEAT_SINKS(3, "散热片", PotionEffectType.FIRE_RESISTANCE, 0, ArmorPiece.CHEST, new ItemStack[]{
        new ItemStack(Material.MAGMA_CREAM), Items.OSMIUM_INGOT, new ItemStack(Material.MAGMA_CREAM),
        Items.OSMIUM_INGOT, Items.MODULE_CASE, Items.OSMIUM_INGOT,
        new ItemStack(Material.MAGMA_CREAM), Items.OSMIUM_INGOT, new ItemStack(Material.MAGMA_CREAM)
    }, 1),
    HYDRAULICS(4, "集成液压系统", PotionEffectType.INCREASE_DAMAGE, 1, ArmorPiece.CHEST, new ItemStack[]{
        SlimefunItems.REINFORCED_PLATE, Items.OSMIUM_SUPERALLOY, SlimefunItems.REINFORCED_PLATE,
        SlimefunItems.FUEL_BUCKET, Items.MODULE_CASE, SlimefunItems.FUEL_BUCKET,
        SlimefunItems.REINFORCED_PLATE, Items.OSMIUM_SUPERALLOY, SlimefunItems.REINFORCED_PLATE
    }, 3),
    REACTION_WHEELS(5, "反应轮", ArmorPiece.LEGS, new ItemStack[]{
        SlimefunItems.STEEL_PLATE, Items.OSMIUM_SUPERALLOY, SlimefunItems.STEEL_PLATE,
        SlimefunItems.STEEL_INGOT, Items.MODULE_CASE, SlimefunItems.STEEL_INGOT,
        SlimefunItems.STEEL_PLATE, Items.OSMIUM_SUPERALLOY, SlimefunItems.STEEL_PLATE
    }, 5, "&7该模组可以让你", "&7跑得更快"),
    ENERGY_SHIELD(6, "个人能量罩", PotionEffectType.DAMAGE_RESISTANCE, 2, ArmorPiece.CHEST, new ItemStack[]{
        Items.UNPATENTABLIUM, Items.OSMIUM_SUPERALLOY, Items.UNPATENTABLIUM,
        Items.POWER_SUIT_GENERATOR, Items.MODULE_CASE, Items.POWER_SUIT_GENERATOR,
        Items.ENERGY_RECTIFIER, Items.SEGGANESSON, Items.ENERGY_RECTIFIER
    }, 3),
    AQUAMASK(7, "水下面具™", PotionEffectType.CONDUIT_POWER, 0, ArmorPiece.HEAD, new ItemStack[]{
        new ItemStack(Material.PUFFERFISH), SlimefunItems.CLOTH, new ItemStack(Material.PUFFERFISH),
        Items.SLIMESTEEL, Items.MODULE_CASE, Items.SLIMESTEEL,
        new ItemStack(Material.PUFFERFISH), SlimefunItems.CLOTH, new ItemStack(Material.PUFFERFISH)
    }, 2),
    AUXILIARY_GENERATOR(8, "备用发电机", null, new ItemStack[]{
        Items.OSMIUM_SUPERALLOY, Items.POWER_SUIT_GENERATOR, Items.OSMIUM_SUPERALLOY,
        Items.POWER_SUIT_GENERATOR, Items.MODULE_CASE, Items.POWER_SUIT_GENERATOR,
        Items.OSMIUM_SUPERALLOY, Items.POWER_SUIT_GENERATOR, Items.OSMIUM_SUPERALLOY
    }, 5, "&7提高动力装甲的能源生产速率"),
    ELECTRONIC_SPRINGS(9, "电力弹簧", PotionEffectType.JUMP, 1, ArmorPiece.LEGS, new ItemStack[]{
        Items.REINFORCED_SLIMESTEEL, SlimefunItems.ADVANCED_CIRCUIT_BOARD, Items.REINFORCED_SLIMESTEEL,
        Items.REINFORCED_SLIMESTEEL, Items.MODULE_CASE, Items.REINFORCED_SLIMESTEEL,
        Items.REINFORCED_SLIMESTEEL, SlimefunItems.BASIC_CIRCUIT_BOARD, Items.REINFORCED_SLIMESTEEL
    }, 3),
    MINI_PISTONS(10, "小型活塞", PotionEffectType.FAST_DIGGING, 3, ArmorPiece.CHEST, new ItemStack[]{
        Items.REINFORCED_SLIMESTEEL, new ItemStack(Material.PISTON), Items.REINFORCED_SLIMESTEEL,
        Items.REINFORCED_SLIMESTEEL, Items.MODULE_CASE, Items.REINFORCED_SLIMESTEEL,
        Items.REINFORCED_SLIMESTEEL, new ItemStack(Material.PISTON), Items.REINFORCED_SLIMESTEEL
    }, 3),
    ;
    @Getter
    @Nullable
    private final PotionEffect effect;
    @Getter
    @Nullable
    private final ArmorPiece allowed;
    @Getter
    @Nonnull
    private final SlimefunItemStack item;
    @Nonnull
    private final ItemStack[] recipe;
    @Getter
    private final int power;

    @Getter
    private final int id;

    private static final Map<Integer, Module> cache = new HashMap<>();

    static {
        for (Module module : Module.values()) {
            cache.put(module.id, module);
        }
    }

    Module(int id, @Nonnull String name, @Nullable PotionEffectType effect, int level, @Nullable ArmorPiece allowed, @Nonnull ItemStack[] recipe, int power, @Nonnull String... lore) {
        this.id = id;
        this.allowed = allowed;
        this.power = power;
        if (effect != null) {
            this.effect = new PotionEffect(effect, 21, level, false, false, false);
        } else {
            this.effect = null;
        }
        this.recipe = recipe;

        List<String> loreList = new ArrayList<>(Arrays.asList(lore));
        if (lore.length > 0) {
            loreList.add(0, "");
        }
        loreList.add("");
        if (effect != null) {
            loreList.add(String.format("&7效果: &a%s %d", PotionEffectTypeHelper.getName(effect), level + 1));
        }
        loreList.add(allowed == null ? "&7可安装在任何部位" : "&7可安装在 " + ArmorPieceUtil.getName(allowed));
        loreList.add(String.format("&e消耗 %dJ", power));

        this.item = new SlimefunItemStack(
            this.name(),
            PlayerHead.getItemStack(Heads.MODULE),
            "&6" + name,
            loreList.toArray(new String[0])
        );
    }

    Module(int id, @Nonnull String name, ArmorPiece allowed, @Nonnull ItemStack[] recipe, int power, @Nonnull String... lore) {
        this(id, name, null, 0, allowed, recipe, power, lore);
    }

    public static void setup(SlimefunWarfare addon) {
        for (Module module : Module.values()) {
            new ModuleItem(Categories.POWER_SUITS, module.item, RecipeType.ENHANCED_CRAFTING_TABLE, module.recipe, module)
                .register(addon);
        }
    }

    @Nullable
    public static Module getById(int id) {
        return cache.get(id);
    }

    public static class ModuleItem extends SlimefunItem implements NotPlaceable {

        @Getter
        private final Module module;

        public ModuleItem(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Module module) {
            super(category, item, recipeType, recipe);

            this.module = module;
        }
    }
}
