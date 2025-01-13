package net.borbius.terralitemod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

//Maybe give the player nausea randomly
public class TeleportSickness extends MobEffect {
    public TeleportSickness(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }
    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }
}