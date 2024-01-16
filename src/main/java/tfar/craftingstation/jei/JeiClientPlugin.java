package tfar.craftingstation.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import tfar.craftingstation.CraftingStation;

@JeiPlugin
public class JeiClientPlugin implements IModPlugin {
    
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(CraftingStation.MODID);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(new TransferHandler());
    }
}
