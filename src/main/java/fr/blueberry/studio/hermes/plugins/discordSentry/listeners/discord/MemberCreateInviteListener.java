package fr.blueberry.studio.hermes.plugins.discordSentry.listeners.discord;

import fr.blueberry.studio.hermes.plugins.discordSentry.DiscordSentry;
import fr.blueberry.studio.hermes.plugins.discordSentry.DiscordSentryColor;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteCreateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MemberCreateInviteListener extends ListenerAdapter {

    @Override
    public void onGuildInviteCreate(GuildInviteCreateEvent event) {
        final User user = event.getInvite().getInviter();
        final String url = event.getUrl();
        final String content = ":envelope: " + user.getAsMention() + " a crée l'invitation : [" + url + "](" + url + ")";

        DiscordSentry.log(user, content, DiscordSentryColor.INFO);
    }
}
