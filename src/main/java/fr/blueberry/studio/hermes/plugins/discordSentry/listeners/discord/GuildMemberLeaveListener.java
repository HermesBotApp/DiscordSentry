package fr.blueberry.studio.hermes.plugins.discordSentry.listeners.discord;

import fr.blueberry.studio.hermes.plugins.discordSentry.DiscordSentry;
import fr.blueberry.studio.hermes.plugins.discordSentry.DiscordSentryColor;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMemberLeaveListener extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        final String content = ":door: " + event.getUser().getAsMention() + " a quitté le serveur.";

        DiscordSentry.log(event.getUser(), content, DiscordSentryColor.NEGATIVE);
    }
}