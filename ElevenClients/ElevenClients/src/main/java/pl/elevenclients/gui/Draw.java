package pl.elevenclients.gui;

import net.minecraft.client.gui.DrawContext;

/** Male narzedzia do rysowania zaokraglonych prostokatow itp. */
public final class Draw {
    private Draw() {}

    public static int argb(float a, int r, int g, int b) {
        return ((int) (a * 255) << 24) | (r << 16) | (g << 8) | b;
    }

    public static int withAlpha(int rgb, float a) {
        return ((int) (a * 255) << 24) | (rgb & 0xFFFFFF);
    }

    /** Wypelniony zaokraglony prostokat. */
    public static void round(DrawContext c, int x1, int y1, int x2, int y2, int r, int color) {
        r = Math.min(r, Math.min((x2 - x1) / 2, (y2 - y1) / 2));
        c.fill(x1, y1 + r, x2, y2 - r, color);
        for (int i = 0; i < r; i++) {
            double dy = r - i - 0.5;
            int d = r - (int) Math.round(Math.sqrt(r * r - dy * dy));
            c.fill(x1 + d, y1 + i, x2 - d, y1 + i + 1, color);
            c.fill(x1 + d, y2 - i - 1, x2 - d, y2 - i, color);
        }
    }

    /** Zaokraglony prostokat z obwodka 1 px. */
    public static void roundBorder(DrawContext c, int x1, int y1, int x2, int y2, int r, int fill, int border) {
        round(c, x1, y1, x2, y2, r, border);
        round(c, x1 + 1, y1 + 1, x2 - 1, y2 - 1, Math.max(1, r - 1), fill);
    }
}
