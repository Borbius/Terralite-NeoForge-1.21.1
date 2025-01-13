package net.borbius.terralitemod.util;

import net.borbius.terralitemod.TerraliteMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> VALUABLE_BLOCKS = createTag("valuable_blocks");


        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(TerraliteMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> VALUABLE_ITEMS = createTag("valuable_items");
        public static final TagKey<Item> TERRALITE_FOOD = createTag("terralite_food");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(TerraliteMod.MOD_ID, name));
        }
    }
}
