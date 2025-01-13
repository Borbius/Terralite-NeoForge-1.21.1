package net.borbius.terralitemod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class InvertControlsEffect extends MobEffect {
    public InvertControlsEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void onEffectAdded(LivingEntity livingEntity, int amplifier) {
        Vec3 deltaMovement = livingEntity.getDeltaMovement();
        double invertedX = deltaMovement.x - 0.2f;
        livingEntity.setDeltaMovement(invertedX, deltaMovement.y, deltaMovement.z);
        super.onEffectAdded(livingEntity, amplifier);
    }
}
