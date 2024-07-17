package tfar.craftingstation;

import net.minecraft.world.inventory.ContainerData;
import tfar.craftingstation.init.ModBlockEntityTypes;
import tfar.craftingstation.util.CraftingStationItemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CraftingStationBlockEntity extends BlockEntity implements MenuProvider {

  public CraftingStationItemHandler input;

  private int currentContainer = 0;
  private boolean useConnectedResources = false;

 ContainerData data = new ContainerData() {
   @Override
   public int get(int pIndex) {
     return currentContainer;
   }

   @Override
   public void set(int pIndex, int pValue) {
     currentContainer = pValue;
   }

   @Override
   public int getCount() {
     return 1;
   }
 };

  public CraftingStationBlockEntity(BlockPos pPos, BlockState pState) {
    super(ModBlockEntityTypes.crafting_station,pPos,pState);
    this.input = new CraftingStationItemHandler(9);
  }

  @Nonnull
  @Override
  public void saveAdditional(CompoundTag tag) {
    CompoundTag compound = this.input.serializeNBT();
    tag.put("inv", compound);
    tag.putBoolean("craftstyle", useConnectedResources);
  }

  @Override
  public void load(CompoundTag tag) {
    CompoundTag invTag = tag.getCompound("inv");
    input.deserializeNBT(invTag);
    useConnectedResources = tag.getBoolean("craftstyle");
    super.load(tag);
  }

  public boolean getUseConnectedResources() { return useConnectedResources; }
  public void setUseConnectedResources(boolean use) { useConnectedResources = use; }

  @Nonnull
  @Override
  public Component getDisplayName() {
    return Component.translatable("title.crafting_station");
  }

  @Nullable
  @Override
  public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
    return new CraftingStationMenu(id, playerInventory, worldPosition,data);
  }

  @Nonnull
  @Override
  public CompoundTag getUpdateTag() {
    return saveWithoutMetadata();    // okay to send entire inventory on chunk load
  }

  @Override
  public ClientboundBlockEntityDataPacket getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
  }
}

