package net.guizhanss.minecraft.slimefunwarfare;

import io.github.seggan.slimefunwarfare.items.powersuits.ArmorPiece;
import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;

@UtilityClass
public class ArmorPieceUtil {
    public static @Nonnull String getName(@Nonnull ArmorPiece piece){
        switch (piece) {
            case HEAD:
                return "头盔";
            case CHEST:
                return "胸甲";
            case LEGS:
                return "护腿";
            case FEET:
                return "靴子";
            default:
                return "未知";
        }
    }
}
