package net.borbius.terralitemod.datagen;

import net.borbius.terralitemod.TerraliteMod;
import net.borbius.terralitemod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TerraliteMod.MOD_ID, exFileHelper);
    }

    //Generates blockState, models and texture json's for blocks
    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.RUBY_ORE_BLOCK);
        blockWithItem(ModBlocks.DEEPSLATE_RUBY_ORE_BLOCK);
        blockWithItem(ModBlocks.RUBY_BLOCK);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
