package tfar.craftingstation.client;

import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import tfar.craftingstation.CraftingStationMenu;

public class PriorityButton extends WidgetButtonBase {

  CraftingStationMenu menu;
  Tooltip useResources = Tooltip.create(Component.translatable("text.crafting_station.connected_resources.true"));
  Tooltip dontUseResources = Tooltip.create(Component.translatable("text.crafting_station.connected_resources.false"));

  public PriorityButton(int x, int y, int widthIn, int heightIn, CraftingStationMenu menu, OnPress callback) {
    super(x, y, widthIn, heightIn, 0, 0, callback);
    this.menu = menu;
  }

  @Override
  protected void renderButtonWidget() {
    setTooltip(menu.tileEntity.getUseConnectedResources() ? useResources : dontUseResources);
  }

  @Override
  protected int getTextureX() {
    return texStartX + (menu.tileEntity.getUseConnectedResources() ? 0 : this.getWidth());
  }
}