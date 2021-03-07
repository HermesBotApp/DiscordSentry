package fr.blueberry.studio.hermes.plugins.discordSentry.listeners.discord;

import java.util.List;

import fr.blueberry.studio.hermes.plugins.discordSentry.DiscordSentry;
import fr.blueberry.studio.hermes.plugins.discordSentry.DiscordSentryColor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildRoleListener extends ListenerAdapter {
    
    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        System.out.println("Test");
        final Member member = event.getMember();
        final String content = format(member, event.getRoles(), true);

        DiscordSentry.log(member, content, DiscordSentryColor.INFO);
    }

    @Override
    public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
        System.out.println("Test remove");
        final Member member = event.getMember();
        final String content = format(member, event.getRoles(), false);

        DiscordSentry.log(member, content, DiscordSentryColor.INFO);
    }

    public String format(Member member, List<Role> roles, boolean isNew) {
        String content = "";

        if(roles.size() == 1) {
            content = ":fleur_de_lis: " + member.getAsMention() + " a " + (isNew ? "obtenu" : "perdu") + " le rôle de " + roles.get(0).getAsMention();
        } else {
            String rolesStr = "";

            for(Role role : roles) {
                rolesStr += role.getAsMention() + ", ";
            }
            content = ":fleur_de_lis: " + member.getAsMention() + " a " + (isNew ? "obtenu" : "perdu") + " les rôles de " + rolesStr;
        }
        
        return content;
    }
}
