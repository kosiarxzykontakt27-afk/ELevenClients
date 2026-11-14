package pl.elevenclients;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import pl.elevenclients.config.ElevenConfig;
import pl.elevenclients.gui.ClickGuiScreen;
import pl.elevenclients.module.Module;
import pl.elevenclients.module.ModuleManager;

public class ElevenClients implements ClientModInitializer {
    public static final String NAME = "ElevenClients";
    public static final String VERSION = "0.1.0";
    public static ModuleManager modules;

    private static KeyBinding openGui;

    @Override
    public void onInitializeClient() {
        ElevenConfig.load();
        modules = new ModuleManager();

        openGui = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.elevenclients.gui", InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT, "category.elevenclients"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGui.wasPressed()) {
                client.setScreen(new ClickGuiScreen(client.currentScreen));
            }
            for (Module m : modules.getAll()) {
                if (m.isEnabled()) m.onTick();
            }
        });

        HudRenderCallback.EVENT.register((ctx, tickCounter) -> {
            for (Module m : modules.getAll()) {
                if (m.isEnabled()) m.onRender(ctx);
            }
        });
    }
}
