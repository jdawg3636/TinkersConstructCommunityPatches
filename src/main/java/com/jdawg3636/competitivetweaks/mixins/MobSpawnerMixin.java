package com.jdawg3636.competitivetweaks.mixins;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

@Mixin(BlockMobSpawner.class)
public class MobSpawnerMixin extends Block {

    protected MobSpawnerMixin(Material materialIn) {
        super(materialIn);
    }

    private void mixin$competitiveTweaks$addTagToMobSpawnerItemStack(ItemStack stack, World world, int x, int y, int z) {
        TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(x, y, z);
        if (spawner != null) {
            NBTTagCompound tag = new NBTTagCompound();
            spawner.func_145881_a().writeToNBT(tag);
            tag.removeTag("Delay"); // Don't store delay - prevents items from stacking in inventory
            stack.setTagCompound(tag);
        }
    }

    @Override
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, placer, stack);
        if(!world.isRemote) {
            TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(x, y, z);
            NBTTagCompound stackTag = stack.getTagCompound();
            if (spawner != null && stackTag != null) {
                spawner.func_145881_a().readFromNBT(stackTag);
            }
        }
    }

    @Overwrite
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        // Delay deletion of the block so that we can grab NBT from the TileEntity to include in the dropped ItemStack later.
        // Same strategy used by Forge's patches to net.minecraft.block.BlockFlowerPot
        if (willHarvest) {
            return true;
        }
        return super.removedByPlayer(world, player, x, y, z, willHarvest);
    }

    @Override
    protected void dropBlockAsItem(World world, int x, int y, int z, ItemStack stack) {
        if(!world.isRemote) {
            this.mixin$competitiveTweaks$addTagToMobSpawnerItemStack(stack, world, x, y, z);
        }
        super.dropBlockAsItem(world, x, y, z, stack);
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
        super.harvestBlock(world, player, x, y, z, meta);
        // Remove block since we skipped removedByPlayer where it would normally happen.
        // Same strategy used by Forge's patches to net.minecraft.block.BlockFlowerPot.
        world.setBlockToAir(x, y, z);
    }

    @Overwrite
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, int x, int y, int z) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        ItemStack stack = super.getPickBlock(target, world, x, y, z);
        this.mixin$competitiveTweaks$addTagToMobSpawnerItemStack(stack, world, x, y, z);
        return stack;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        if(!player.capabilities.isCreativeMode) {
            return false;
        }
        ItemStack heldStack = player.getHeldItem();
        if(heldStack.getItem() instanceof ItemMonsterPlacer) {
            if(!world.isRemote) {
                TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(x, y, z);
                if (spawner != null) {
                    EntityList.EntityEggInfo eggInfo = (EntityList.EntityEggInfo) EntityList.entityEggs.get(heldStack.getItemDamage());
                    if(eggInfo != null) {
                        String entityId = EntityList.getStringFromID(eggInfo.spawnedID);
                        spawner.func_145881_a().setEntityName(entityId);
                        world.markBlockForUpdate(x, y, z);
                    }
                }
            }
            return true;
        }
        return false;
    }

}
