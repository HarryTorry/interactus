package org.simoncook.interactus;

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
    public boolean handlesPM();

    /**
     * Void function for handling a private message. (handlesPM() will be called
     * first to determine if takePM(user,message) should be called.)
     * @param user User who sent the private message to the bot.
     * @param message The message sent by the user.
     */
    public void takePM(String user, String message);

    /**
     * Returns true if the component responds to Channel Messages for a given
     * channel.
     * @param channel Channel name the message originated from.
     * @return true if component responds to Channel Messages
     */
    public boolean handlesChanMessage(String channel);

    /**
     * Void function for handling a Channel message (handlesChanMessage() will
     * be called first to determine if takeChanMessage(channel, user, message)
     * should be called.)
     * @param channel Channel that the message came from.
     * @param user User that the message came from.
     * @param message The message sent by the user.
     */
    public void takeChanMessage(String channel, String user, String message);

    /**
     * Returns true if the component responds to Actions for a given
     * channel.
     * @param target Target for the given action.
     * @return true if component responds to Channel Actions
     */
    public boolean handlesAction(String channel);

    /**
     * Void function for handling an Action (handlesAction() will be
     * called first to determine if takeAction(target, user, action)
     * should be called.)
     * @param target Target for the Action.
     * @param user User that the action came from.
     * @param action The action sent by the user.
     */
    public void takeAction(String target, String user, String action);

    /**
     * Returns true if the component responds to Part messages for a given
     * channel.
     * @param channel Channel that the part came from
     * @return true if component responds to Part messages
     */
    public boolean handlesPart(String channel);

    /**
     * Void function for handling a Part message (handlesPart() will be called
     * first to determine if takePart(channel, user) should be called.)
     * @param channel Channel that the user parted from
     * @param user User who parted
     */
    public void takePart(String channel, String user);

    /**
     * Returns true if the component responds to Join messages for a given
     * channel.
     * @param channel Channel that the join came from
     * @return true if component responds to Join messages
     */
    public boolean handlesJoin(String channel);

    /**
     * Void function for handling a Join message (handlesJoin() will be called
     * first to determine if takeJoin(channel, user) should be called.)
     * @param channel Channel that the user joined
     * @param user User who joined
     */
    public void takeJoin(String channel, String user);

    /**
     * Returns true if the component responds to Name Changes.
     * @return true if component responds to Join messages
     */
    public boolean handlesNameChange();

    /**
     * Void function for handling a Name Change (handlesNameChane() will be
     * called first to determine if takeNameChange(origname, newname) should
     * be called).
     * @param origname Original name for the user.
     * @param newname New name for the user.
     */
    public void takeNameChange(String origname, String newname);

}
