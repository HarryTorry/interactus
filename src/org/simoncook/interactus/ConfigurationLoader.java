package org.simoncook.interactus;

import java.io.IOException;

import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Loads the Configuration for a Interactus Instance
 * @author Simon Cook
 * @version 0.0.1
 * @since 0.0.1
 */
public class ConfigurationLoader extends DefaultHandler
{

    private String tmpString;

    private void parseConfig(URL configname)
    {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try
        {
            SAXParser sp = spf.newSAXParser();
            sp.parse(configname.toString(), this);
        }
        catch(SAXException se)
        {
            se.printStackTrace();
        }
        catch(ParserConfigurationException pce)
        {
            pce.printStackTrace();
	}
        catch (IOException ie)
        {
            ie.printStackTrace();
        }
    }

    // Event Handlers
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
		tmpString = "";
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        tmpString = new String(ch,start,length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        System.out.println("We now have " + tmpString + " from " + qName);
    }

    public static void main(String[] args)
    {
        ConfigurationLoader loader = new ConfigurationLoader();
        URL configurl = ConfigurationLoader.class.getResource("config-default.xml");
        loader.parseConfig(configurl);
    }
}
