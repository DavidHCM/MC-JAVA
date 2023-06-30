package net.chesterdavid.tutorialmod.item;

import net.chesterdavid.tutorialmod.TutorialMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.IModStateTransition;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    // With this we are kinda creating a object as a Item that the game can see
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    // Adding the item
    /*
    In this part we are adding the item in the game, is kinda hard to explain the process.
     -- IN THIS PART WE ARE NOT ADDING THE NAME NEITHER THE TEXTURE -- For those we need to
     add it in the resources
     */
    public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon",
        () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    // Each time we have to add a item we just use a new registry
    public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
