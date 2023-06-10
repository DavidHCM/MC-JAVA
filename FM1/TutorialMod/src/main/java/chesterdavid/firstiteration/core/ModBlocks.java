package chesterdavid.firstiteration.core;

import chesterdavid.firstiteration.TutorialMod;
import net.minecraft.util.datafix.fixes.BlockEntityKeepPacked;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks
{
    // How to register the mods, where we deferred the register of the mods
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MODID);

    public static final RegistryObject<Block> MOD_BLOCK = BLOCKS.register("mod_block",() -> new Block(BlockBehaviour.Properties.of(Material.CACTUS)));



}
