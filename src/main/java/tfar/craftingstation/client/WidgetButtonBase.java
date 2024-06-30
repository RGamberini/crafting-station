package tfar.craftingstation.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import tfar.craftingstation.CraftingStation;

public abstract class WidgetButtonBase extends Button {

    protected static final ResourceLocation STATION_WIDGETS = new ResourceLocation(CraftingStation.MODID, "textures/gui/station_widgets.png");

    protected int texStartX;
    protected int texStartY;

    public WidgetButtonBase(int x, int y, int widthIn, int heightIn, int texStartX, int texStartY, OnPress callback) {
        super(x, y, widthIn, heightIn, Component.empty(), callback,Button.DEFAULT_NARRATION);
        this.texStartX = texStartX;
        this.texStartY = texStartY;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        guiGraphics.blit(STATION_WIDGETS, this.getX(), this.getY(), getTextureX(), getTextureY(), this.getWidth(), this.getHeight());
        renderButtonWidget();
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        int i = getFGColor();
        this.renderString(guiGraphics, minecraft.font, i | Mth.ceil(this.alpha * 255.0F) << 24);
    }

    protected void renderButtonWidget() {}

    protected int getTextureX() {
        return texStartX;
    }

    protected int getTextureY() {
        return texStartY + (this.isHovered() ? getHeight() : 0);
    }
}
