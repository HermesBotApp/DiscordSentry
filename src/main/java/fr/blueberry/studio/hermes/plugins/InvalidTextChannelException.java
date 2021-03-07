package fr.blueberry.studio.hermes.plugins;

public class InvalidTextChannelException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidTextChannelException(long channelId) {
        super("The text channel id " + channelId + " is not valid.");
    }
    
}
