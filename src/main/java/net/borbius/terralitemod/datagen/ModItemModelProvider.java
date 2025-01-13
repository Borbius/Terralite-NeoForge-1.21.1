package net.borbius.terralitemod.datagen;

import net.borbius.terralitemod.TerraliteMod;
import net.borbius.terralitemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TerraliteMod.MOD_ID, existingFileHelper);
    }

    //Creates Item Model json's
    @Override
    protected void registerModels() {
        basicItem(ModItems.RUBY_ORE.get());
    }
}
