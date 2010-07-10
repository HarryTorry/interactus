package org.simoncook.interactus;

import java.io.File;

import java.net.URL;

/**
 * The main Interactus Class. When running the bot from a jar file, this is the
 * class thats gets loaded to initialize the bot.
 * @author Simon Cook
 * @version 0.0.1
 * @since 0.0.1
 */
public class Interactus
{
    /**
     * String for holding the bot version
     */
    public final static String version = "0.0.1";

    /**
     * Bot is the main bot.
     */
    private static Bot bot;

    /**
     * Main Program
     * @param args Command line arguments (currently ignored)
     */
    public static void main(String[] args) throws Exception
    {
        ConfigurationLoader config = new ConfigurationLoader();
        File f = new File("config.xml");
        if(f.exists())
            config.parseConfig("config.xml");
        else
            config.parseConfig(ConfigurationLoader.class.getResource
                    ("config-default.xml"));
        bot = new Bot(config.getConfig());
    }
}
