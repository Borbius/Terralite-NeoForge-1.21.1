package net.borbius.terralitemod.datagen;

import net.borbius.terralitemod.TerraliteMod;
import net.borbius.terralitemod.block.ModBlocks;
import net.borbius.terralitemod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> RUBY_SPELTABLES = List.of(ModItems.RUBY_ORE,//change the output later or entire list. reason ruby not smelt able
                ModBlocks.RUBY_ORE_BLOCK, ModBlocks.DEEPSLATE_RUBY_ORE_BLOCK);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RUBY_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.RUBY_ORE.get())
                .unlockedBy("has_ruby_ore", has(ModItems.RUBY_ORE)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RUBY_ORE.get(), 9)
                .requires(ModBlocks.RUBY_BLOCK)
                .unlockedBy("has_ruby_block", has(ModBlocks.RUBY_BLOCK)).save(recipeOutput);//, "id" Use this if you have more than 1 recipe with the same output

        oreSmelting(recipeOutput, RUBY_SPELTABLES, RecipeCategory.MISC, ModItems.RUBY_ORE.get(), 0.25f, 200, "ruby");
        oreBlasting(recipeOutput, RUBY_SPELTABLES, RecipeCategory.MISC, ModItems.RUBY_ORE.get(), 0.25f, 100, "ruby");



















    }

    //All this below just moves the recipes json's file path to our mod directory instead of Minecraft's
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, TerraliteMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
