package net.borbius.terralitemod.datagen;

import net.borbius.terralitemod.TerraliteMod;
import net.borbius.terralitemod.block.ModBlocks;
import net.borbius.terralitemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TerraliteMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.RUBY_ORE_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_RUBY_ORE_BLOCK.get())
                .add(ModBlocks.RUBY_BLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.RUBY_ORE_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_RUBY_ORE_BLOCK.get())
                .add(ModBlocks.RUBY_BLOCK.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL);
                //.add(ModBlocks);

        tag(ModTags.Blocks.VALUABLE_BLOCKS)//add all blocks to be seen with spelunker effect
                .add(ModBlocks.RUBY_ORE_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_RUBY_ORE_BLOCK.get())
                .add(ModBlocks.RUBY_BLOCK.get());
    }
}
