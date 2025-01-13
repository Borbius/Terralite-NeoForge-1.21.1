package net.borbius.terralitemod.item;

import net.borbius.terralitemod.TerraliteMod;
//import net.borbius.terralitemod.item.custom.MagicMirrorItem;
//import net.borbius.terralitemod.item.custom.RecallPotionItem;
//import net.borbius.terralitemod.item.custom.RopeBundleItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    public  static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TerraliteMod.MOD_ID);
    //Items
    /*public  static  final DeferredItem<Item> RAW_TEST = ITEMS.register("raw_test",
            () -> new Item(new Item.Properties()));
    public  static  final DeferredItem<Item> TEST_INGOT = ITEMS.register("test_ingot",
            () -> new Item(new Item.Properties()));*/
    public  static  final DeferredItem<Item> RUBY_ORE = ITEMS.register("ruby_ore",
            () -> new Item(new Item.Properties()));
    /*public  static  final DeferredItem<Item> POLISHED_RUBY = ITEMS.register("polished_ruby",
            () -> new Item(new Item.Properties()));
    public  static  final DeferredItem<Item> FALLEN_STAR = ITEMS.register("fallen_star",
            () -> new Item(new Item.Properties()));
    public  static  final DeferredItem<Item> FLOUR = ITEMS.register("flour",
            () -> new Item(new Item.Properties()));
    public  static  final DeferredItem<Item> OPAL = ITEMS.register("opal",
            () -> new Item(new Item.Properties()));



    //Advanced Items
    public  static  final DeferredItem<Item> MAGIC_MIRROR = ITEMS.register("magic_mirror",
            () -> new MagicMirrorItem(new Item.Properties().rarity(Rarity.EPIC).durability(60)));
    public  static  final DeferredItem<Item> ROPE_BUNDLE = ITEMS.register("rope_bundle",
            () -> new RopeBundleItem(new Item.Properties()));
    public  static  final DeferredItem<Item> RECALL_POTION = ITEMS.register("recall_potion",
            () -> new RecallPotionItem(new Item.Properties().stacksTo(1)));


    //Food
    public  static  final DeferredItem<Item> CARBONARA = ITEMS.register("carbonara",
            () -> new Item(new Item.Properties().food(ModFoodProperties.CARBONARA)));
    public  static  final DeferredItem<Item> GOOP = ITEMS.register("goop",
            () -> new Item(new Item.Properties().food(ModFoodProperties.GOOP)));
    public  static  final DeferredItem<Item> DOUGH = ITEMS.register("dough",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DOUGH)));*/

    public  static  void  register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
