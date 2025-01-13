package net.borbius.terralitemod.block;

import net.borbius.terralitemod.TerraliteMod;
//import net.borbius.terralitemod.block.custom.MillBlock;
//import net.borbius.terralitemod.block.custom.PlatformBlock;
//import net.borbius.terralitemod.block.custom.RopeBlock;
import net.borbius.terralitemod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

//.noLootTable()
public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TerraliteMod.MOD_ID);
    //Creates the block
    /*public static final DeferredBlock<Block> TEST_BLOCK = registryBlock("test_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    //Block Properties
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.ANVIL)
                    ));
    public static final DeferredBlock<Block> TEST_BLOCK_ORE = registryBlock("test_block_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,4), BlockBehaviour.Properties.of()
                    .strength(5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.ANVIL)
                    ));*/
    public static final DeferredBlock<Block> RUBY_ORE_BLOCK = registryBlock("ruby_ore_block",
            () -> new DropExperienceBlock(UniformInt.of(2,4), BlockBehaviour.Properties.of()
                    .strength(5f)
                    .requiresCorrectToolForDrops()
                    ));
    public static final DeferredBlock<Block> DEEPSLATE_RUBY_ORE_BLOCK = registryBlock("deepslate_ruby_ore_block",
            () -> new DropExperienceBlock(UniformInt.of(2,4), BlockBehaviour.Properties.of()
                    .strength(5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
                    ));
    public static final DeferredBlock<Block> RUBY_BLOCK = registryBlock("ruby_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5f)
                    //.requiresCorrectToolForDrops()
                    ));
    /*public static final DeferredBlock<Block> OPAL_ORE_BLOCK = registryBlock("opal_ore_block",
            () -> new DropExperienceBlock(UniformInt.of(2,4), BlockBehaviour.Properties.of()
                    .strength(5f)
                    .requiresCorrectToolForDrops()
            ));
    public static final DeferredBlock<Block> DEEPSLATE_OPAL_ORE_BLOCK = registryBlock("deepslate_opal_ore_block",
            () -> new DropExperienceBlock(UniformInt.of(2,4), BlockBehaviour.Properties.of()
                    .strength(5f)
                    .requiresCorrectToolForDrops()
            ));
    public static final DeferredBlock<Block> OPAL_BLOCK = registryBlock("opal_block",
            () -> new DropExperienceBlock(UniformInt.of(2,4), BlockBehaviour.Properties.of()
                    .strength(5f)
                    .requiresCorrectToolForDrops()
            ));
    public static final DeferredBlock<Block> DUNGEON_BRICK_BLOCK = registryBlock("dungeon_brick_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(55f)
                    .explosionResistance(2400f)
                    .requiresCorrectToolForDrops()
                    ));
    public static final DeferredBlock<Block> CRACKED_DUNGEON_BRICK_BLOCK = registryBlock("cracked_dungeon_brick_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(55f)
                    .explosionResistance(2400f)
                    .requiresCorrectToolForDrops()
                    ));


    //Advanced Block
    public static final DeferredBlock<Block> ROPE_BLOCK = registryBlock("rope_block",
            () -> new RopeBlock(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .noOcclusion()
                    .sound(SoundType.WOOL)
                    .instabreak()
                    ));
    public static final DeferredBlock<Block> PLATFORM_BLOCK = registryBlock("platform_block",
            () -> new PlatformBlock(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .noOcclusion()
                    .sound(SoundType.WOOD)
                    .instabreak()
                    ));
    public static final DeferredBlock<Block> MILL_BLOCK = registryBlock("mill_block",
            () -> new MillBlock(BlockBehaviour.Properties.of()
                    .strength(10f)
                    .noOcclusion()
                    .sound(SoundType.WOOD)
                    ));*/


    private static <T extends  Block> DeferredBlock<T> registryBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
