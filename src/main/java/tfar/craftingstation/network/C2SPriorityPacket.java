package tfar.craftingstation.network;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
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
        craftingStationMenu.tileEntity.setUseConnectedResources(!craftingStationMenu.tileEntity.getUseConnectedResources());
        BlockPos pos = craftingStationMenu.tileEntity.getBlockPos();
        BlockState state = craftingStationMenu.tileEntity.getBlockState();
        craftingStationMenu.tileEntity.getLevel().sendBlockUpdated(pos, state, state, 2);
      }
    });
    ctx.get().setPacketHandled(true);
  }
}
