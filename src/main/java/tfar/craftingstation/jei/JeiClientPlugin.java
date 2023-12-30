package tfar.craftingstation.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import tfar.craftingstation.CraftingStation;
import tfar.craftingstation.CraftingStationMenu;

@JeiPlugin
public class JeiClientPlugin implements IModPlugin {
    
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(CraftingStation.MODID);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(CraftingStationMenu.class, null, RecipeTypes.CRAFTING, 1, 9, 10, 36);
    }
}
