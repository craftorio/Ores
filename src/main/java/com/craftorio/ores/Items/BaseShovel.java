package com.craftorio.ores.Items;

import com.craftorio.ores.Ores;
import com.craftorio.ores.util.IColoredItem;
import com.craftorio.ores.util.IHasCustomModel;
import com.craftorio.ores.util.OreConfig;
import com.craftorio.ores.util.OreLogger;
import com.craftorio.ores.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseShovel extends ItemSpade implements IHasCustomModel, IColoredItem {

    private int color = 0x000000;

    public BaseShovel(ToolMaterial t, int color) {
        super(t);
        this.color = color;
        this.setTranslationKey(Ores.MODID + ":shovel" + t.name());

        OreLogger.localize(this.getTranslationKey() + ".name=" + t.name().substring(0, 1).toUpperCase() + t.name().substring(1) + " Shovel");
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("Minecraft:iron_shovel"));
    }

    public int getColor() {
        return this.color;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        if (OreConfig.requireResourcePack) {
            return super.getItemStackDisplayName(stack);
        }
        return Ores.proxy.langs.translate(this.getUnlocalizedNameInefficiently(stack) + ".name").trim();
    }
}
