package fr.blueberry.studio.hermes.plugins.discordSentry.listeners.discord;

import fr.blueberry.studio.hermes.plugins.discordSentry.DiscordSentry;
import fr.blueberry.studio.hermes.plugins.discordSentry.DiscordSentryColor;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMemberBanListener extends ListenerAdapter {
    @Override
    public void onGuildBan(GuildBanEvent event) {
        event.getGuild().retrieveBan(event.getUser()).queue(ban -> {
            final String banReason = ban.getReason();
            final String user = event.getUser().getAsMention();
            String content = "";

            if(banReason != null) {
                content =   """
                                :no_entry: %user% est maintenant banni(e) du serveur.
                                __**Raison**__ : %reason%
                            """.replace("%user%", user).replace("%reason%", banReason);
            } else {
                content = ":no_entry: " + event.getUser().getAsMention() + " est maintenant banni(e) du serveur.";
            }
           

            DiscordSentry.log(event.getUser(), content, DiscordSentryColor.NEGATIVE);
        });
       
        
    }
}
