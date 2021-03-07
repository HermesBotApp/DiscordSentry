package fr.blueberry.studio.hermes.plugins.discordSentry.listeners.discord;

import fr.blueberry.studio.hermes.plugins.discordSentry.DiscordSentry;
import fr.blueberry.studio.hermes.plugins.discordSentry.DiscordSentryColor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildVoiceListener extends ListenerAdapter {
    
    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        final Member member = event.getMember();
        final String content = format(event.getChannelJoined(), event.getChannelLeft(), member, true);

        DiscordSentry.log(member, content, DiscordSentryColor.INFO);
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
        if(event.getChannelJoined() == null) {
            final Member member = event.getMember();
            final String content = format(event.getChannelJoined(), event.getChannelLeft(), member, false);

            DiscordSentry.log(member, content, DiscordSentryColor.INFO);
        }
    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
        final Member member = event.getMember();
        final String content = format(event.getChannelJoined(), event.getChannelLeft(), member, true);

        DiscordSentry.log(member, content, DiscordSentryColor.INFO);
    }

    private String format(VoiceChannel join, VoiceChannel leave, Member member, boolean isJoin) {
            final String from = leave != null ? leave.getName() : "Aucun channel.";
            final String to = join != null ? join.getName() : "Aucun channel.";
            final String joinLeave = isJoin ? "rejoint" : "quitté";
            final String content = 
                """
                    :speaker: %mention% a %joinLeave% un salon vocal.
    
                    __**Depuis**__ : %from%
                    __**Vers**__ : %to%
                """.replace("%mention%", member.getAsMention())
                   .replace("%from%", from)
                   .replace("%to%", to)
                   .replace("%joinLeave%", joinLeave);
            
        return content;
    }
}
