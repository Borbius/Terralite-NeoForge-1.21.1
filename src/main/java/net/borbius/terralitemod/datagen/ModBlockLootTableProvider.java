package net.borbius.terralitemod.datagen;

import net.borbius.terralitemod.block.ModBlocks;
import net.borbius.terralitemod.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    //Handles block loot tables
    @Override
    protected void generate() {
        //Self Droping blocks
        dropSelf(ModBlocks.RUBY_BLOCK.get());

        //Drop Singular Item blocks
        add(ModBlocks.RUBY_ORE_BLOCK.get(),//Silk touch drop, normal drop
                block -> createOreDrop(ModBlocks.RUBY_ORE_BLOCK.get(), ModItems.RUBY_ORE.get()));

        //Drop Multiple Item blocks
        add(ModBlocks.DEEPSLATE_RUBY_ORE_BLOCK.get(),
                block -> createMultiplyOreDrops(ModBlocks.DEEPSLATE_RUBY_ORE_BLOCK.get(), ModItems.RUBY_ORE.get(), 2, 5));

    }

    protected LootTable.Builder createMultiplyOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops,maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
