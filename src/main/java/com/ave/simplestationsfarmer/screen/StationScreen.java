package com.ave.simplestationsfarmer.screen;

import java.util.Arrays;
import java.util.List;

import com.ave.simplestationsfarmer.Config;
import com.ave.simplestationsfarmer.SimpleStationsFarmer;
import com.ave.simplestationsfarmer.blockentity.FarmerBlockEntity;
import com.ave.simplestationsfarmer.uihelpers.NumToString;
import com.ave.simplestationsfarmer.uihelpers.UIBlocks;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class StationScreen extends AbstractContainerScreen<StationMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SimpleStationsFarmer.MODID,
            "textures/gui/base_miner_gui.png");

    public StationScreen(StationMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void render(GuiGraphics gfx, int mouseX, int mouseY, float partialTicks) {
        super.render(gfx, mouseX, mouseY, partialTicks);
        this.renderTooltip(gfx, mouseX, mouseY);

        if (!(menu.blockEntity instanceof FarmerBlockEntity miner))
            return;

        int startX = (width - imageWidth) / 2;
        int startY = (height - imageHeight) / 2;

        if (UIBlocks.WATER_BAR.isHovered(mouseX - startX, mouseY - startY)) {
            String fuelPart = NumToString.parse(miner.fuel.getEnergyStored(), "RF / ")
                    + NumToString.parse(Config.POWER_MAX.get(), "RF");
            List<Component> fuelText = Arrays.asList(Component.translatable("screen.simplestationsfarmer.fuel"),
                    Component.literal(fuelPart));
            gfx.renderComponentTooltip(font, fuelText, mouseX, mouseY);
        }

        if (UIBlocks.FERTI_BAR.isHovered(mouseX - startX, mouseY - startY)) {
            String fertPart = miner.fertilizer + " / " + Config.FERT_MAX.get();
            List<Component> coolantText = Arrays.asList(
                    Component.translatable("screen.simplestationsfarmer.fertilizer"),
                    Component.literal(fertPart));
            gfx.renderComponentTooltip(font, coolantText, mouseX, mouseY);
        }

        if (UIBlocks.RED_BAR.isHovered(mouseX - startX, mouseY - startY)) {
            String redstonePart = miner.redstone + " / " + Config.POWER_MAX.get();
            List<Component> redstoneText = Arrays.asList(
                    Component.translatable("screen.simplestationsfarmer.power"),
                    Component.literal(redstonePart));
            gfx.renderComponentTooltip(font, redstoneText, mouseX, mouseY);
        }

        if (miner.progress > 0 && UIBlocks.PROGRESS_BAR.isHovered(mouseX - startX, mouseY - startY)) {
            int progressPart = (int) Math.ceil(100 * miner.progress / Config.MAX_PROGRESS.get());
            gfx.renderTooltip(font, Component.literal(progressPart + "%"), mouseX, mouseY);
        }

        if (miner.type == null && UIBlocks.FILTER_SLOT.isHovered(mouseX - startX, mouseY - startY)) {
            gfx.renderTooltip(font, Component.translatable("screen.simplestationsfarmer.filter"), mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float tick, int mx, int my) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        graphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight, imageWidth,
                imageHeight);

        Component title = Component.translatable("screen.simplestationsfarmer.miner");
        int textWidth = font.width(title);
        int centerX = (width / 2) - (textWidth / 2);
        graphics.drawString(font, title, centerX, y + 6, 0x222222, false);

        if (!(menu.blockEntity instanceof FarmerBlockEntity miner))
            return;

        int tickAlpha = 96 + (int) (63 * Math.sin(System.currentTimeMillis() / 400.0));
        int borderColor = (tickAlpha << 24) | 0xFF0000;
        float progressPart = miner.progress / Config.MAX_PROGRESS.get();
        UIBlocks.PROGRESS_BAR.drawProgressToRight(graphics, x, y, progressPart, 0xFFCCFEDD);

        float fuelPart = (float) miner.fuel.getEnergyStored() / Config.POWER_MAX.get();
        UIBlocks.WATER_BAR.drawProgressToTop(graphics, x, y, fuelPart, 0xAA222299);
        if (fuelPart == 0)
            UIBlocks.WATER_SLOT.drawBorder(graphics, x, y, borderColor);

        float fertPart = (float) miner.fertilizer / Config.FERT_MAX.get();
        UIBlocks.FERTI_BAR.drawProgressToTop(graphics, x, y, fertPart, 0xAAEEFFFF);

        float redstonePart = (float) miner.redstone / Config.POWER_MAX.get();
        UIBlocks.RED_BAR.drawProgressToTop(graphics, x, y, redstonePart, 0xAABB2211);
    }
}
