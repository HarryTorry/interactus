package org.simoncook.interactus.ExampleModule;

import java.util.Map;

import org.simoncook.interactus.*;

/**
 * Example Module to demonstrate Bot Module Interface
 * @author Simon Cook
 * @version 0.0.1
 * @since 0.0.1
 */
public class ExampleModule extends ComponentAdapter
{
    private Map<String,String> config;

    /**
     * Function for returning config value matching parameter name given
     * @param parameter Parameter to find in config
     * @returns Config matching config message
     */
    private String getConfig(String parameter)
    {
        return config.get(parameter);
    }

    /**
     * Returns true if the component responds to Channel Messages for a given
     * channel.
     * @param channel Channel name the message originated from.
     * @return true if component responds to Channel Messages
     */
    @Override
    public boolean handlesChanMessage(String channel)
    {
        return true;
    }

    /**
     * Called when component initilized.
     * @param config Bot Config
     */
    public void init(Map<String,String> config)
    {
        this.config = config;
    }

    /**
     * Void function for handling a Channel message (handlesChanMessage() will
     * be called first to determine if takeChanMessage(channel, user, message)
     * should be called.)
     * @param channel Channel that the message came from.
     * @param user User that the message came from.
     * @param message The message sent by the user.
     */
    public void takeChanMessage(String channel, String user, String message)
    {
        if(message.equals("!test"))
            Interactus.sendMessage(channel, getConfig("Secret"));
    }
}
