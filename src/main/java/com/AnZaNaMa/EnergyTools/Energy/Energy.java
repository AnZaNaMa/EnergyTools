package com.AnZaNaMa.EnergyTools.Energy;

import com.AnZaNaMa.EnergyTools.Block.BlockEnergyTools;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnergizer;
import com.AnZaNaMa.EnergyTools.Item.ItemExpTools;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by andre_000 on 4/13/2015.
 */
public class Energy {

    public static String[] lowEnergyValues = new String[]{
            Items.apple.getUnlocalizedName(),
            Items.arrow.getUnlocalizedName(),
            Items.acacia_door.getUnlocalizedName(),
            Items.baked_potato.getUnlocalizedName(),
            Items.bed.getUnlocalizedName(),
            Items.birch_door.getUnlocalizedName(),
            Items.boat.getUnlocalizedName(),
            Items.book.getUnlocalizedName(),
            Items.bow.getUnlocalizedName(),
            Items.bone.getUnlocalizedName(),
            Items.bowl.getUnlocalizedName(),
            Items.bread.getUnlocalizedName(),
            Items.brick.getUnlocalizedName(),
            Items.carrot.getUnlocalizedName(),
            Items.carrot_on_a_stick.getUnlocalizedName(),
            Items.clay_ball.getUnlocalizedName(),
            Items.dark_oak_door.getUnlocalizedName(),
            Items.dye.getUnlocalizedName(),
            Items.egg.getUnlocalizedName(),
            Items.feather.getUnlocalizedName(),
            Items.filled_map.getUnlocalizedName(),
            Items.fishing_rod.getUnlocalizedName(),
            Items.flower_pot.getUnlocalizedName(),
            Items.glass_bottle.getUnlocalizedName(),
            Items.item_frame.getUnlocalizedName(),
            Items.jungle_door.getUnlocalizedName(),
            Items.lead.getUnlocalizedName(),
            Items.leather.getUnlocalizedName(),
            Items.leather_boots.getUnlocalizedName(),
            Items.leather_chestplate.getUnlocalizedName(),
            Items.leather_helmet.getUnlocalizedName(),
            Items.leather_leggings.getUnlocalizedName(),
            Items.map.getUnlocalizedName(),
            Items.melon.getUnlocalizedName(),
            Items.melon_seeds.getUnlocalizedName(),
            Items.name_tag.getUnlocalizedName(),
            Items.nether_wart.getUnlocalizedName(),
            Items.netherbrick.getUnlocalizedName(),
            Items.oak_door.getUnlocalizedName(),
            Items.painting.getUnlocalizedName(),
            Items.paper.getUnlocalizedName(),
            Items.poisonous_potato.getUnlocalizedName(),
            Items.pumpkin_seeds.getUnlocalizedName(),
            Items.rabbit_foot.getUnlocalizedName(),
            Items.rabbit_hide.getUnlocalizedName(),
            Items.reeds.getUnlocalizedName(),
            Items.rotten_flesh.getUnlocalizedName(),
            Items.sign.getUnlocalizedName(),
            Items.snowball.getUnlocalizedName(),
            Items.spider_eye.getUnlocalizedName(),
            Items.spruce_door.getUnlocalizedName(),
            Items.stick.getUnlocalizedName(),
            Items.stone_axe.getUnlocalizedName(),
            Items.stone_hoe.getUnlocalizedName(),
            Items.stone_pickaxe.getUnlocalizedName(),
            Items.stone_shovel.getUnlocalizedName(),
            Items.stone_sword.getUnlocalizedName(),
            Items.string.getUnlocalizedName(),
            Items.wheat.getUnlocalizedName(),
            Items.wheat_seeds.getUnlocalizedName(),
            Items.wooden_axe.getUnlocalizedName(),
            Items.wooden_hoe.getUnlocalizedName(),
            Items.wooden_pickaxe.getUnlocalizedName(),
            Items.wooden_shovel.getUnlocalizedName(),
            Items.wooden_sword.getUnlocalizedName(),
            Items.writable_book.getUnlocalizedName(),
            Items.written_book.getUnlocalizedName(),
            Blocks.acacia_door.getUnlocalizedName(),
            Blocks.acacia_fence.getUnlocalizedName(),
            Blocks.acacia_fence_gate.getUnlocalizedName(),
            Blocks.acacia_stairs.getUnlocalizedName(),
            Blocks.bed.getUnlocalizedName(),
            Blocks.birch_door.getUnlocalizedName(),
            Blocks.birch_fence.getUnlocalizedName(),
            Blocks.birch_fence_gate.getUnlocalizedName(),
            Blocks.birch_stairs.getUnlocalizedName(),
            Blocks.bookshelf.getUnlocalizedName(),
            Blocks.brick_block.getUnlocalizedName(),
            Blocks.brick_stairs.getUnlocalizedName(),
            Blocks.brown_mushroom.getUnlocalizedName(),
            Blocks.brown_mushroom_block.getUnlocalizedName(),
            Blocks.cactus.getUnlocalizedName(),
            Blocks.carpet.getUnlocalizedName(),
            Blocks.chest.getUnlocalizedName(),
            Blocks.clay.getUnlocalizedName(),
            Blocks.cobblestone.getUnlocalizedName(),
            Blocks.cobblestone_wall.getUnlocalizedName(),
            Blocks.cocoa.getUnlocalizedName(),
            Blocks.crafting_table.getUnlocalizedName(),
            Blocks.dark_oak_door.getUnlocalizedName(),
            Blocks.dark_oak_fence.getUnlocalizedName(),
            Blocks.dark_oak_fence_gate.getUnlocalizedName(),
            Blocks.dark_oak_stairs.getUnlocalizedName(),
            Blocks.deadbush.getUnlocalizedName(),
            Blocks.dirt.getUnlocalizedName(),
            Blocks.dispenser.getUnlocalizedName(),
            Blocks.double_plant.getUnlocalizedName(),
            Blocks.double_stone_slab.getUnlocalizedName(),
            Blocks.double_stone_slab2.getUnlocalizedName(),
            Blocks.double_wooden_slab.getUnlocalizedName(),
            Blocks.dropper.getUnlocalizedName(),
            Blocks.flower_pot.getUnlocalizedName(),
            Blocks.furnace.getUnlocalizedName(),
            Blocks.glass.getUnlocalizedName(),
            Blocks.glass_pane.getUnlocalizedName(),
            Blocks.grass.getUnlocalizedName(),
            Blocks.gravel.getUnlocalizedName(),
            Blocks.hardened_clay.getUnlocalizedName(),
            Blocks.hay_block.getUnlocalizedName(),
            Blocks.ice.getUnlocalizedName(),
            Blocks.jungle_door.getUnlocalizedName(),
            Blocks.jungle_fence.getUnlocalizedName(),
            Blocks.jungle_fence_gate.getUnlocalizedName(),
            Blocks.jungle_stairs.getUnlocalizedName(),
            Blocks.ladder.getUnlocalizedName(),
            Blocks.leaves.getUnlocalizedName(),
            Blocks.leaves2.getUnlocalizedName(),
            Blocks.lever.getUnlocalizedName(),
            Blocks.lit_pumpkin.getUnlocalizedName(),
            Blocks.log.getUnlocalizedName(),
            Blocks.log2.getUnlocalizedName(),
            Blocks.melon_block.getUnlocalizedName(),
            Blocks.mossy_cobblestone.getUnlocalizedName(),
            Blocks.mycelium.getUnlocalizedName(),
            Blocks.nether_brick.getUnlocalizedName(),
            Blocks.nether_brick_fence.getUnlocalizedName(),
            Blocks.nether_brick_stairs.getUnlocalizedName(),
            Blocks.netherrack.getUnlocalizedName(),
            Blocks.oak_door.getUnlocalizedName(),
            Blocks.oak_fence.getUnlocalizedName(),
            Blocks.oak_fence_gate.getUnlocalizedName(),
            Blocks.oak_stairs.getUnlocalizedName(),
            Blocks.packed_ice.getUnlocalizedName(),
            Blocks.planks.getUnlocalizedName(),
            Blocks.pumpkin.getUnlocalizedName(),
            Blocks.red_flower.getUnlocalizedName(),
            Blocks.red_mushroom.getUnlocalizedName(),
            Blocks.red_mushroom_block.getUnlocalizedName(),
            Blocks.red_sandstone.getUnlocalizedName(),
            Blocks.red_sandstone_stairs.getUnlocalizedName(),
            Blocks.reeds.getUnlocalizedName(),
            Blocks.sand.getUnlocalizedName(),
            Blocks.sandstone.getUnlocalizedName(),
            Blocks.sandstone_stairs.getUnlocalizedName(),
            Blocks.sapling.getUnlocalizedName(),
            Blocks.snow.getUnlocalizedName(),
            Blocks.sponge.getUnlocalizedName(),
            Blocks.spruce_door.getUnlocalizedName(),
            Blocks.spruce_fence.getUnlocalizedName(),
            Blocks.spruce_fence_gate.getUnlocalizedName(),
            Blocks.spruce_stairs.getUnlocalizedName(),
            Blocks.stained_glass.getUnlocalizedName(),
            Blocks.stained_glass_pane.getUnlocalizedName(),
            Blocks.stained_hardened_clay.getUnlocalizedName(),
            Blocks.stone.getUnlocalizedName(),
            Blocks.stone_brick_stairs.getUnlocalizedName(),
            Blocks.stone_button.getUnlocalizedName(),
            Blocks.stone_pressure_plate.getUnlocalizedName(),
            Blocks.stone_slab.getUnlocalizedName(),
            Blocks.stone_slab2.getUnlocalizedName(),
            Blocks.stone_stairs.getUnlocalizedName(),
            Blocks.stonebrick.getUnlocalizedName(),
            Blocks.torch.getUnlocalizedName(),
            Blocks.trapdoor.getUnlocalizedName(),
            Blocks.tripwire_hook.getUnlocalizedName(),
            Blocks.vine.getUnlocalizedName(),
            Blocks.waterlily.getUnlocalizedName(),
            Blocks.web.getUnlocalizedName(),
            Blocks.wheat.getUnlocalizedName(),
            Blocks.wooden_button.getUnlocalizedName(),
            Blocks.wooden_pressure_plate.getUnlocalizedName(),
            Blocks.wooden_slab.getUnlocalizedName(),
            Blocks.wool.getUnlocalizedName(),
            Blocks.yellow_flower.getUnlocalizedName()
    };

