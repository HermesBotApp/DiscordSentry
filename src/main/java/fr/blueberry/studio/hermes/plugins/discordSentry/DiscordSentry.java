package fr.blueberry.studio.hermes.plugins.discordSentry;

import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import fr.blueberry.studio.hermes.api.plugins.Plugin;
import fr.blueberry.studio.hermes.api.utils.MessageEmbedHelper;
import fr.blueberry.studio.hermes.plugins.InvalidTextChannelException;
import fr.blueberry.studio.hermes.plugins.discordSentry.listeners.discord.*;
import fr.blueberry.studio.hermes.plugins.discordSentry.listeners.hermes.BeforeCommandListener;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class DiscordSentry extends Plugin {
    public static DiscordSentry INSTANCE;

    @Override
    public void onLoad() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        getHermes().getBotManager().getJDAListenerManager().registerJDAListener(new GuildBoostChangeListener());
        getHermes().getBotManager().getJDAListenerManager().registerJDAListener(new GuildMemberJoinListener());
        getHermes().getBotManager().getJDAListenerManager().registerJDAListener(new GuildMemberLeaveListener());
        getHermes().getBotManager().getJDAListenerManager().registerJDAListener(new GuildMemberBanListener());
        getHermes().getBotManager().getJDAListenerManager().registerJDAListener(new GuildRoleListener());
        getHermes().getBotManager().getJDAListenerManager().registerJDAListener(new GuildVoiceListener());
        getHermes().getBotManager().getJDAListenerManager().registerJDAListener(new MemberCreateInviteListener());
        getHermes().getBotManager().getJDAListenerManager().registerJDAListener(new MemberEditMessageListener());

        getHermes().getPluginManager().registerEventListener(this, new BeforeCommandListener());
    }

    @Override
    public void onDisable() {
        
    }

    @Override
    public void onPostStart() {

    }
    
    public static void log(Member member, String content, DiscordSentryColor color) {
        log(member.getUser(), content, color, 0L);
    }

    public static void log(Member member, String content, DiscordSentryColor color, long delay) {
        log(member.getUser(), content, color, delay);
    }

    public static void log(User user, String content, DiscordSentryColor color) {
        log(user, content, color, 0L);
    }

    public static void log(User user, String content, DiscordSentryColor color, long delay) {
        final Guild guild = DiscordSentry.INSTANCE.getHermes().getBotManager().getTriggerer().getGuild();
        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        final MessageEmbed embed = MessageEmbedHelper.getBuilder()
            .setAuthor(user.getAsTag(), null, user.getAvatarUrl())
            .setDescription(content)
            .setTimestamp(Instant.now())
            .setColor(color.getColor())
            .setFooter(guild.getName(), guild.getIconUrl())
            .build();

        try {
            final TextChannel textChannel = DiscordSentry.INSTANCE.getLoggingTextChannel();
            executor.schedule(() -> { 
                textChannel.sendMessage(embed).queue(); 
            }, delay, TimeUnit.MILLISECONDS);
        } catch(InvalidTextChannelException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the text channel associated with the id in
     * the configuration
     * @return - The Discord text channel
     * @throws InvalidTextChannelException - Get the logging text channel
     */
    public TextChannel getLoggingTextChannel() throws InvalidTextChannelException {
        final long id = getConfiguration().getLong("logChannel");
        final TextChannel textChannel = getHermes().getBotManager().pickRandomBot(true).getTextChannel(id);

        if(textChannel == null) {
            throw new InvalidTextChannelException(id);
        }

        return textChannel;
    }
}
