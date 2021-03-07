package fr.blueberry.studio.hermes.plugins.discordSentry.listeners.discord;

import fr.blueberry.studio.hermes.plugins.discordSentry.DiscordSentry;
import fr.blueberry.studio.hermes.plugins.discordSentry.DiscordSentryColor;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MemberEditMessageListener extends ListenerAdapter {
    
    @Override
    public void onGuildMessageUpdate(GuildMessageUpdateEvent event) {
        final User author = event.getAuthor();
        final String content = ":notepad_spiral: " + author.getAsMention() + " a édité le message n°`" + event.getMessageId() + "`.";
           
         DiscordSentry.log(event.getAuthor(), content, DiscordSentryColor.WARN);
    }
}