    public static String[] mediumEnergyValues = new String[]{
            Items.armor_stand.getUnlocalizedName(),
            Items.beef.getUnlocalizedName(),
            Items.blaze_powder.getUnlocalizedName(),
            Items.brewing_stand.getUnlocalizedName(),
            Items.cake.getUnlocalizedName(),
            Items.cauldron.getUnlocalizedName(),
            Items.chainmail_boots.getUnlocalizedName(),
            Items.chainmail_chestplate.getUnlocalizedName(),
            Items.chainmail_helmet.getUnlocalizedName(),
            Items.chainmail_leggings.getUnlocalizedName(),
            Items.chicken.getUnlocalizedName(),
            Items.clock.getUnlocalizedName(),
            Items.comparator.getUnlocalizedName(),
            Items.compass.getUnlocalizedName(),
            Items.cookie.getUnlocalizedName(),
            Items.enchanted_book.getUnlocalizedName(),
            Items.fermented_spider_eye.getUnlocalizedName(),
            Items.firework_charge.getUnlocalizedName(),
            Items.fire_charge.getUnlocalizedName(),
            Items.fireworks.getUnlocalizedName(),
            Items.fish.getUnlocalizedName(),
            Items.flint.getUnlocalizedName(),
            Items.flint_and_steel.getUnlocalizedName(),
            Items.furnace_minecart.getUnlocalizedName(),
            Items.gold_nugget.getUnlocalizedName(),
            Items.golden_carrot.getUnlocalizedName(),
            Items.hopper_minecart.getUnlocalizedName(),
            Items.iron_axe.getUnlocalizedName(),
            Items.iron_boots.getUnlocalizedName(),
            Items.iron_chestplate.getUnlocalizedName(),
            Items.iron_door.getUnlocalizedName(),
            Items.iron_helmet.getUnlocalizedName(),
            Items.iron_hoe.getUnlocalizedName(),
            Items.iron_ingot.getUnlocalizedName(),
            Items.iron_horse_armor.getUnlocalizedName(),
            Items.iron_leggings.getUnlocalizedName(),
            Items.iron_pickaxe.getUnlocalizedName(),
            Items.iron_shovel.getUnlocalizedName(),
            Items.iron_sword.getUnlocalizedName(),
            Items.magma_cream.getUnlocalizedName(),
            Items.milk_bucket.getUnlocalizedName(),
            Items.minecart.getUnlocalizedName(),
            Items.mushroom_stew.getUnlocalizedName(),
            Items.mutton.getUnlocalizedName(),
            Items.porkchop.getUnlocalizedName(),
            Items.potionitem.getUnlocalizedName(),
            Items.prismarine_crystals.getUnlocalizedName(),
            Items.prismarine_shard.getUnlocalizedName(),
            Items.pumpkin_pie.getUnlocalizedName(),
            Items.rabbit_stew.getUnlocalizedName(),
            Items.record_11.getUnlocalizedName(),
            Items.record_13.getUnlocalizedName(),
            Items.record_blocks.getUnlocalizedName(),
            Items.record_cat.getUnlocalizedName(),
            Items.record_chirp.getUnlocalizedName(),
            Items.record_far.getUnlocalizedName(),
            Items.record_mall.getUnlocalizedName(),
            Items.record_mellohi.getUnlocalizedName(),
            Items.record_stal.getUnlocalizedName(),
            Items.record_strad.getUnlocalizedName(),
            Items.record_wait.getUnlocalizedName(),
            Items.record_ward.getUnlocalizedName(),
            Items.repeater.getUnlocalizedName(),
            Items.saddle.getUnlocalizedName(),
            Items.shears.getUnlocalizedName(),
            Items.speckled_melon.getUnlocalizedName(),
            Items.sugar.getUnlocalizedName(),
            Items.tnt_minecart.getUnlocalizedName(),
            Items.water_bucket.getUnlocalizedName(),
            Blocks.activator_rail.getUnlocalizedName(),
            Blocks.brewing_stand.getUnlocalizedName(),
            Blocks.cauldron.getUnlocalizedName(),
            Blocks.detector_rail.getUnlocalizedName(),
            Blocks.end_portal_frame.getUnlocalizedName(),
            Blocks.end_stone.getUnlocalizedName(),
            Blocks.ender_chest.getUnlocalizedName(),
            Blocks.daylight_detector.getUnlocalizedName(),
            Blocks.golden_rail.getUnlocalizedName(),
            Blocks.heavy_weighted_pressure_plate.getUnlocalizedName(),
            Blocks.hopper.getUnlocalizedName(),
            Blocks.jukebox.getUnlocalizedName(),
            Blocks.light_weighted_pressure_plate.getUnlocalizedName(),
            Blocks.noteblock.getUnlocalizedName(),
            Blocks.prismarine.getUnlocalizedName(),
            Blocks.piston.getUnlocalizedName(),
            Blocks.rail.getUnlocalizedName(),
            Blocks.sea_lantern.getUnlocalizedName(),
            Blocks.slime_block.getUnlocalizedName(),
            Blocks.soul_sand.getUnlocalizedName(),
            Blocks.sticky_piston.getUnlocalizedName(),
    };

