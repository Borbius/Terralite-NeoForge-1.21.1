package net.borbius.terralitemod.effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class RandomTeleportEffect extends MobEffect {
    public RandomTeleportEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    public float oldHealth;

    @Override
    public void onEffectAdded(LivingEntity pLivingEntity, int pAmplifier) {
        oldHealth = pLivingEntity.getHealth();
        super.onEffectAdded(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        //Teleport Sickness check
        if (pLivingEntity.hasEffect(ModEffects.TELEPORT_SICKNESS)) {
            return false;
        }
        //Damaged check
        if (pLivingEntity.getHealth() < oldHealth) {
            return false;
        }
        if (!(pLivingEntity instanceof ServerPlayer)) {//maybe add a black list for mobs
            return false;
        }
        //Retreating check
        if (pLivingEntity instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.level().dimension() == ServerLevel.END) {
                boolean isEnderDragonAlive = serverPlayer.level().getEntitiesOfClass(EnderDragon.class, serverPlayer.getBoundingBox().inflate(1000)).size() > 0;
                boolean isWitherAlive = serverPlayer.level().getEntitiesOfClass(WitherBoss.class, serverPlayer.getBoundingBox().inflate(500)).size() > 0;
                if (isEnderDragonAlive) {//Add End Retreating config check
                    serverPlayer.displayClientMessage(Component.translatable("A powerful entity is preventing teleportation"),true);
                    return false;
                }
                if (isWitherAlive) {//Add Wither Retreating config check
                    serverPlayer.displayClientMessage(Component.translatable("A powerful entity is preventing teleportation"),true);
                    return false;
                }
            }
        }
        // Calculate random teleport location within the 10k, 100, 10k radius
        Random random = new Random();
        double randomX = random.nextInt(20000) - 10000;
        double randomY = random.nextInt(20) - 10;
        double randomZ = random.nextInt(20000) - 10000;
        Vec3 randomPos = new Vec3(pLivingEntity.getX() + randomX, pLivingEntity.getY() + randomY, pLivingEntity.getZ() + randomZ);

        //Teleport distance to duration for teleport sickness duration
        int sicknessDuration = 6000;
        if (pLivingEntity instanceof ServerPlayer serverPlayer) {
            // Check if the player's current dimension is different from the respawn dimension
            ResourceKey<Level> currentDimension = serverPlayer.level().dimension();
            ResourceKey<Level> respawnDimensionKey = serverPlayer.getRespawnDimension();

            if (!currentDimension.equals(respawnDimensionKey)) {
                sicknessDuration = 36000;
            } else {
                // Teleport distance to duration for sickness (if dimensions are the same)
                double distance = pLivingEntity.position().distanceTo(randomPos);
                sicknessDuration = Math.max(6000, (int) (distance * 4));
                sicknessDuration = Math.min(sicknessDuration, 12000); // Caps the duration to 30 minutes
            }
        }

        var duration = pLivingEntity.getEffect(ModEffects.RANDOM_TELEPORT_EFFECT).getDuration();
        if (duration == 1) {
            if (pLivingEntity instanceof ServerPlayer serverPlayer) {
                ResourceKey<Level> respawnDimensionKey = serverPlayer.getRespawnDimension();
                ServerLevel respawnLevel = serverPlayer.server.getLevel(respawnDimensionKey);
                //serverPlayer.changeDimension(new DimensionTransition(respawnLevel, pLivingEntity,DimensionTransition.PLAY_PORTAL_SOUND));
                serverPlayer.teleportTo(randomPos.x, randomPos.y, randomPos.z);
            }
            //pLivingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION,100,1));
            pLivingEntity.addEffect(new MobEffectInstance(ModEffects.TELEPORT_SICKNESS, sicknessDuration,0,false,false));
            pLivingEntity.level().addParticle(ParticleTypes.PORTAL,pLivingEntity.getX(),pLivingEntity.getY(),pLivingEntity.getZ(),0,0,0);
            pLivingEntity.playSound(SoundEvents.ENDERMAN_TELEPORT,3f,1f);

        }
        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}

//Config
//Random Teleport Radius
//Boss Retreating
//Random Dimension
//Apply Teleport Sickness
//Cancel if hurt
//Apply Nausea/Confusion