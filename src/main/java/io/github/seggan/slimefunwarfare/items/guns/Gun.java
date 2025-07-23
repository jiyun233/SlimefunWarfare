package io.github.seggan.slimefunwarfare.items.guns;

import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.Util;
import io.github.seggan.slimefunwarfare.items.Bullet;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.backpacks.SlimefunBackpack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Getter
public class Gun extends SlimefunItem implements DamageableItem {

    public static final NamespacedKey LAST_USE = AbstractAddon.createKey("last_use");

    private final int range;
    private final int minRange;
    private final int damageDealt;
    private final int cooldown;

    public Gun(SlimefunItemStack item, ItemStack[] recipe, int range, int damage, double cooldown) {
        super(Categories.GUNS, item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);

        this.range = range;
        minRange = 0;
        damageDealt = damage;
        this.cooldown = (int) (cooldown * 1000);

        addItemHandler(getItemHandler());
    }

    public Gun(SlimefunItemStack item, ItemStack[] recipe, int range, int minRange, int damage, double cooldown) {
        super(Categories.GUNS, item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);

        this.range = range;
        this.minRange = SlimefunWarfare.inst().getConfig().getBoolean("guns.min-range-on", true) ? minRange : 0;
        damageDealt = damage;
        this.cooldown = (int) (cooldown * 1000);

        addItemHandler(getItemHandler());
    }

    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            Player p = e.getPlayer();
            ItemStack gun = p.getInventory().getItemInMainHand();
            if (!(SlimefunItem.getByItem(gun) instanceof Gun)) {
                return;
            }

            ItemMeta meta = gun.getItemMeta();
            PersistentDataContainer container = meta.getPersistentDataContainer();
            long lastUse = container.getOrDefault(Gun.LAST_USE, PersistentDataType.LONG, 0L);
            long currentTime = System.currentTimeMillis();
            if ((currentTime - lastUse) < cooldown) {
                p.sendMessage(ChatColor.RED + "换弹中!");
                return;
            }
            container.set(LAST_USE, PersistentDataType.LONG, currentTime);
            gun.setItemMeta(meta);
            shoot(p, gun);
        };
    }

    public void shoot(@Nonnull Player p, @Nonnull ItemStack gun) {
        PlayerInventory inv = p.getInventory();

        Bullet bullet = checkAndConsume(inv.getItemInOffHand());

        if (bullet == null) {
            if (SlimefunWarfare.inst().getConfig().getBoolean("guns.use-bullets-from-inv", true)) {
                bullet = checkAndConsumeInv(inv);
            }

            if (bullet == null) {
                p.sendMessage(ChatColor.RED + "子弹耗尽!");
                return;
            }
        }

        Vector v = p.getEyeLocation().subtract(0, 1, 0).getDirection().multiply(20);
        LlamaSpit spit = p.launchProjectile(LlamaSpit.class);
        spit.setMetadata("isGunBullet", new FixedMetadataValue(SlimefunWarfare.inst(), true));
        spit.setMetadata("damage",
            new FixedMetadataValue(SlimefunWarfare.inst(), this.damageDealt * bullet.getMultiplier())
        );
        spit.setMetadata("isFire", new FixedMetadataValue(SlimefunWarfare.inst(), bullet.isFire()));
        spit.setMetadata("locInfo", new FixedMetadataValue(
            SlimefunWarfare.inst(),
            Util.serializeLocation(p.getEyeLocation())
        ));
        spit.setMetadata("rangeInfo", new FixedMetadataValue(
            SlimefunWarfare.inst(),
            range + ":" + minRange
        ));
        spit.setVelocity(v);
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Nullable
    protected Bullet checkAndConsumeInv(@Nonnull Inventory inv) {
        Bullet bullet = null;

        for (ItemStack itemStack : inv) {
            bullet = checkAndConsume(itemStack);
            if (bullet != null) {
                break;
            }
        }

        return bullet;
    }

    @Nullable
    protected Bullet checkAndConsume(@Nonnull ItemStack stack) {
        AtomicReference<Bullet> bullet = new AtomicReference<>(null);

        if (this.getId().equals("GUN_HEAVY_MACHINE")) {
            SlimefunItem item = SlimefunItem.getByItem(stack);
            if (item instanceof Bullet result) {
                if (result.getId().equals("MACHINE_BULLET")) {
                    bullet.set(result);
                }
            } else if (item instanceof SlimefunBackpack) {
                PlayerProfile.getBackpack(stack, backpack -> bullet.set(checkAndConsumeInv(backpack.getInventory())));
            }
        }

        SlimefunItem item = SlimefunItem.getByItem(stack);
        if (item instanceof Bullet) {
            bullet.set((Bullet) item);
            ItemUtils.consumeItem(stack, true);
        } else if (item instanceof SlimefunBackpack) {
            PlayerProfile.getBackpack(stack, backpack -> bullet.set(checkAndConsumeInv(backpack.getInventory())));
        }

        return bullet.get();
    }
}
