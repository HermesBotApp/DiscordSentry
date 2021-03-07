package fr.blueberry.studio.hermes.plugins.discordSentry;

import java.awt.Color;

public enum DiscordSentryColor {
    POSITIVE(new Color(0, 204, 102)),
    NEGATIVE(new Color(230, 0, 0)),
    WARN(new Color(255, 153, 0)),
    INFO(new Color(0, 184, 230)),
    SYSTEM(new Color(255, 102, 255)),
    NEUTRAL(new Color(179, 179, 179));

    private final Color color;

    DiscordSentryColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