    public static String[] highEnergyValues = new String[]{
            Items.blaze_rod.getUnlocalizedName(),
            Items.coal.getUnlocalizedName(),
            Items.cooked_beef.getUnlocalizedName(),
            Items.cooked_chicken.getUnlocalizedName(),
            Items.cooked_fish.getUnlocalizedName(),
            Items.cooked_mutton.getUnlocalizedName(),
            Items.cooked_porkchop.getUnlocalizedName(),
            Items.cooked_rabbit.getUnlocalizedName(),
            Items.diamond.getUnlocalizedName(),
            Items.emerald.getUnlocalizedName(),
            Items.ender_eye.getUnlocalizedName(),
            Items.ender_pearl.getUnlocalizedName(),
            Items.experience_bottle.getUnlocalizedName(),
            Items.ghast_tear.getUnlocalizedName(),
            Items.glowstone_dust.getUnlocalizedName(),
            Items.gold_ingot.getUnlocalizedName(),
            Items.golden_apple.getUnlocalizedName(),
            Items.gunpowder.getUnlocalizedName(),
            Items.quartz.getUnlocalizedName(),
            Items.redstone.getUnlocalizedName(),
            Blocks.anvil.getUnlocalizedName(),
            Blocks.bedrock.getUnlocalizedName(),
            Blocks.coal_block.getUnlocalizedName(),
            Blocks.coal_ore.getUnlocalizedName(),
            Blocks.glowstone.getUnlocalizedName(),
            Blocks.lapis_block.getUnlocalizedName(),
            Blocks.obsidian.getUnlocalizedName(),
            Blocks.redstone_lamp.getUnlocalizedName(),
            Blocks.redstone_torch.getUnlocalizedName(),
    };

