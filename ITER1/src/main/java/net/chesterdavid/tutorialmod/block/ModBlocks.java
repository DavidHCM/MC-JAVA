package net.chesterdavid.tutorialmod.block;

import net.chesterdavid.tutorialmod.TutorialMod;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCK_DEFERRED_REGISTER =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    // If there is a deferred register then there will be a register.
    public static void register(IEventBus eventBus)
    {
        BLOCK_DEFERRED_REGISTER.register(eventBus);
    }
}
