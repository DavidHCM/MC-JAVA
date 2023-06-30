package net.chesterdavid.tutorialmod.item;

import net.chesterdavid.tutorialmod.TutorialMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    // Adding the item


    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
