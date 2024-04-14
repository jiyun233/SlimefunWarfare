package io.github.seggan.slimefunwarfare.items.rareearths;

import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Material;
import org.bukkit.block.Block;

import javax.annotation.Nonnull;

public class Lanthanum extends RareEarth {

    public Lanthanum() {
        super(Items.LANTHANUM_INGOT);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> e.getClickedBlock().ifPresent(b -> {
            Block next = b.getRelative(e.getClickedFace());
            if (next.getType().isAir() && Slimefun.getProtectionManager().hasPermission(e.getPlayer(), next, Interaction.PLACE_BLOCK)) {
                next.setType(Material.FIRE);
            }
        });
    }
}
