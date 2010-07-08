package org.simoncook.interactus;

import java.io.IOException;

import java.net.URL;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Loads the Configuration for a Interactus Instance
 * @author Simon Cook
 * @version 0.0.1
 * @since 0.0.1
 */
public class ConfigurationLoader
{
    /**
     * Document object to store the parsed XML file
     */
    private Document dom;
    private Map<String,String> mp = new HashMap<String,String>();

    /**
     * Parses a configuration
     * @param configname URL pointing at valid Configuration XML file
     */
    public void parseConfig(URL configname)
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(configname.toString());
        }
        catch(ParserConfigurationException pce)
        {
            pce.printStackTrace();
        }
        catch(SAXException se)
        {
            se.printStackTrace();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        Element docEle = dom.getDocumentElement();
        NodeList nl = docEle.getElementsByTagName("ConfigSection");
        if(nl != null && nl.getLength() > 0)
        {
            for(int i = 0 ; i < nl.getLength();i++)
            {
                Element el = (Element)nl.item(i);
                if(el.getAttribute("section").equals("BotCore"))
                    parseBotCore(el);
            }
        }
    }

    /**
     * Parses the BotCore section of a configuration file.
     * @param el Element corresponding to the BotCore component
     */
    private void parseBotCore(Element el){
        NodeList nl = el.getElementsByTagName("Config");
        if(nl != null && nl.getLength() > 0)
        {
            for(int i = 0; i < nl.getLength(); i++)
            {
                Element el2 = (Element)nl.item(i);
                if(el2.getAttribute("name").equals("BotName"))
                    mp.put("BotName", el2.getFirstChild().getNodeValue());
                else if(el2.getAttribute("name").equals("DefaultJoin"))
                    parseChannelList(el2);
                else if(el2.getAttribute("name").equals("Verbosity"))
                    mp.put("Verbosity", el2.getFirstChild().getNodeValue());
                else
                    System.err.println("We have something else here!");
            }
        }
    }

    /**
     * Parses the ChannelList section of a configuration file.
     * @param el Element corresponding to the ChannelList component
     */
    private void parseChannelList(Element el){
        Element network = (Element)el.getElementsByTagName("Network").item(0);
        mp.put("Network", network.getFirstChild().getNodeValue());
        
        NodeList nl = el.getElementsByTagName("Channel");
        if(nl != null && nl.getLength() > 0)
        {
            int chanid = 0;
            for(int i = 0; i < nl.getLength(); i++)
            {
                Element el2 = (Element)nl.item(i);
                el2 = (Element)el2.getElementsByTagName("ChanName").item(0);
                mp.put("Chan"+i, el2.getFirstChild().getNodeValue());
            }
        }
    }

    /**
     * Gets the Config once the ConfigurationLoader has run.
     * @return Configuration
     */
    public Map<String,String>getConfig()
    {
        return mp;
    }
}
