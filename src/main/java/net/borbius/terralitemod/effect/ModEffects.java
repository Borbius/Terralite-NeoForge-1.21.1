package net.borbius.terralitemod.effect;

import net.borbius.terralitemod.TerraliteMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;


//Registers the effect and adds attribute modifiers, effect/potion color and effect icon
public class ModEffects {
    public static DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, TerraliteMod.MOD_ID);

    public  static  final Holder<MobEffect> RECALL_EFFECT = MOB_EFFECTS.register("recall",
            () -> new RecallEffect(MobEffectCategory.NEUTRAL, 0x36bab)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(TerraliteMod.MOD_ID, "recall"),
                            -0.50f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public  static  final Holder<MobEffect> TELEPORT_SICKNESS = MOB_EFFECTS.register("teleport_sickness",
            () -> new TeleportSickness(MobEffectCategory.HARMFUL, 0x36bab));

    public  static  final Holder<MobEffect> RANDOM_TELEPORT_EFFECT = MOB_EFFECTS.register("random_teleport",
            () -> new RandomTeleportEffect(MobEffectCategory.NEUTRAL, 0x791d6e));

    /*public  static  final Holder<MobEffect> RECALL_TO_LODESTONE_EFFECT = MOB_EFFECTS.register("recall_to_ls",
            () -> new RecallToLodeStoneEffect(MobEffectCategory.NEUTRAL, 0x36bab));*/

    public  static  final Holder<MobEffect> SPELUNKER_EFFECT = MOB_EFFECTS.register("spelunker_effect",
            () -> new SpelunkerEffect(MobEffectCategory.BENEFICIAL, 0xFFFF00));

    public  static  final Holder<MobEffect> INVERT_CONTROLS_EFFECT = MOB_EFFECTS.register("invert_controls_effect",
            () -> new InvertControlsEffect(MobEffectCategory.HARMFUL, 0xFFFF00));//change to grey color


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}