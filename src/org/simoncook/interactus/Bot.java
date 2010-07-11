package org.simoncook.interactus;

import java.util.LinkedList;
import java.util.List;
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
    Map<String,Map<String,String>> moduleconfig;
    List<ComponentInterface> components;

    /**
     * Default constructor. Once given a valid ConfigurationLoader, the bot will
     * then join the default IRC Server and Channels.
     * @param config ConfigurationLoader used to initilize the bot.
     */
    public Bot(Map<String,String> config, Map<String,Map<String,String>>
            moduleconfig)
    {
        this.config = config;
        this.moduleconfig = moduleconfig;
        this.setName(config.get("BotName"));
        if(config.get("Verbosity").equals("1"))
            this.setVerbose(true);
        else
            this.setVerbose(false);
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

    /**
     * This method is called once the bot has successfully connected to the IRC
     * server.
     */
    @Override
    public void onConnect()
    {
        String channel = config.get("Chan0");
        int i = 0;
        while(channel != null)
        {
            this.joinChannel(channel);
            channel = config.get("Chan" + ++i);
        }
        components = new LinkedList<ComponentInterface>();
        i = 0;
        channel = config.get("Module0");
        while(channel != null)
        {
            String component = channel + channel.substring(channel.lastIndexOf("."));
            try
            {
                Class cl = Class.forName(component);
                ComponentInterface obj = (ComponentInterface)cl.newInstance();
                obj.init(moduleconfig.get(channel));
                components.add(obj);
                channel = config.get("Module" + ++i);
            }
            catch (Exception e)
            {
                System.err.println("Unable to Load Module " + channel);
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message)
    {
        for(int i = 0; i < components.size(); i++)
        {
            ComponentInterface component = components.get(i);
            if(component.handlesChanMessage(channel))
                component.takeChanMessage(channel, sender, message);
        }
    }
}
