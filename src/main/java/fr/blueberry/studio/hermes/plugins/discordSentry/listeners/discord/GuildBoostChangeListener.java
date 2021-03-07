package fr.blueberry.studio.hermes.plugins.discordSentry.listeners.discord;

import fr.blueberry.studio.hermes.plugins.discordSentry.DiscordSentry;
import fr.blueberry.studio.hermes.plugins.discordSentry.DiscordSentryColor;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBoostCountEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildBoostChangeListener extends ListenerAdapter {
    @Override
    public void onGuildUpdateBoostCount(GuildUpdateBoostCountEvent event) {
        final int newBoost = event.getNewBoostCount();
        final int oldBoost = event.getOldBoostCount();

        String content = "";

        if (oldBoost < newBoost) {
            
            content = ":tada: **" + newBoost + " BOOST sur le serveur !**";
        } else {
            content = ":sob: **Nous avons perdus " + (oldBoost - newBoost) + "BOOSTS (" +newBoost + " BOOST)**";
        }

        DiscordSentry.log(event.getJDA().getSelfUser(), content, DiscordSentryColor.SYSTEM);
    }
}
