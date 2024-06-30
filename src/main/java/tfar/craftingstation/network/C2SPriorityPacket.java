package tfar.craftingstation.network;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkEvent;
import tfar.craftingstation.CraftingStationMenu;

import java.util.function.Supplier;

public class C2SPriorityPacket {

  public void handle(Supplier<NetworkEvent.Context> ctx) {
    Player player = ctx.get().getSender();

    if (player == null) return;

    ctx.get().enqueueWork(() -> {
      AbstractContainerMenu container = player.containerMenu;
      if (container instanceof CraftingStationMenu craftingStationMenu) {
        craftingStationMenu.useConnectedResources = !craftingStationMenu.useConnectedResources;
      }
    });
    ctx.get().setPacketHandled(true);
  }
}
