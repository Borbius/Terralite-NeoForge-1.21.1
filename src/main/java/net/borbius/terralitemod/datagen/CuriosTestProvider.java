package net.borbius.terralitemod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import top.theillusivec4.curios.api.CuriosDataProvider;

import java.util.concurrent.CompletableFuture;

public class CuriosTestProvider extends CuriosDataProvider {

    public CuriosTestProvider(String modId, PackOutput output,
                              ExistingFileHelper fileHelper,
                              CompletableFuture<HolderLookup.Provider> registries) {
        super(modId, output, fileHelper, registries);
    }


    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
        //Generate Curios Slot & Entity json files
        this.createSlot("custom_shoes")
                .order(1000)
                .size(1)
                .icon(ResourceLocation.fromNamespaceAndPath("terralitemod", "slot/custom_shoes"))
                .addCosmetic(true);


        this.createSlot("curio");
        this.createSlot("charm");
        this.createSlot("back");
        this.createSlot("belt");
        this.createSlot("body");
        this.createSlot("bracelet");
        this.createSlot("charm");
        this.createSlot("head");
        this.createSlot("hands");
        this.createSlot("ring")
                .size(2);
        this.createSlot("necklace");



        //Register slot entities
        this.createEntities("slot_registerer")
                .addPlayer()
                //.addEntities()
                .addSlots("head", "back", "belt", "body", "bracelet", "charm", "hands", "necklace", "ring", "custom_shoes");
    }
}