    public static String[] veryHighEnergyValues = new String[]{
            Items.diamond_axe.getUnlocalizedName(),
            Items.diamond_boots.getUnlocalizedName(),
            Items.diamond_chestplate.getUnlocalizedName(),
            Items.diamond_helmet.getUnlocalizedName(),
            Items.diamond_hoe.getUnlocalizedName(),
            Items.diamond_shovel.getUnlocalizedName(),
            Items.diamond_sword.getUnlocalizedName(),
            Items.diamond_horse_armor.getUnlocalizedName(),
            Items.diamond_leggings.getUnlocalizedName(),
            Items.golden_axe.getUnlocalizedName(),
            Items.golden_boots.getUnlocalizedName(),
            Items.golden_chestplate.getUnlocalizedName(),
            Items.golden_helmet.getUnlocalizedName(),
            Items.golden_hoe.getUnlocalizedName(),
            Items.golden_horse_armor.getUnlocalizedName(),
            Items.golden_leggings.getUnlocalizedName(),
            Items.golden_pickaxe.getUnlocalizedName(),
            Items.golden_shovel.getUnlocalizedName(),
            Items.golden_sword.getUnlocalizedName(),
            ItemExpTools.energyingot.getUnlocalizedName(),
            Blocks.diamond_block.getUnlocalizedName(),
            Blocks.diamond_ore.getUnlocalizedName(),
            Blocks.emerald_block.getUnlocalizedName(),
            Blocks.emerald_ore.getUnlocalizedName(),
            Blocks.gold_block.getUnlocalizedName(),
            Blocks.gold_ore.getUnlocalizedName(),
            Blocks.quartz_block.getUnlocalizedName(),
            Blocks.quartz_ore.getUnlocalizedName(),
            Blocks.quartz_stairs.getUnlocalizedName(),
            Blocks.redstone_block.getUnlocalizedName(),
            Blocks.redstone_ore.getUnlocalizedName(),
    };

