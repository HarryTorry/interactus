package org.simoncook.interactus;

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
    public static void main(String[] args)
    {
        ConfigurationLoader config = new ConfigurationLoader();
        config.parseConfig(ConfigurationLoader.class.getResource(
                "config-default.xml"));
        bot = new Bot(config.getConfig());
    }
}
