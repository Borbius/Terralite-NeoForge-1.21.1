package net.borbius.terralitemod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

//things to do. create a bounding box around the player. iterate through all blocks in that area & see if they have the valuable box tag. implement block glow

public class SpelunkerEffect extends MobEffect {
    public SpelunkerEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return super.shouldApplyEffectTickThisTick(pDuration, pAmplifier);
    }
}