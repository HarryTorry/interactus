package org.simoncook.interactus;

import java.util.Map;

import org.jibble.pircbot.PircBot;

/**
 * The Bot class holds the main bot, but is interacted with via the Interactus
 * class.
 * @author Simon Cook
 * @version 0.0.1
 * @since 0.0.1
 */
public class Bot extends PircBot
{

    Map<String,String> config;

    /**
     * Default constructor. Once given a valid ConfigurationLoader, the bot will
     * then join the default IRC Server and Channels.
     * @param config ConfigurationLoader used to initilize the bot.
     */
    public Bot(Map<String,String> config)
    {
        this.config = config;
        this.setName(config.get("BotName"));
        this.setVerbose(true);
        this.setLogin(this.getName());

        // Connect to server
        try
        {
            this.connect(config.get("Network"));
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    /**
     * Once the bot has connected to the server, connect to channels set in
     * configuration.
     */
    public void onConnect()
    {
        String channel = config.get("Chan0");
        int i = 0;
        while(channel != null)
        {
            this.joinChannel(channel);
            channel = config.get("Chan" + ++i);
        }
    }
}
