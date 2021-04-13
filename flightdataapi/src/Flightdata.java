import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Flightdata {

    public static void main(String[] args) throws SQLException, ParserConfigurationException {

// Create Variable for the connection string
        String connectionUrl = "jdbc:sqlserver://localhost/flightdata_A";

// Declare JDBC Objects
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
// Establish the connection
            Class.forName(".com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(connectionUrl);

// Create & Execute SQL Query to return the data
            stmt = conn.createStatement();
            stmt.execute("CREATE TABLE Flights (\n" +
                    " flightId int, \n" +
                    " flight reservation varchar(6),\n" +
                    " outflightno varchar(5),\n" +
                    " outflightclass varchar(10),\n" +
                    " outdeparttime varchar(8),\n" +
                    " outdepartdate varchar(10),\n" +
                    " outcarriercode varchar(2),\n" +
                    " outdeparttime varchar(8),\n " +
                    " outbookingclass varchar(10),\n" +
                    " outarrivaltime varchar(8),\n" +
                    " outarrivaldate varchar(10),\n" +
                    " originalprice varchar(8),\n" +
                    " originalcurrency varchar(6),\n " +
                    " oneway varchar(1),\n" +
                    " inflightno varchar(5),\n" +
                    " inflightclass varchar(10),\n" +
                    " outdepartdate varchar(10),\n" +
                    " indeparttime varchar(8),\n " +
                    " indepartdate varchar(10),\n" +
                    " indepartcode varchar(3),\n" +
                    " incarriercode varchar(3),\n" +
                    " inbookingclass varchar(10),\n" +
                    " inarrivecode varchar(3),\n " +
                    " inarrivaltime varchar(8),\n" +
                    " inarrivaldate varchar(10),\n" +
                    " id varchar(7),\n" +
                    " destair varchar(3),\n" +
                    " depair varchar(3),\n " +
                    " carrier varchar(10),\n"
            );
// Parse the XML data
            File file = new File(flightdata_A.xml);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xmlDoc = builder.parse(file);
        };
        ;

// Require the data from the nodes
        static private String getAttrValue(Node node,String attrName) {
            if ( ! node.hasAttributes() ) return "";
            NamedNodeMap nmap = node.getAttributes();
            if ( nmap == null ) return "";
            Node n = nmap.getNamedItem(attrName);
            if ( n == null ) return "";
            return n.getNodeValue();
        }
// extract data from child elements
        static private String getTextContent(Node parentNode,String childName) {
            NodeList nlist = parentNode.getChildNodes();
            for (int i = 0 ; i < nlist.getLength() ; i++) {
                Node n = nlist.item(i);
                String name = n.getNodeName();
                if ( name != null && name.equals(childName) )
                    return n.getTextContent();
            }
            return "";
        }

// Extract nodes for inserting into the database
        XPath xpath = XPathFactory.newInstance().newXPath();
        Object res = xpath.evaluate("/flightdata_A/flights/",
                XPathConstants.NODESET);

        stmt.execute("INSERT INTO Flights " +
                            "('Flight Reseveration'," +
                            "'outflightno," +
                            "'outflightclass'," +
                            "'outdeparttime'," +
                            "'outdepartdate'," +
                            "'outcarriercode'," +
                            "'outdeparttime," +
                            "'outbookingclass'," +
                            "'outarrivaltime'," +
                            "'outarrivaldate'," +
                            "'originalprice'," +
                            "'originalcurrency'," +
                            "'oneway'," +
                            "'inflightno'," +
                            "'inflightclass'," +
                            "'outdepartdate'," +
                            "'indeparttime'," +
                            "'indepartdate'," +
                            "'indepartcode'," +
                            "'incarriercode'," +
                            "'inbookingclass'," +
                            "'inarrivecode'," +
                            "'narrivaltime'," +
                            "'inarrivaldate'," +
                            "'id'," +
                            "'destair'," +
                            "'depair'," +
                            "'carrier')");

// loop to extract & insert the data
        for (int i = 0 ; i < nlist.getLength() ; i++) {
            Node node = nlist.item(i);
            List<String> columns = Arrays
                    .asList(getAttrValue(node, "id"),
                            getTextContent(node, "author"),
                            getTextContent(node, "title"),
                            getTextContent(node, "genre"),
                            getTextContent(node, "price"),
                            getTextContent(node, "publish_date"),
                            getTextContent(node, "description"));
            for (int n = 0 ; n < columns.size() ; n++) {
                stmt.setString(n+1, columns.get(n));
            }
            stmt.execute();
        }
    }   catch (SQLException ex) {
//            Handle any errors
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }
                stmt = null;
            }
        }
    }