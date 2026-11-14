package pl.elevenclients.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

/** Tymczasowy ekran - zostanie zastapiony pelnym GUI (Mods / Visual / Friends / Cosmetics / Settings). */
public class ClickGuiScreen extends Screen {
    private final Screen parent;

    public ClickGuiScreen(Screen parent) {
        super(Text.literal("ElevenClients"));
        this.parent = parent;
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        ctx.fill(0, 0, width, height, 0xF0010204);
        ctx.drawCenteredTextWithShadow(textRenderer, Text.literal("ElevenClients - GUI wkr\u00F3tce"), width / 2, height / 2 - 4, 0xFF0EA5E9);
    }

    @Override
    public void close() {
        MinecraftClient.getInstance().setScreen(parent);
    }

    @Override
    public boolean shouldPause() { return false; }
}