    public static String[] ultimateEnergyValues = new String[]{
            Items.nether_star.getUnlocalizedName(),
            Blocks.dragon_egg.getUnlocalizedName(),
            BlockEnergyTools.energyblock.getUnlocalizedName()
    };

    public static int getItemEnergyValue(Item item){
        Boolean hasBeenMatched = false;
        for(int i=0; i < lowEnergyValues.length; i++){
            if(item.getUnlocalizedName().equals(lowEnergyValues[i])){
                hasBeenMatched = true;
                break;
            }
        }
        if(!hasBeenMatched){
            for(int i=0; i < mediumEnergyValues.length; i++){
                if(item.getUnlocalizedName().equals(mediumEnergyValues[i])){
                    hasBeenMatched = true;
                    break;
                }
            }
            if(!hasBeenMatched){
                for(int i=0; i < highEnergyValues.length; i++){
                    if(item.getUnlocalizedName().equals(highEnergyValues[i])){
                        hasBeenMatched = true;
                        break;
                    }
                }
                if(!hasBeenMatched){
                    for(int i=0; i < veryHighEnergyValues.length; i++){
                        if(item.getUnlocalizedName().equals(veryHighEnergyValues[i])){
                            hasBeenMatched = true;
                            break;
                        }
                    }
                    if(!hasBeenMatched){
                        for(int i=0; i < ultimateEnergyValues.length; i++){
                            if(item.getUnlocalizedName().equals(ultimateEnergyValues[i])){
                                hasBeenMatched = true;
                                break;
                            }
                        }
                        if(hasBeenMatched){
                            return 5000;
                        }
                        else{
                            return 10;
                        }
                    }
                    else{
                        return 1000;
                    }
                }
                else{
                    return 200;
                }
            }
            else{
                return 50;
            }
        }
        else{
            return 10;
        }
    }

    public static void tryMoveEnergy(TileEntity tileEntity, EntityPlayer player, int amount){
        if(canMoveEnergy(tileEntity, player, amount)){
            ((TileEntityEnergizer)tileEntity).subtractEnergyContained(amount);
            player.getEntityData().setInteger("Energy", player.getEntityData().getInteger("Energy") + amount);
        }
    }

    public static boolean canMoveEnergy(TileEntity tileEntity, EntityPlayer player, int amount){
        if(tileEntity instanceof TileEntityEnergizer && tileEntity != null && player != null && ((TileEntityEnergizer)tileEntity).getEnergyContained() - amount >= 0 && ((TileEntityEnergizer)tileEntity).getEnergyContained() + amount <= Integer.MAX_VALUE){
            if(player.getEntityData().getInteger("Energy") + amount <= Integer.MAX_VALUE || player.getEntityData().getInteger("Energy") - amount >= Integer.MIN_VALUE){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

}
