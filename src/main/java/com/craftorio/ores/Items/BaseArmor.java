package com.craftorio.ores.Items;

import com.craftorio.ores.Ores;
import com.craftorio.ores.util.IColoredItem;
import com.craftorio.ores.util.IHasCustomModel;
import com.craftorio.ores.util.OreConfig;
import com.craftorio.ores.util.OreLogger;
import com.craftorio.ores.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseArmor extends ItemArmor implements IColoredItem, IHasCustomModel {

    private int color = 0x000000;

    public BaseArmor(ArmorMaterial mat, int renderIndex, EntityEquipmentSlot slot, int color) {
        super(mat, renderIndex, slot);
        this.setTranslationKey(Ores.MODID + ":" + mat.name() + getArmorPieceName(slot));
        this.color = color;

        OreLogger.localize(this.getTranslationKey() + ".name=" + mat.name().substring(0, 1).toUpperCase() + mat.name().substring(1) +
                " " + getArmorPieceName(slot).substring(0, 1).toUpperCase() + getArmorPieceName(slot).substring(1));
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(Ores.NAME + ":base_" + getArmorPieceName(this.getEquipmentSlot())));
    }


    private String getArmorPieceName(EntityEquipmentSlot slot) {


        switch (slot) {
            case FEET:
                return "boots";
            case LEGS:
                return "leggings";
            case CHEST:
                return "chestplate";
            case HEAD:
                return "helmet";
            default:
                return "";
        }

    }

    @Override
    public int getColor(ItemStack stack) {
        return this.color;
    }

    @Override
    public int getColor() {
        return getColor(ItemStack.EMPTY);
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
