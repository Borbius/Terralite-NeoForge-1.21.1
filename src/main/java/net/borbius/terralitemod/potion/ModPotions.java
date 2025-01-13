package net.borbius.terralitemod.potion;

import net.borbius.terralitemod.TerraliteMod;
import net.borbius.terralitemod.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;


//Generates potions and arrow
//every 20 is 1 second
public class ModPotions {
    public static DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, TerraliteMod.MOD_ID);

    public static final Holder<Potion> RECALL_POTION = POTIONS.register("recall_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.RECALL_EFFECT, 100, 0, false , true)));

    public static final Holder<Potion> SPELUNKER_POTION = POTIONS.register("spelunker_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.SPELUNKER_EFFECT, 600, 0)));

     public static final Holder<Potion> RANDOM_TELEPORT_POTION = POTIONS.register("random_teleport_potion",
             () -> new Potion(new MobEffectInstance(ModEffects.RANDOM_TELEPORT_EFFECT, 100, 0)));

     public static final Holder<Potion> INVERT_CONTROLS_POTION = POTIONS.register("invert_controls_potion",
             () -> new Potion(new MobEffectInstance(ModEffects.INVERT_CONTROLS_EFFECT, 600, 0)));




    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
