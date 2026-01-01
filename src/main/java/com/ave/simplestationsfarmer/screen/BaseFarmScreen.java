package com.ave.simplestationsfarmer.screen;

import java.util.Arrays;
import java.util.List;

import com.ave.simplestationscore.mainblock.BaseStationBlockEntity;
import com.ave.simplestationscore.screen.BaseStationMenu;
import com.ave.simplestationscore.screen.BaseStationScreen;
import com.ave.simplestationscore.uihelpers.NumToString;
import com.ave.simplestationsfarmer.Config;
import com.ave.simplestationsfarmer.SimpleStationsFarmer;
import com.ave.simplestationsfarmer.blockentity.BaseFarmerBlockEntity;
import com.ave.simplestationsfarmer.uihelpers.UIBlocks;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public abstract class BaseFarmScreen extends BaseStationScreen {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SimpleStationsFarmer.MODID,
            "textures/gui/station_gui.png");

    public BaseFarmScreen(BaseStationMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void renderMoreTooltips(GuiGraphics gfx, int mouseX, int mouseY, BaseStationBlockEntity station) {
        if (!(menu.blockEntity instanceof BaseFarmerBlockEntity farmer))
            return;

        int startX = getStartX();
        int startY = getStartY();

        if (UIBlocks.WATER_BAR.isHovered(mouseX - startX, mouseY - startY)) {
            String fluidPart = NumToString.parse(farmer.getFluidResource().get() / 1000f, "B / ")
                    + NumToString.parse(Config.FLUID_MAX.get() / 1000f, "B");
            List<Component> waterText = Arrays.asList(getFluidName(),
                    Component.literal(fluidPart));
            gfx.renderComponentTooltip(font, waterText, mouseX, mouseY);
        }

        if (UIBlocks.FERTI_BAR.isHovered(mouseX - startX, mouseY - startY)) {
            String fertPart = farmer.getFertResource().get() + " / " + Config.FERT_MAX.get();
            List<Component> fertText = Arrays.asList(
                    Component.translatable("screen.simplestationsfarmer.fertilizer"),
                    Component.literal(fertPart));
            gfx.renderComponentTooltip(font, fertText, mouseX, mouseY);
        }

        renderPowerTooltip(gfx, UIBlocks.POWER_BAR, mouseX, mouseY, station);
        renderProgressTooltip(gfx, UIBlocks.PROGRESS_BAR, mouseX, mouseY, station);

        if (farmer.type == null && UIBlocks.FILTER_SLOT.isHovered(mouseX - startX, mouseY - startY)) {
            gfx.renderTooltip(font, Component.translatable("screen.simplestationsfarmer.filter"), mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float tick, int mx, int my) {
        super.renderBg(graphics, tick, mx, my);
        if (!(menu.blockEntity instanceof BaseFarmerBlockEntity farm))
            return;

        int x = getStartX();
        int y = getStartY();
        int borderColor = getWarningColor();

        float waterPart = farm.getFluidResource().getPercent();
        UIBlocks.WATER_BAR.drawProgressToTop(graphics, x, y, waterPart, getFluidColor());
        if (!farm.getFluidResource().isEnough())
            UIBlocks.WATER_SLOT.drawBorder(graphics, x, y, borderColor);

        float fertPart = farm.getFertResource().getPercent();
        UIBlocks.FERTI_BAR.drawProgressToTop(graphics, x, y, fertPart, getFertColor());

        renderProgressBar(graphics, farm, UIBlocks.PROGRESS_BAR);
        renderPowerBar(graphics, farm, UIBlocks.POWER_BAR, null);
    }

    public int getFertColor() {
        return 0;
    }

    public int getFluidColor() {
        return 0xAA222299;
    }

    protected Component getFluidName() {
        return Component.translatable("screen.simplestationsfarmer.water");
    }

    public ResourceLocation getTexture() {
        return TEXTURE;
    }
}
