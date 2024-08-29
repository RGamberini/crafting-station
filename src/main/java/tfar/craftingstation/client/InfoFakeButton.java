package tfar.craftingstation.client;

import net.minecraft.network.chat.Component;

public class InfoFakeButton extends WidgetButtonBase {

    public InfoFakeButton(int x, int y, int widthIn, int heightIn, Component message) {
        super(x, y, widthIn, heightIn, 47, 0, null);
        setMessage(message);
    }
}
