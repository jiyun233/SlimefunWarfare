package io.github.seggan.slimefunwarfare.lists;

import io.github.mooy1.infinitylib.groups.MultiGroup;
import io.github.mooy1.infinitylib.groups.SubGroup;
import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import org.bukkit.Material;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Categories {

    public static final ItemGroup GENERAL = new SubGroup(
        "slimefunwarfare_general",
        new CustomItemStack(Material.DIAMOND_SWORD, "战争工艺 - 通用")
    );

    public static final ItemGroup MACHINES = new SubGroup(
        "slimefunwarfare_machines",
        new CustomItemStack(Material.STONECUTTER, "战争工艺 - 机器")
    );

    public static final ItemGroup GUNS = new SubGroup(
        "slimefunwarfare_guns",
        new CustomItemStack(Material.CROSSBOW, "战争工艺 - 枪械")
    );

    public static final ItemGroup MELEE = new SubGroup(
        "slimefunwarfare_melee",
        new CustomItemStack(Material.IRON_AXE, "战争工艺 - 近战武器")
    );

    public static final ItemGroup EXPLOSIVES = new SubGroup(
        "slimefunwarfare_explosives",
        new CustomItemStack(Material.TNT, "战争工艺 - 爆炸物")
    );

    public static final ItemGroup RESOURCES = new SubGroup(
        "slimefunwarfare_resources",
        new CustomItemStack(Material.IRON_ORE, "战争工艺 - 资源")
    );

    public static final ItemGroup POWER_SUITS = new SubGroup(
        "slimefunwarfare_power_suits",
        new CustomItemStack(PlayerHead.getItemStack(Heads.SUIT_HELMET), "战争工艺 - 动力装甲")
    );

    private static final ItemGroup MAIN = new MultiGroup(
        "slimefunwarfare",
        new CustomItemStack(Material.DIAMOND_SWORD, "战争工艺"),
        GENERAL, MACHINES, GUNS, MELEE, EXPLOSIVES, RESOURCES, POWER_SUITS
    );

    public static void setup(SlimefunWarfare addon) {
        MAIN.register(addon);
    }
}
