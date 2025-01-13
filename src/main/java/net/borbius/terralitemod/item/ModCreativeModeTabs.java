package net.borbius.terralitemod.item;

import net.borbius.terralitemod.TerraliteMod;
//import net.borbius.terralitemod.block.ModBlocks;
import net.borbius.terralitemod.block.ModBlocks;
import net.borbius.terralitemod.potion.ModPotions;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public  static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TerraliteMod.MOD_ID);

    public static final Supplier<CreativeModeTab> TERRALITE_TAB = CREATIVE_MODE_TABS.register("terralite_tab",
            () -> CreativeModeTab.builder()
                    //This line of code creates the tab after the specified tab is created
                    //.withTabsBefore(ResourceLocation.fromNamespaceAndPath(TerraliteMod.MOD_ID,"previous creative tab name")
                    //Tab icon (MAKE A CUSTOM ICON LATER)
                    .icon(() -> new ItemStack(Items.GLOW_ITEM_FRAME))
                    //internal key used for references & translation
                    .title(Component.translatable("creativetab.terralitemod.terralitemod_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //Add items to the custom tab here
                        //output.accept(ModItems.RECALL_POTION.get());
                        //output.accept(ModItems.SPELUNKER_POTION.get());
                        //Items
                        /*output.accept(ModItems.RAW_TEST.get());
                        output.accept(ModItems.TEST_INGOT.get());*/
                        output.accept(ModItems.RUBY_ORE.get());
                        /*output.accept(ModItems.POLISHED_RUBY.get());
                        output.accept(ModItems.FALLEN_STAR.get());
                        output.accept(ModItems.FLOUR.get());
                        output.accept(ModItems.OPAL.get());

                        //Advanced Items
                        output.accept(ModItems.MAGIC_MIRROR.get());
                        output.accept(ModItems.ROPE_BUNDLE.get());
                        //output.accept(ModItems.RECALL_POTION.get());

                        //Food
                        output.accept(ModItems.CARBONARA.get());
                        output.accept(ModItems.GOOP.get());
                        output.accept(ModItems.DOUGH.get());

                        //Blocks
                        output.accept(ModBlocks.TEST_BLOCK.get());
                        output.accept(ModBlocks.TEST_BLOCK_ORE.get());*/
                        output.accept(ModBlocks.RUBY_ORE_BLOCK.get());
                        output.accept(ModBlocks.DEEPSLATE_RUBY_ORE_BLOCK.get());
                        output.accept(ModBlocks.RUBY_BLOCK.get());
                        /*output.accept(ModBlocks.OPAL_ORE_BLOCK.get());
                        output.accept(ModBlocks.DEEPSLATE_OPAL_ORE_BLOCK.get());
                        output.accept(ModBlocks.OPAL_BLOCK.get());
                        output.accept(ModBlocks.DUNGEON_BRICK_BLOCK.get());
                        output.accept(ModBlocks.CRACKED_DUNGEON_BRICK_BLOCK.get());

                        //Advanced Blocks
                        output.accept(ModBlocks.ROPE_BLOCK.get());
                        output.accept(ModBlocks.PLATFORM_BLOCK.get());
                        output.accept(ModBlocks.MILL_BLOCK.get());*/

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
