package fr.blueberry.studio.hermes.plugins.discordSentry.listeners.hermes;

import fr.blueberry.studio.hermes.api.app.Sender;
import fr.blueberry.studio.hermes.api.plugins.HermesListen;
import fr.blueberry.studio.hermes.api.plugins.Listener;
import fr.blueberry.studio.hermes.api.plugins.events.BeforeCommandEvent;
import fr.blueberry.studio.hermes.plugins.discordSentry.DiscordSentry;
import fr.blueberry.studio.hermes.plugins.discordSentry.DiscordSentryColor;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;

public class BeforeCommandListener implements Listener {
    
    @HermesListen
    public void onUserCommand(BeforeCommandEvent event) {
        final Sender sender = event.getSender();
        final String input = event.getInput();

        if(sender.getMessageChannel() != null) {
            final String channel = sender.getMessageChannel().getType() == ChannelType.PRIVATE ? "DM Privé" : (sender.getMessageChannel().getType() == ChannelType.TEXT ? ((TextChannel)sender.getMessageChannel()).getAsMention() : sender.getMessageChannel().getName());
            final String content = 
                """
                    :gear: %identifier% a executé la commande : !%label%
                    
                    __**Channel**__ : %channel%
                    __**Commande**__ : `!%input%`
                """.replace("%identifier%", sender.getIdentifier())
                   .replace("%label%", event.getCommand().getLabel())
                   .replace("%channel%", channel)
                   .replace("%input%", input);

            if(sender.getUser() != null) {
                DiscordSentry.log(sender.getUser(), content, DiscordSentryColor.NEUTRAL);
            }
        }
    }
}
