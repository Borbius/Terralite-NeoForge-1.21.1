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
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;

public class RecallEffect extends MobEffect {
    public RecallEffect(MobEffectCategory pCategory, int pColor) {
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
        //Retreating check
        if (pLivingEntity instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.level().dimension() == ServerLevel.END) {
                //Maybe have a list of black listed mobs instead of this
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
        //Teleport distance to duration for teleport sickness duration
        var respawnPos = new Vec3(0,0,0);
        int sicknessDuration = 6000;
        if (pLivingEntity instanceof ServerPlayer serverPlayer) {
            //If player has spawn check
            if (serverPlayer.getRespawnPosition() != null) {
                respawnPos = serverPlayer.getRespawnPosition().getCenter();
            } else {
                respawnPos = pLivingEntity.level().getSharedSpawnPos().getCenter();
            }

            // Check if dimensions differ
            ResourceKey<Level> currentDimension = serverPlayer.level().dimension();
            ResourceKey<Level> respawnDimensionKey = serverPlayer.getRespawnDimension();

            // If dimensions are different set fixed sickness duration
            if (!currentDimension.equals(respawnDimensionKey)) {
                sicknessDuration = 24000; //20 minutes
            } else {
                // Teleport distance to duration for sickness (if dimensions are the same)
                double distance = pLivingEntity.position().distanceTo(respawnPos);
                sicknessDuration = Math.max(6000, (int) (distance * 4)); //Min
                sicknessDuration = Math.min(sicknessDuration, 18000); //Cap
            }
        }

        var duration = pLivingEntity.getEffect(ModEffects.RECALL_EFFECT).getDuration();
        if (duration == 1) {
            if (pLivingEntity instanceof ServerPlayer serverPlayer) {
                ResourceKey<Level> respawnDimensionKey = serverPlayer.getRespawnDimension();
                ServerLevel respawnLevel = serverPlayer.server.getLevel(respawnDimensionKey);
                serverPlayer.changeDimension(new DimensionTransition(respawnLevel, pLivingEntity,DimensionTransition.PLAY_PORTAL_SOUND));
                serverPlayer.teleportTo(respawnPos.x, respawnPos.y, respawnPos.z);
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

//Add config for
//Cancel if hurt,
//prevent tp if teleport sickness/don't give teleport sickness,
//teleport sickness min & max duration,
//teleport sickness duration for dimensional teleport
//give confusion/nausea after tp
//Allow end fight retreating
//Teleport Sickness duration increased for dimension travel