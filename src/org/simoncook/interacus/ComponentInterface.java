package org.simoncook.interacus;

/**
 * Interface that a Component that a bot component must meet.
 * A bot component should extend the ComponentAdapter that meets all interface
 * needs, allowing the component to add which features it wants.
 * @author Simon Cook
 * @version 0.0.1
 * @see ComponentAdapter
 * @since 0.0.1
 */
public interface ComponentInterface
{
    /**
     * Returns true if the component responds to Private Messages.
     * @return true if component responds to Private Messages
     */
    public boolean takesPM();

    /**
     * Void function for handling a private message. (takesPM() will be called
     * first to determine if takePM(user,message) should be called.)
     * @param user User who send the private message to the bot.
     * @param message The message sent by the user.
     */
    public void takePM(String user, String message);

    /**
     * Returns true if the component responds to Channel Messages for a given
     * channel.
     * @param channel Channel name the message originated from.
     * @return true if component responds to Channel Messages
     */
    public boolean takesChanMessage(String channel);

    /**
     * Void function for handling a Channel message (takesChanMessage() will be
     * called first to determine if takeChanMessage(channel, user, message)
     * should be called.
     * @param channel Channel that the message came from.
     * @param user User that the message came from.
     * @param message The message sent by the user.
     */
    public void takeChanMessage(String channel, String user, String message);
}
