package net.chesterdavid.tutorialmod.block;

import net.chesterdavid.tutorialmod.TutorialMod;
import net.chesterdavid.tutorialmod.item.ModItems;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCK_DEFERRED_REGISTER =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    // If there is a deferred register then there will be a register.
    public static void register(IEventBus eventBus)
    {
        BLOCK_DEFERRED_REGISTER.register(eventBus);
    }

    // Helpers methods where if there are blocks then there will be items that come with it

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T>block, CreativeModeTab tab)
    {
        RegistryObject<T> toReturn = BLOCK_DEFERRED_REGISTER.register(name,block);
        return toReturn;
    }

    // Method to return the item
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab)
    {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}
