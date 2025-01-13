package net.borbius.terralitemod.event;

import net.borbius.terralitemod.TerraliteMod;
import net.borbius.terralitemod.block.ModBlocks;
import net.borbius.terralitemod.item.ModItems;
import net.borbius.terralitemod.potion.ModPotions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;


import java.util.Random;

@EventBusSubscriber(modid = TerraliteMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvent {

    /*@SubscribeEvent
    public static void extendRopeBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getEntity() instanceof Player player) {
            // Get the item the player is holding
            ItemStack itemInHand = event.getItemStack();
            BlockItem ropeBlockItem = (BlockItem) BlockItem.BY_BLOCK.get(ModBlocks.ROPE_BLOCK.get());

            // Check if the player is right-clicking the rope block and holding the rope block item
            if (event.getLevel().getBlockState(event.getPos()).getBlock() == ModBlocks.ROPE_BLOCK.get() &&
                    itemInHand.getItem() == ropeBlockItem) {
                //player.sendSystemMessage(Component.translatable("Right Clicked with Rope Block"));//Debug

                // Get the position of the clicked block and the block below it
                BlockPos currentPos = event.getPos();
                BlockPos belowPos = currentPos.below();
                Level level = event.getLevel();

                while (level.getBlockState(belowPos).getBlock() == ModBlocks.ROPE_BLOCK.get()) {
                    // If Rope move down to the next block until no rope
                    belowPos = belowPos.below();
                }
                //Checks for entities below
                boolean isOccupied = !level.getEntitiesOfClass(Entity.class, new AABB(belowPos), entity -> true).isEmpty();

                if (level.getBlockState(belowPos).isAir() & !isOccupied) {
                    // Place the rope block at the position
                    level.setBlockAndUpdate(belowPos, ModBlocks.ROPE_BLOCK.get().defaultBlockState());
                    level.playSound(event.getEntity(), currentPos, SoundEvents.WOOL_PLACE, SoundSource.BLOCKS, 1, 1);
                    if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                        if (!serverPlayer.gameMode.isCreative()) {
                            itemInHand.shrink(1);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void extendPlatformBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getEntity().isCrouching()) {
            if (event.getEntity() instanceof Player player) {
                ItemStack itemInHand = event.getItemStack();
                BlockItem platformBlockItem = (BlockItem) BlockItem.BY_BLOCK.get(ModBlocks.PLATFORM_BLOCK.get());
                if (event.getLevel().getBlockState(event.getPos()).getBlock() == ModBlocks.PLATFORM_BLOCK.get() &&
                        itemInHand.getItem() == platformBlockItem) {
                    //player.sendSystemMessage(Component.translatable("Right-Clicked with Platform Block"));//Debug
                    Direction playerFacing = player.getDirection();
                    BlockPos blockPos = event.getPos();
                    BlockPos newPos = blockPos.relative(playerFacing);
                    Level level = event.getLevel();
                    while (level.getBlockState(newPos).getBlock() == ModBlocks.PLATFORM_BLOCK.get()) {
                        newPos = newPos.relative(playerFacing);
                    }
                    boolean isOccupied = !level.getEntitiesOfClass(Entity.class, new AABB(newPos), entity -> true).isEmpty();
                    if (level.isEmptyBlock(newPos) & !isOccupied) { // Check if the block position is empty
                        level.setBlockAndUpdate(newPos, ModBlocks.PLATFORM_BLOCK.get().defaultBlockState());
                        level.playSound(event.getEntity(), event.getPos(), SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1, 1);
                        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                            if (!serverPlayer.gameMode.isCreative()) {
                                itemInHand.shrink(1);
                            }
                        }
                    }
                }
            }
        }
    }*/

    //Brewing Recipes
    @SubscribeEvent
    public static void onBrewingRecipeRegisterMethod(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.AWKWARD, Items.ENDER_PEARL, ModPotions.RANDOM_TELEPORT_POTION);
        builder.addMix(ModPotions.RANDOM_TELEPORT_POTION, ModItems.RUBY_ORE.get(),  ModPotions.RECALL_POTION);//Change the ruby ore to a custom flower
    }

    /*@SubscribeEvent
    public static void fallingStar(PlayerTickEvent event) {
        //Things to do. Add light, Switch to model/entity star, Add entity collision, Add sound, Add particles
        Level level = event.getEntity().level();
        Player player = event.getEntity();
        if (level.isNight()) {
            if (player.position().y > 60) {//underground check
                //event.player.sendSystemMessage(Component.translatable("Star can spawn "));//debug
                Random random = new Random();
                double spawnChance = 0.01;//1%   //0.0005;//0.05%
                if (random.nextDouble() < spawnChance) {
                    double radius = 5.0;//50.0

                    double xOffset = (random.nextDouble() * 2 - 1) * radius;
                    double zOffset = (random.nextDouble() * 2 - 1) * radius;

                    double yOffset = 5.0 + random.nextDouble() * 0;
                    //double yOffset = 50.0 + random.nextDouble() * 30; // Spawning at height 50 to 80 above the player

                    int xOffsetInt = (int) xOffset;
                    int yOffsetInt = (int) yOffset;
                    int zOffsetInt = (int) zOffset;

                    BlockPos spawnPos = player.blockPosition().offset(xOffsetInt, yOffsetInt, zOffsetInt);

                    event.getEntity().sendSystemMessage(Component.translatable("A star has fallen "));
                    ItemEntity itemEntity = new ItemEntity(level, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), ModItems.FALLEN_STAR.get().getDefaultInstance());
                    itemEntity.lifespan = 2400;//120 seconds

                    double randomDirectionX = (random.nextDouble() * 2 - 1);
                    double randomDirectionZ = (random.nextDouble() * 2 - 1);
                    itemEntity.setDeltaMovement(randomDirectionX,itemEntity.getDeltaMovement().y,randomDirectionZ);
                    level.addFreshEntity(itemEntity);

                    level.getEntitiesOfClass(ItemEntity.class, itemEntity.getBoundingBox().inflate(0.5))
                            .forEach(star -> {
                                if (star.getDeltaMovement().length() > 0) {
                                    if (!level.isEmptyBlock(star.blockPosition().below())) {
                                        star.setDeltaMovement(0, 0, 0);
                                    }
                                }
                            });
                }
            }
        }
    }*/
}