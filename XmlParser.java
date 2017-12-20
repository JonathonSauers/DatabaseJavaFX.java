// Jonathon Sauers
// jo046326
// Object Oriented Programming, Summer 2017
// DatabaseJavaFx.java

package inputOutput;
import java.io.IOException;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser
{
    private ConnectionData connectionData;
    private Document document;

    /**
     *
     * @param file
     */
    public XmlParser(String file)
    {
        parseXmlFile(file);
    }

    private void parseXmlFile(String fileName)
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try
        {
            // Create an instance of document builder.
            DocumentBuilder db = dbf.newDocumentBuilder();

            // document contains the complete XML as a Tree.
            document = db.parse(ClassLoader.getSystemResourceAsStream(fileName));

            // Extracting the data.
            NodeList nodeList = document.getDocumentElement().getChildNodes();

            for(int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);

                if(node instanceof Element)
                {
                    String type = node.getAttributes().getNamedItem("type").getNodeValue();
                    connectionData = new ConnectionData();
                    connectionData.setType(type);

                    NodeList childNodes = node.getChildNodes();

                    for(int j = 0; j < childNodes.getLength(); j++)
                    {
                        Node childNode = childNodes.item(j);

                        if(childNode instanceof Element)
                        {
                            String content = childNode.getLastChild().getTextContent().trim();
                            switch (childNode.getNodeName())
                            {
                                case "login":
                                  connectionData.setLogin(content);
                                  break;
                                case "password":
                                  connectionData.setPassword(content);
                                  break;
                                case "url":
                                  connectionData.setUrl(content);
                                  break;
                                case "ipaddress":
                                  connectionData.setIpaddress(content);
                                  break;
                                case "port":
                                  connectionData.setPort(content);
                                  break;
                                case "database":
                                  connectionData.setDatabase(content);
                                  break;
                            }
                        }
                    }
                }
            }
        }
        catch(ParserConfigurationException | SAXException | IOException pce) {}
    }

     /**
     *
     * @return
     */
    public ConnectionData getConnectionData()
    {
        return connectionData;
    }
}
