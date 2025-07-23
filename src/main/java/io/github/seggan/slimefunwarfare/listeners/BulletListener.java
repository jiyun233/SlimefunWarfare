package io.github.seggan.slimefunwarfare.listeners;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.Util;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.CommonPatterns;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BulletListener implements Listener {

    private final Map<Material, Double> armorDamageReduction = new HashMap<>();

    public BulletListener() {
        armorDamageReduction.put(Material.LEATHER_HELMET, 0.05);
        armorDamageReduction.put(Material.LEATHER_CHESTPLATE, 0.10);
        armorDamageReduction.put(Material.LEATHER_LEGGINGS, 0.05);
        armorDamageReduction.put(Material.LEATHER_BOOTS, 0.05);

        armorDamageReduction.put(Material.CHAINMAIL_HELMET, 0.10);
        armorDamageReduction.put(Material.CHAINMAIL_CHESTPLATE, 0.20);
        armorDamageReduction.put(Material.CHAINMAIL_LEGGINGS, 0.10);
        armorDamageReduction.put(Material.CHAINMAIL_BOOTS, 0.10);

        armorDamageReduction.put(Material.IRON_HELMET, 0.15);
        armorDamageReduction.put(Material.IRON_CHESTPLATE, 0.30);
        armorDamageReduction.put(Material.IRON_LEGGINGS, 0.15);
        armorDamageReduction.put(Material.IRON_BOOTS, 0.15);

        armorDamageReduction.put(Material.GOLDEN_HELMET, 0.10);
        armorDamageReduction.put(Material.GOLDEN_CHESTPLATE, 0.20);
        armorDamageReduction.put(Material.GOLDEN_LEGGINGS, 0.10);
        armorDamageReduction.put(Material.GOLDEN_BOOTS, 0.10);

        armorDamageReduction.put(Material.DIAMOND_HELMET, 0.20);
        armorDamageReduction.put(Material.DIAMOND_CHESTPLATE, 0.40);
        armorDamageReduction.put(Material.DIAMOND_LEGGINGS, 0.20);
        armorDamageReduction.put(Material.DIAMOND_BOOTS, 0.20);

        armorDamageReduction.put(Material.NETHERITE_HELMET, 0.25);
        armorDamageReduction.put(Material.NETHERITE_CHESTPLATE, 0.50);
        armorDamageReduction.put(Material.NETHERITE_LEGGINGS, 0.25);
        armorDamageReduction.put(Material.NETHERITE_BOOTS, 0.25);
    }


    @EventHandler
    public void onEntityBulletHit(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Projectile bullet)) return;

        Entity shot = e.getEntity();
        if (shot instanceof Player player) {
            double totalDamageReduction = 0;
            for (ItemStack armor : player.getInventory().getArmorContents()) {
                if (armor != null) {
                    Material armorMaterial = armor.getType();
                    if (armorDamageReduction.containsKey(armorMaterial)) {
                        totalDamageReduction += armorDamageReduction.get(armorMaterial);
                    }
                }
            }

            double newDamage = e.getDamage() * (1 - totalDamageReduction);
            Arrays.stream(player.getInventory().getArmorContents())
                    .filter(Objects::nonNull)
                    .forEach(armor -> {
                        val meta = armor.getItemMeta();
                        if (meta instanceof Damageable damageable) {
                            int currentDamage = damageable.getDamage();
                            int armorDamage = Math.min(currentDamage + (int) newDamage, armor.getType().getMaxDurability());
                            damageable.setDamage(armorDamage);
                            armor.setItemMeta(damageable);
                        }
                    });
            e.setDamage(0.1);
            player.setHealth(Math.max(player.getHealth() - newDamage, 0));
        }

        if (bullet.hasMetadata("isGunBullet")) {
            if (bullet.getShooter() instanceof Player shooter) {
                if (!Slimefun.getProtectionManager().hasPermission(shooter, shot.getLocation(), Interaction.ATTACK_PLAYER)) {
                    return;
                }
            }
            Location shooterLoc = Util.deserializeLocation(bullet.getMetadata("locInfo").get(0).asString());
            String[] split = CommonPatterns.COLON.split(bullet.getMetadata("rangeInfo").get(0).asString());
            double distance = shooterLoc.distance(e.getEntity().getLocation());
            if (distance <= Integer.parseInt(split[0]) && distance >= Integer.parseInt(split[1])) {
                e.setDamage(bullet.getMetadata("damage").get(0).asInt());
                if (bullet.getMetadata("isFire").get(0).asBoolean()) {
                    shot.setFireTicks(e.getEntity().getFireTicks() + 60);
                }

                if (bullet instanceof ShulkerBullet && shot instanceof LivingEntity) {
                    Bukkit.getScheduler().runTaskLater(SlimefunWarfare.inst(), () -> ((LivingEntity) shot).removePotionEffect(PotionEffectType.LEVITATION), 1);
                }
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBulletHitBlock(ProjectileHitEvent e) {
        Block b = e.getHitBlock();
        Entity entity = e.getEntity();

        if (!(entity instanceof ShulkerBullet) || b == null) return;

        if (e.getEntity().hasMetadata("isGunBullet") && SlimefunWarfare.inst().getConfig().getBoolean("guns.energy-rifle-explosions", false)) {
            b.getWorld().createExplosion(b.getLocation(), 1F);
        }
    }
}
